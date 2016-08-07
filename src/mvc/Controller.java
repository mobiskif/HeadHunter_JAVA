package mvc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

public class Controller implements KeyListener, MouseListener  {
	Model model;

	public void setModel(Model m) {
		model=m;
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
		else if (e.getButton()==1) 
			model.setVacancyDetail((JTable)e.getSource());
		else if (e.getButton()==2) { 
			model.openVacancyInBrowser((JTable)e.getSource());
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
