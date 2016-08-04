package mvc;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.table.DefaultTableModel;

public class Model {
	public int currentPage;
	public Map<String,Object[]> data = new HashMap<String,Object[]>();

	public DefaultTableModel getVacanciesListModel(String text, int page) {
		currentPage=page;
		String[] headers = { "Дата", "Фирма", "Работа", "Доход", "Задачи", "КодВ", "КодФ", "Лого" };
		Object[][] cache = new Object[20][headers.length];
		String[] short_headers = { "Дата", "Фирма", "Работа", "Доход", "КодВ" };
		Object[][] short_cache = new Object[20][short_headers.length];
		try {
			text = URLEncoder.encode(text, "UTF-8");
			URL url = new URL("https://api.hh.ru/vacancies?text=" 
					+ text + "&" 
					+ "area=2&" 
					+ "only_with_salary=true&"
					+ "salary=120000&" 
					+ "exclude_archived=true&" 
					+ "exclude_closed=true&" 
					+ "page=" + currentPage + "&"
					+ "order_by=salary_desc&");
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray arr = obj.getJsonArray("items");
			int i = 0;
			for (JsonObject result : arr.getValuesAs(JsonObject.class)) {
				String vacancy_id = result.get("id").toString().replace("\"", "");

				cache[i][0] = result.get("published_at").toString().substring(1, 11).replace("\"", "");
				short_cache[i][0] = result.get("published_at").toString().substring(1, 11).replace("\"", "");

				cache[i][1] = result.getJsonObject("employer").get("name").toString().replace("\"", "");
				short_cache[i][1] = result.getJsonObject("employer").get("name").toString().replace("\"", "");

				//cache[i][2] = result.getJsonObject("area").get("name").toString().replace("\"", "");
				cache[i][2] = result.get("name").toString().replace("\"", "");
				short_cache[i][2] = result.get("name").toString().replace("\"", "");

				//cache[i][3] = result.get("salary").toString().replace("\"", "");
				cache[i][3] = result.getJsonObject("salary").get("from").toString().replace("\"", "") + " .. " + result.getJsonObject("salary").get("to").toString().replace("\"", "");
				short_cache[i][3] = result.getJsonObject("salary").get("from").toString().replace("\"", "");

				cache[i][4] = result.getJsonObject("snippet").get("responsibility").toString().replace("\"", "");
				
				cache[i][5] = result.get("id").toString().replace("\"", "");
				short_cache[i][4] = result.get("id").toString().replace("\"", "");
				cache[i][6] = result.getJsonObject("employer").get("id").toString().replace("\"", "");
				try {cache[i][7] = result.getJsonObject("employer").getJsonObject("logo_urls").get("240").toString().replace("\"", "");}
				catch (Exception e) {cache[i][7]="*";}
				data.put(vacancy_id, cache[i]);
				i++;
			}
		} 
		catch (Exception e) {e.printStackTrace();}
		return new DefaultTableModel(short_cache, (Object[]) short_headers);
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
