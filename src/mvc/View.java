package mvc;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class View extends JPanel {
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JScrollPane scrollPane_3 = new JScrollPane();
	final JTable table_1 = new JTable();
	final JEditorPane editPane_2 = new JEditorPane();
	final JEditorPane editPane_3 = new JEditorPane();
	Model model;
	private final JToolBar toolBar = new JToolBar();
	JTextField textField_1 = new JTextField("ИТ");
	private final JButton button = new JButton("Найти");
	private final JTextField textField_2 = new JTextField("Санкт-Петербург", 6);
	
	public View(Model model) {
		super();
		//System.out.println(model.data.size());
		setPreferredSize(new Dimension(940, 375));
		setMinimumSize(new Dimension(940, 375));
		setPreferredSize(new Dimension(940, 375));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panel_1.setMaximumSize(new Dimension(460, 32767));
		panel_1.setPreferredSize(new Dimension(460, 350));		
		panel_1.setMinimumSize(new Dimension(460, 350));
		panel_1.setLayout(new BorderLayout(0, 0));
		table_1.setName("Таблица результатов");
		scrollPane_1.setViewportView(table_1); panel_1.add(scrollPane_1, BorderLayout.CENTER);
		textField_2.setEnabled(false);
		//textField_2.setcol;
		
		toolBar.add(textField_2);
		textField_1.setName("Поле поиска");
		textField_1.setMargin(new Insets(2, 4, 2, 2));
		toolBar.add(textField_1);
		button.setName("Найти");
		toolBar.add(button);
		panel_1.add(toolBar, BorderLayout.SOUTH);
		add(panel_1);

		
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		panel_2.setMinimumSize(new Dimension(260, 350));
		panel_2.setMaximumSize(new Dimension(260, 32767));
		panel_2.setPreferredSize(new Dimension(260, 350));
		editPane_2.setMargin(new Insets(3, 12, 3, 12));
		editPane_2.setContentType("text/html");
		editPane_2.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		editPane_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(editPane_2);
		add(panel_2);

		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));	
		panel_3.setMinimumSize(new Dimension(240, 350));
		panel_3.setPreferredSize(new Dimension(240, 350));
		editPane_3.setMargin(new Insets(3, 12, 3, 12));
		editPane_3.setContentType("text/html");
		editPane_3.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		editPane_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_3.setBorder(null);
		scrollPane_3.setViewportView(editPane_3); panel_3.add(scrollPane_3);
		//panel_3.add(editPane_3);
		add(panel_3);
		
		Controller c = new Controller(this);
		table_1.addKeyListener(c);
		table_1.addMouseListener(c);
		table_1.setModel(model.getVacanciesListModel(textField_1.getText(),0));
		button.addKeyListener(c);
		button.addMouseListener(c);
		textField_1.addKeyListener(c);
		
		this.model = model;
		table_1.getColumnModel().getColumn(0).setMinWidth(75);
		table_1.getColumnModel().getColumn(0).setMaxWidth(75);
		table_1.getColumnModel().getColumn(1).setMaxWidth(75);
		table_1.getColumnModel().getColumn(3).setMaxWidth(45);
		table_1.getColumnModel().getColumn(4).setMaxWidth(45);

	}

}
