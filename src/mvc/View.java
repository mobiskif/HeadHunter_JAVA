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
	private JPanel panelVacancyList = new JPanel();
	private JPanel panelVacancyDetailed = new JPanel();
	private JPanel panelEmployerDetailed = new JPanel();
	private JScrollPane scrollPane_Table = new JScrollPane();
	private JScrollPane scrollPane_Employer = new JScrollPane();
	private JToolBar toolBar = new JToolBar();
	JTable table_VacanciesList = new JTable();
	JEditorPane editPane_VacancyDetail = new JEditorPane();
	JEditorPane editPane_EmployerDetail = new JEditorPane();
	JButton button = new JButton("Найти");
	JTextField textField_SearchPhrase = new JTextField("IT");
	JTextField textField_CitySelect = new JTextField("Санкт-Петербург");
	
	public View(Model model) {
		super();
		model.setView(this);

		setPreferredSize(new Dimension(940, 375));
		setMinimumSize(new Dimension(940, 375));
		setPreferredSize(new Dimension(940, 375));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panelVacancyList.setMaximumSize(new Dimension(460, 32767));
		panelVacancyList.setPreferredSize(new Dimension(460, 350));		
		panelVacancyList.setMinimumSize(new Dimension(460, 350));
		panelVacancyList.setLayout(new BorderLayout(0, 0));
		table_VacanciesList.setName("Таблица результатов");
		scrollPane_Table.setViewportView(table_VacanciesList); panelVacancyList.add(scrollPane_Table, BorderLayout.CENTER);
		
		//FontMetrics fm = getFontMetrics(new Font("Tahoma", Font.PLAIN, 14));
		//int[] w = fm.getWidths();
		
		textField_CitySelect.setEnabled(false);
		toolBar.add(textField_CitySelect);
		textField_SearchPhrase.setName("Поле поиска");
		textField_SearchPhrase.setMargin(new Insets(2, 4, 2, 2));
		toolBar.add(textField_SearchPhrase);
		button.setName("Найти");
		toolBar.add(button);
		panelVacancyList.add(toolBar, BorderLayout.SOUTH);
		add(panelVacancyList);

		
		panelVacancyDetailed.setLayout(new BoxLayout(panelVacancyDetailed, BoxLayout.X_AXIS));
		panelVacancyDetailed.setMinimumSize(new Dimension(260, 350));
		panelVacancyDetailed.setMaximumSize(new Dimension(260, 32767));
		panelVacancyDetailed.setPreferredSize(new Dimension(260, 350));
		editPane_VacancyDetail.setMargin(new Insets(3, 12, 3, 12));
		editPane_VacancyDetail.setContentType("text/html");
		editPane_VacancyDetail.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		editPane_VacancyDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelVacancyDetailed.add(editPane_VacancyDetail);
		add(panelVacancyDetailed);

		panelEmployerDetailed.setLayout(new BoxLayout(panelEmployerDetailed, BoxLayout.X_AXIS));	
		panelEmployerDetailed.setMinimumSize(new Dimension(240, 350));
		panelEmployerDetailed.setPreferredSize(new Dimension(240, 350));
		editPane_EmployerDetail.setMargin(new Insets(12, 12, 12, 12));
		editPane_EmployerDetail.setContentType("text/html");
		editPane_EmployerDetail.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		editPane_EmployerDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_Employer.setBorder(null);
		scrollPane_Employer.setViewportView(editPane_EmployerDetail); panelEmployerDetailed.add(scrollPane_Employer);
		add(panelEmployerDetailed);
		
		table_VacanciesList.addKeyListener(model.controller);
		table_VacanciesList.addMouseListener(model.controller);
		button.addKeyListener(model.controller);
		button.addMouseListener(model.controller);
		textField_SearchPhrase.addKeyListener(model.controller);

		table_VacanciesList.setModel(model.getVacanciesList(textField_SearchPhrase.getText()));
	}

}
