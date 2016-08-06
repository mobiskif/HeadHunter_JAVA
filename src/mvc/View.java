package mvc;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class View extends JPanel {
	private JPanel panel_1 = new JPanel();
	private JPanel panel_2 = new JPanel();
	private JPanel panel_3 = new JPanel();
	private JScrollPane scrollPane_1 = new JScrollPane();
	private JScrollPane scrollPane_3 = new JScrollPane();
	private JToolBar toolBar = new JToolBar();
	Model model;
	JTable table_1 = new JTable();
	JEditorPane editPane_2 = new JEditorPane();
	JEditorPane editPane_3 = new JEditorPane();
	JButton button = new JButton("Найти");
	JTextField textField_1 = new JTextField("IT");
	JTextField textField_2 = new JTextField("Санкт-Петербург");
	
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
		
		FontMetrics fm = getFontMetrics(new Font("Tahoma", Font.PLAIN, 14));
		int[] w = fm.getWidths();
		System.out.println(w[40]);
		
		textField_2.setEnabled(false);
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
		editPane_3.setMargin(new Insets(12, 12, 12, 12));
		editPane_3.setContentType("text/html");
		editPane_3.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		editPane_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_3.setBorder(null);
		scrollPane_3.setViewportView(editPane_3); panel_3.add(scrollPane_3);
		//panel_3.add(editPane_3);
		add(panel_3);
		
		this.model = model;
		//table_1.setModel(model.getVacanciesListModel(textField_1.getText(),0));
		table_1.setModel(model.getVacanciesList(textField_1.getText()));

		model.controller.setView(this);
		table_1.addKeyListener(model.controller);
		table_1.addMouseListener(model.controller);
		button.addKeyListener(model.controller);
		button.addMouseListener(model.controller);
		textField_1.addKeyListener(model.controller);
	}

}
