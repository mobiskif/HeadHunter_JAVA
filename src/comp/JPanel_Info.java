package comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import mvc.Model;

@SuppressWarnings("serial")
public class JPanel_Info extends JPanel implements MouseListener, KeyListener {
	private JTable table = new JTable();
	public JTextArea textArea = new JTextArea();
	private final JPanel panel = new JPanel();
	public final JToolBar toolBar = new JToolBar();
	JButton btnSearch = new JButton("Search");
	
	public JPanel_Info() {
		super();
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(800, 375));

		Color backColor = new Color(245, 245, 245);
		table.setBackground(backColor);
		table.addMouseListener(this);

		panel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.SOUTH);
		add(panel, BorderLayout.CENTER);

		textArea.setBackground(backColor);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setPreferredSize(new Dimension(240, 120));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(textArea, BorderLayout.EAST);
	}

	public void setTableModel(Model model) {
		table.setModel(model.tableModel);
		btnSearch.addActionListener(model);
		toolBar.add(model.textField);
		toolBar.add(btnSearch);
		table.addKeyListener(this);
		for (int i = 0; i < table.getColumnCount(); i++) table.getColumnModel().getColumn(i).setMaxWidth(75);
		table.getColumnModel().getColumn(1).setMaxWidth(150);
		table.getColumnModel().getColumn(3).setMaxWidth(250);
		table.getColumnModel().getColumn(5).setMaxWidth(1200);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		textArea.setText((String) table.getValueAt(table.rowAtPoint(p), 3) 
				+ "\n\n" + (String) table.getValueAt(table.rowAtPoint(p), 5)
				+ "\n\n" + (String) table.getValueAt(table.rowAtPoint(p), 4) + " руб."
				+ "\n\"" + (String) table.getValueAt(table.rowAtPoint(p), 1) + "\""
				);
		
		if (e.getButton()==3) ((HeadHunterJsonModel) table.getModel()).getNextPage();
		if (e.getClickCount() == 2) {
			JTable table = (JTable) e.getSource();
			String s = (String) table.getValueAt(table.rowAtPoint(p), 6);
			table.changeSelection(table.rowAtPoint(p), table.columnAtPoint(p), false, false);
			try {Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe https://hh.ru/vacancy/" + s);}
			catch (IOException e1) {e1.printStackTrace();}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int row = table.getSelectedRow();
		textArea.setText((String) table.getValueAt(row, 3) 
				+ "\n\n" + (String) table.getValueAt(row, 5)
				+ "\n\n" + (String) table.getValueAt(row, 4) + " руб."
				+ "\n\"" + (String) table.getValueAt(row, 1) + "\""
				);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
