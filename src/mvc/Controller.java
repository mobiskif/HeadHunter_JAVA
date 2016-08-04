package mvc;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.JTextField;

public class Controller implements KeyListener, MouseListener  {
	View view = null;

	public Controller(View v) {
		view=v;
		//System.out.println(view.model.data.size());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(e.getComponent().getName());
		if ( e.getKeyCode()==10 && e.getComponent().getName().equals("Поле поиска")) {
			view.table_1.setModel(view.model.getVacanciesListModel(view.textField_1.getText(),0));
		}
		else if ( e.getKeyCode()!=10 && e.getComponent().getName().equals("Таблица результатов")) {
			JTable table = (JTable) e.getComponent();
			String vacancy_id = (String) table.getValueAt(table.getSelectedRow(), 4);
			String logo_url = view.model.data.get(vacancy_id)[7].toString();
			String work = view.model.data.get(vacancy_id)[2].toString();
			String task = view.model.data.get(vacancy_id)[4].toString();
			String employer_id = view.model.data.get(vacancy_id)[6].toString();
			String salary = view.model.data.get(vacancy_id)[3].toString();			
			view.editPane_2.setText(""
					+ "<h2>" + work	+ "</h2>" 
					+ "<h3>" + salary	+ "</h3>" 
					+ task
					+ "<br/><br/><img src=\"" + logo_url + "\" />"
				);
			view.editPane_3.setText(view.model.getCompanyByID(employer_id));
			view.editPane_3.setCaretPosition(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		//JTable table = (JTable) e.getComponent();
		if (e.getButton() == 1) {
			String vacancy_id = (String) view.table_1.getValueAt(view.table_1.getSelectedRow(), 4);
			String logo_url = view.model.data.get(vacancy_id)[7].toString();
			String work = view.model.data.get(vacancy_id)[2].toString();
			String task = view.model.data.get(vacancy_id)[4].toString();
			String employer_id = view.model.data.get(vacancy_id)[6].toString();
			String salary = view.model.data.get(vacancy_id)[3].toString();			
			view.editPane_2.setText(""
					+ "<h2>" + work	+ "</h2>" 
					+ "<h3>" + salary	+ "</h3>" 
					+ task
					+ "<br/><br/><img src=\"" + logo_url + "\" />"
				);
			view.editPane_3.setText(view.model.getCompanyByID(employer_id));
			view.editPane_3.setCaretPosition(0);
		}
		else if (e.getButton()==3) {
			view.table_1.setModel(view.model.getVacanciesListModel("IT",view.model.currentPage+1));
			view.table_1.getColumnModel().getColumn(0).setMinWidth(75);
			view.table_1.getColumnModel().getColumn(0).setMaxWidth(75);
			view.table_1.getColumnModel().getColumn(1).setMaxWidth(75);
			view.table_1.getColumnModel().getColumn(3).setMaxWidth(45);
			view.table_1.getColumnModel().getColumn(4).setMaxWidth(45);
		}
		else if (e.getButton()==2) {
			String vacancy_id = (String) view.table_1.getValueAt(view.table_1.getSelectedRow(), 4);
			//String s = (String) table.getValueAt(table.rowAtPoint(p), 5);
			//String s = view.model.data.get(vacancy_id)[4].toString();
			//table.changeSelection(table.rowAtPoint(p), table.columnAtPoint(p), false, false);
			try {Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe https://hh.ru/vacancy/" + vacancy_id);}
			catch (IOException e1) {e1.printStackTrace();}
		}
		else if (e.getComponent().getName().equals("Найти")) {
			view.table_1.setModel(view.model.getVacanciesListModel(view.textField_1.getText(),0));	
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
