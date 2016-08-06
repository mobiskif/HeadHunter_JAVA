package mvc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Controller implements KeyListener, MouseListener  {
	View view = null;

	public void setView(View v) {
		view=v;
	}

	public void setContent() {
		String vacancy_id = (String) view.table_1.getValueAt(view.table_1.getSelectedRow(), 0);
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
		if ( e.getKeyCode()==10 && e.getComponent().getName().equals("Поле поиска")) {
			view.table_1.setModel(view.model.getVacanciesList(view.textField_1.getText()));
		}
		else if ( (e.getKeyCode()==40 || e.getKeyCode()==38) && e.getComponent().getName().equals("Таблица результатов")) {
			setContent();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1 && e.getComponent().getName().equals("Найти")) {
			view.table_1.setModel(view.model.getVacanciesList(view.textField_1.getText()));	
		}
		else if (e.getButton() == 1) {
			setContent();
		}
		else if (e.getButton()==2) {
			String vacancy_id = (String) view.table_1.getValueAt(view.table_1.getSelectedRow(), 0);
			//String s = (String) table.getValueAt(table.rowAtPoint(p), 5);
			try {Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe https://hh.ru/vacancy/" + vacancy_id);}
			catch (IOException e1) {e1.printStackTrace();}
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
