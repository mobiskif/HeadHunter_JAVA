package mvc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import comp.SortByDate;
import comp.Vacancy;

public class Model {
	View view;
	Controller controller;
	
	ArrayList<Vacancy> VacancyList;
	Map<String,Vacancy> VacancyMap;

	public Model(Controller contr) {
		controller = contr;
	}
	
	public DefaultTableModel getVacanciesList(String text) {
		int page=0;
		VacancyList = new ArrayList<Vacancy>();
		VacancyMap= new HashMap<String,Vacancy>();
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
					for (JsonObject result : arr.getValuesAs(JsonObject.class)) {
						Vacancy v = new Vacancy();
						v.vacancy_id = result.get("id").toString().replace("\"", "");
						v.vacancy_name = result.get("name").toString().replace("\"", "");
						v.published_at = result.get("published_at").toString().substring(1, 11).replace("\"", "");
						v.employer_id = result.getJsonObject("employer").get("id").toString().replace("\"", "");
						v.employer_name  = result.getJsonObject("employer").get("name").toString().replace("\"", "");
						v.employer_logourl = "*";
						try {v.employer_logourl = result.getJsonObject("employer").getJsonObject("logo_urls").get("240").toString().replace("\"", "");}
						catch (Exception e) {}
						v.salary = result.getJsonObject("salary").get("from").toString().replace("\"", "") + " .. " + result.getJsonObject("salary").get("to").toString().replace("\"", "");
						v.snippet = result.getJsonObject("snippet").get("responsibility").toString().replace("\"", "");

						VacancyList.add(v);
						VacancyMap.put(v.vacancy_id, v);					
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

		Collections.sort(VacancyList, new SortByDate());
		
		Object[] header = { "ID", "Дата", "Фирма", "Работа", "Доход" };
		Object[][] cells = new Object[VacancyList.size()][header.length];
		int j=0;
		for (Iterator<Vacancy> iterator = VacancyList.iterator(); iterator.hasNext();) {
			Vacancy v = (Vacancy) iterator.next();
			cells[j][0]=v.vacancy_id;
			cells[j][1]=v.published_at;
			cells[j][2]=v.employer_name;
			cells[j][3]=v.snippet;
			cells[j][4]=v.salary;
			j++;
		}
		//fireTableDataChanged();
		return new DefaultTableModel(cells, header);
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
	}

	public void setView(View v) {
		view = v;
		controller.setModel(this);	
	}

	public void openVacancyInBrowser(JTable table) {
		String vacancy_id = table.getValueAt(table.getSelectedRow(), 0).toString();
		try {Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe https://hh.ru/vacancy/" + vacancy_id);}
		catch (IOException e1) {e1.printStackTrace();}
	}
	
	public void setVacancyDetail(JTable table) {
		String vacancy_id	= table.getValueAt(table.getSelectedRow(), 0).toString();
		String logo_url		= VacancyMap.get(vacancy_id).employer_logourl;
		String work			= VacancyMap.get(vacancy_id).vacancy_name;
		String task 		= VacancyMap.get(vacancy_id).snippet;
		String employer_id	= VacancyMap.get(vacancy_id).employer_id;
		String salary 		= VacancyMap.get(vacancy_id).salary;
		
		view.editPane_VacancyDetail.setText(""
				+ "<h2>" + work	+ "</h2>" 
				+ "<h3>" + salary + "</h3>" 
				+ "<p>" + task + "</p>"
				+ "<br/><img src=\"" + logo_url + "\" />"
				);		
		view.editPane_EmployerDetail.setText(getCompanyByID(employer_id));
		view.editPane_EmployerDetail.setCaretPosition(0);		
	}

	public void updateModel() {
		view.table_VacanciesList.setModel(getVacanciesList(view.textField_SearchPhrase.getText()));
	}
		
}
