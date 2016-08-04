package comp;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.table.AbstractTableModel;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Vector;

@SuppressWarnings("serial")
public class HHJsonVacanciesListModel extends AbstractTableModel {
	public Vector<String[]> cache = new Vector<String[]>();
	public String[] headers = { "Дата", "Фирма", "Город", "Работа", "Доход", "Задачи", "КодВ", "КодР" };
	int page=0;
	String search="";

	public HHJsonVacanciesListModel(String arg) {
		super();
		getListVacanciesByKeyword(arg,page);
	}

	public String getColumnName(int i) {
		return headers[i].toString();
	}

	public int getColumnCount() {
		return headers.length;
	}

	public int getRowCount() {
		return cache.size();
	}

	public Object getValueAt(int row, int col) {
		return ((String[]) cache.elementAt(row))[col];
	}

	public void getListVacanciesByKeyword(String arg, int pag) {
		cache = new Vector<String[]>();
		String[] record = new String[headers.length];

		try {
			String text = URLEncoder.encode(arg, "UTF-8");
			this.page = pag;
			//this.text = arg;
			System.out.println(text+" " +page);

			// URL url = new URL ("https://portal.dtek.com?"
			URL url = new URL("https://api.hh.ru/vacancies?"
					// + "use_recommendations=false&"
					+ "text=" + text + "&"
					// + "L_neighbours=true&"
					// + "search_field=name&"
					// + "search_field=company_name&"
					// + "search_field=description&"
					// + "search_debug=false&"
					 + "items_on_page=30&"
					+ "area=2&"
					// + "area=3&"
					// + "area=104&"
					// + "area=22&area=102&"
					// + "L_disable_clusters_narrowing=false&"
					// + "enable_snippets=true&"
					// + "no_magic=false&"
					+ "only_with_salary=true&"
					// + "L_site=XHH&"
					// + "clusters=true&"
					+ "salary=120000&" + "exclude_archived=true&"
					// + "L_lenient=true&"
					// + "L_priority_sort=metallic&"
					// + "currency_code=RUR&"
					+ "exclude_closed=true&"
					+ "page="+ this.page + "&"
					+ "order_by=salary_desc&"
					// + "order_by=published_at_desc&"
					+ "search_period=");
			InputStream is = url.openStream();
			//is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray arr = obj.getJsonArray("items");

			for (JsonObject result : arr.getValuesAs(JsonObject.class)) {
				record = new String[headers.length];
				
				record[0] = result.get("published_at").toString().substring(1, 11).replace("\"", "");
				record[1] = result.getJsonObject("employer").get("name").toString().replace("\"", "");
				record[2] = result.getJsonObject("area").get("name").toString().replace("\"", "");
				record[3] = result.get("name").toString().replace("\"", "");
				record[4] = result.get("salary").toString().replace("\"", "");
				record[5] = result.getJsonObject("snippet").get("responsibility").toString().replace("\"", "");
				record[6] = result.get("id").toString().replace("\"", "");
				record[7] = result.getJsonObject("employer").get("id").toString().replace("\"", "");
				if (result.get("salary").toString().length() > 4) {
					record[4] = result.getJsonObject("salary").get("from").toString().replace("\"", "");
					if (record[4]=="null") {record[4] = result.getJsonObject("salary").get("to").toString().replace("\"", "");}
				}
				cache.addElement(record);
			}

		} catch (Exception e) {
			e.printStackTrace();
			cache.addElement(headers);
			cache.addElement(headers);
		}
		this.fireTableDataChanged();
	}

	public void getNextPage() {
		this.getListVacanciesByKeyword(this.search, ++page);
		this.fireTableDataChanged();
	}


	public void getVacancyByID(String ID) {
		cache = new Vector<String[]>();
		String[] record = new String[headers.length];

		try {
			String text = URLEncoder.encode(ID, "UTF-8");
			//this.page = pag;
			//this.text = arg;
			System.out.println(text+" " +page);

			// URL url = new URL ("https://portal.dtek.com?"
			URL url = new URL("https://api.hh.ru/vacancies?"
					// + "use_recommendations=false&"
					+ "text=" + text + "&"
					// + "L_neighbours=true&"
					// + "search_field=name&"
					// + "search_field=company_name&"
					// + "search_field=description&"
					// + "search_debug=false&"
					 + "items_on_page=30&"
					+ "area=2&"
					// + "area=3&"
					// + "area=104&"
					// + "area=22&area=102&"
					// + "L_disable_clusters_narrowing=false&"
					// + "enable_snippets=true&"
					// + "no_magic=false&"
					+ "only_with_salary=true&"
					// + "L_site=XHH&"
					// + "clusters=true&"
					+ "salary=120000&" + "exclude_archived=true&"
					// + "L_lenient=true&"
					// + "L_priority_sort=metallic&"
					// + "currency_code=RUR&"
					+ "exclude_closed=true&"
					+ "page="+ this.page + "&"
					+ "order_by=salary_desc&"
					// + "order_by=published_at_desc&"
					+ "search_period=");
			InputStream is = url.openStream();
			//is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray arr = obj.getJsonArray("items");

			for (JsonObject result : arr.getValuesAs(JsonObject.class)) {
				record = new String[headers.length];
				
				record[0] = result.get("published_at").toString().substring(1, 11).replace("\"", "");
				record[1] = result.getJsonObject("employer").get("name").toString().replace("\"", "");
				record[2] = result.getJsonObject("area").get("name").toString().replace("\"", "");
				record[3] = result.get("name").toString().replace("\"", "");
				record[4] = result.get("salary").toString().replace("\"", "");
				record[5] = result.getJsonObject("snippet").get("responsibility").toString().replace("\"", "");
				record[6] = result.get("id").toString().replace("\"", "");
				if (result.get("salary").toString().length() > 4) {
					record[4] = result.getJsonObject("salary").get("from").toString().replace("\"", "");
					if (record[4]=="null") {record[4] = result.getJsonObject("salary").get("to").toString().replace("\"", "");}
				}
				cache.addElement(record);
			}

		} catch (Exception e) {
			e.printStackTrace();
			cache.addElement(headers);
			cache.addElement(headers);
		}
		this.fireTableDataChanged();
	}


}
