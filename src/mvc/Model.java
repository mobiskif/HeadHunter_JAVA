package mvc;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.table.DefaultTableModel;

import comp.Vacancy;

public class Model {
	public Map<String,Object[]> data = new HashMap<String,Object[]>();
	public Controller controller;
	ArrayList<Vacancy> vac = new ArrayList<Vacancy>();

	public Model(Controller contr) {
		controller = contr;
	}
	
	public <T> DefaultTableModel getVacanciesList(String text) {
		int page=0;
		data = new HashMap<String,Object[]>();
		while (page>=0) {
			try {
				text = URLEncoder.encode(text, "UTF-8");
				URL url = new URL("https://api.hh.ru/vacancies?text=" 
						+ text + "&" 
						+ "area=2&" 
						+ "only_with_salary=true&"
						+ "salary=120000&" 
						+ "exclude_archived=true&" 
						+ "exclude_closed=true&" 
						+ "page=" + page + "&"
						+ "order_by=salary_desc&"
						);
				InputStream is = url.openStream();
				JsonReader rdr = Json.createReader(is);
				JsonObject obj = rdr.readObject();
				JsonArray arr = obj.getJsonArray("items");
				if (arr.size()>0) {
					//System.out.println(url);
					Object[][] cache = new Object[arr.size()][8];
					int i = 0;
					for (JsonObject result : arr.getValuesAs(JsonObject.class)) {
						String vacancy_id = result.get("id").toString().replace("\"", "");
						String vacancy_name = result.get("name").toString().replace("\"", "");
						String published_at = result.get("published_at").toString().substring(1, 11).replace("\"", "");
						String employer_id = result.getJsonObject("employer").get("id").toString().replace("\"", "");
						String employer_name  = result.getJsonObject("employer").get("name").toString().replace("\"", "");
						String employer_logourl = "*";
						try {employer_logourl = result.getJsonObject("employer").getJsonObject("logo_urls").get("240").toString().replace("\"", "");}
						catch (Exception e) {}
						String salary = result.getJsonObject("salary").get("from").toString().replace("\"", "") + " .. " + result.getJsonObject("salary").get("to").toString().replace("\"", "");
						String snippet = result.getJsonObject("snippet").get("responsibility").toString().replace("\"", "");
						
						Vacancy v = new Vacancy();
							v.vacancy_id = vacancy_id;
							v.vacancy_name = vacancy_name;
							v.published_at = published_at;
							v.employer_id = employer_id;
							v.employer_name = employer_name;
							v.employer_logourl = employer_logourl;
							v.salary = salary;
							v.snippet = snippet;
						
		
						cache[i][0] = vacancy_id;
						cache[i][1] = published_at;
						cache[i][2] = employer_name;
						cache[i][3] = vacancy_name;
						cache[i][4] = salary;
						cache[i][5] = snippet;
						cache[i][6] = employer_id;
						cache[i][7] = employer_logourl;
						
						vac.add(v);
						
						data.put(vacancy_id, cache[i]);
						//data.put(vacancy_id, v);
						i++;
					}
				}
				else {
					page = -1;
					break;
				}
			} 
			catch (Exception e) {e.printStackTrace();}
			page++;
		}
/*
		String[] short_headers = { "КодВ", "Дата", "Фирма", "Работа", "Доход" };
		Object[][] table_data = new Object[data.size()][short_headers.length];
		int k=0;
		for (Iterator<Object[]> iterator = data.values().iterator(); iterator.hasNext();) {
			Object[] map = iterator.next();
			table_data[k]=map;
			k++;
		}
*/
		Collections.sort(vac, new SortByID());
		
		String[] short_headers = { "КодВ", "Дата", "Фирма", "Работа", "Доход" };
		Object[][] table_data = new Object[data.size()][short_headers.length];
		int j=0;
		for (Iterator iterator = vac.iterator(); iterator.hasNext();) {
			Vacancy v = (Vacancy) iterator.next();
			table_data[j][0]=v.vacancy_id;
			//table_data[j][1]=v.vacancy_name;
			table_data[j][1]=v.published_at;
			//table_data[j][3]=v.employer_id;
			table_data[j][2]=v.employer_name;
			//table_data[j][5]=v.employer_logourl;
			table_data[j][4]=v.salary;
			table_data[j][3]=v.snippet;
			j++;
		}
			
		return new DefaultTableModel(table_data, (Object[]) short_headers);
	}

	public String getCompanyByID(String ID) {
		try {
			//System.out.println(ID);
			URL url = new URL("https://api.hh.ru/employers/"+ ID);
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			return obj.get("description").toString().replace("\"", "");
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		//this.fireTableDataChanged();
	}

		
}
