package mvc;

import static org.junit.Assert.*;

import javax.swing.table.DefaultTableModel;

import org.junit.Test;

public class ModelTest {

	@Test
	public void testGetCompanyByID() {
		String result = new Model(null).getCompanyByID("41144");
		assertTrue(result.contains("АСКОН"));
	}
	@Test
	public void testGetvacanctiesList() {
		DefaultTableModel result = new Model(null).getVacanciesList("водитель");
		assertTrue(result.getColumnCount()==5);
	}
}
