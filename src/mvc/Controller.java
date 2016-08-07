package mvc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JTable;

public class Controller implements KeyListener, MouseListener  {
	Model model;

	public void setModel(Model m) {
		model=m;
	}

	public void openVacancyInExplorer(JTable table) {
		String vacancy_id = table.getValueAt(table.getSelectedRow(), 0).toString();
		try {Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe https://hh.ru/vacancy/" + vacancy_id);}
		catch (IOException e1) {e1.printStackTrace();}
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
		if ( e.getKeyCode()==10 && e.getComponent().getName().equals("Поле поиска"))
			model.updateModel();
		else if ( (e.getKeyCode()==40 || e.getKeyCode()==38) && e.getComponent().getName().equals("Таблица результатов")) {
			model.setVacancyDetail((JTable)e.getSource());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1 && e.getComponent().getName().equals("Найти")) 
			model.updateModel();
		else {
			JTable table = (JTable)e.getSource();
			if (e.getButton() == 1) model.setVacancyDetail(table);
			else if (e.getButton()==2) openVacancyInExplorer(table);
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
