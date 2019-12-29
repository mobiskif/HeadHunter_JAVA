package mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class View extends JPanel {
	private JPanel panelVacanciesList = new JPanel();
	private JPanel panelVacancyDetail = new JPanel();
	private JPanel panelEmployerDetail = new JPanel();
	private JScrollPane scrollPane_VacanciesList = new JScrollPane();
	private JScrollPane scrollPane_EmployerDetail = new JScrollPane();
	private JToolBar toolBar = new JToolBar();
	JTable table_VacanciesList = new JTable();
	JEditorPane editPane_VacancyDetail = new JEditorPane();
	JEditorPane editPane_EmployerDetail = new JEditorPane();
	JButton button = new JButton("Найти");
	JTextField textField_SearchPhrase = new JTextField("ИТ");
	JTextField textField_CitySelect = new JTextField("Санкт-Петербург");
	
	public View(Model model) {
		super();
		setBorder(null);
		model.setView(this);

		setPreferredSize(new Dimension(940, 375));
		setMinimumSize(new Dimension(940, 375));
		setPreferredSize(new Dimension(940, 375));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		panelVacanciesList.setBorder(null);
		panelVacanciesList.setMaximumSize(new Dimension(460, 32767));
		panelVacanciesList.setPreferredSize(new Dimension(460, 350));		
		panelVacanciesList.setMinimumSize(new Dimension(460, 350));
		panelVacanciesList.setLayout(new BorderLayout(0, 0));
		table_VacanciesList.setBorder(null);
		table_VacanciesList.setName("Таблица результатов");
		scrollPane_VacanciesList.setViewportBorder(null);
		scrollPane_VacanciesList.setBorder(null);
		scrollPane_VacanciesList.setViewportView(table_VacanciesList); 
		panelVacanciesList.add(scrollPane_VacanciesList, BorderLayout.CENTER);
		
		//FontMetrics fm = getFontMetrics(new Font("Tahoma", Font.PLAIN, 14));
		//int[] w = fm.getWidths();
		
		textField_CitySelect.setEnabled(false);
		toolBar.setBorder(null);
		toolBar.setBorderPainted(false);
		toolBar.setFloatable(false);
		toolBar.add(textField_CitySelect);
		textField_SearchPhrase.setName("Поле поиска");
		textField_SearchPhrase.setMargin(new Insets(2, 4, 2, 2));
		toolBar.add(textField_SearchPhrase);
		button.setName("Найти");
		toolBar.add(button);
		panelVacanciesList.add(toolBar, BorderLayout.SOUTH);
		add(panelVacanciesList);

		
		panelVacancyDetail.setLayout(new BoxLayout(panelVacancyDetail, BoxLayout.X_AXIS));
		panelVacancyDetail.setMinimumSize(new Dimension(260, 350));
		panelVacancyDetail.setMaximumSize(new Dimension(260, 32767));
		panelVacancyDetail.setPreferredSize(new Dimension(260, 350));
		editPane_VacancyDetail.setMargin(new Insets(3, 12, 3, 12));
		editPane_VacancyDetail.setContentType("text/html");
		editPane_VacancyDetail.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		editPane_VacancyDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelVacancyDetail.add(editPane_VacancyDetail);
		add(panelVacancyDetail);

		panelEmployerDetail.setLayout(new BoxLayout(panelEmployerDetail, BoxLayout.X_AXIS));	
		panelEmployerDetail.setMinimumSize(new Dimension(240, 350));
		panelEmployerDetail.setPreferredSize(new Dimension(240, 350));
		editPane_EmployerDetail.setMargin(new Insets(12, 12, 12, 12));
		editPane_EmployerDetail.setContentType("text/html");
		editPane_EmployerDetail.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		editPane_EmployerDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_EmployerDetail.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_EmployerDetail.setBorder(null);
		scrollPane_EmployerDetail.setViewportView(editPane_EmployerDetail); panelEmployerDetail.add(scrollPane_EmployerDetail);
		add(panelEmployerDetail);
		
		table_VacanciesList.addKeyListener(model.controller);
		table_VacanciesList.addMouseListener(model.controller);
		button.addKeyListener(model.controller);
		button.addMouseListener(model.controller);
		textField_SearchPhrase.addKeyListener(model.controller);

		table_VacanciesList.setModel(model.getVacanciesList(textField_SearchPhrase.getText()));
		table_VacanciesList.getColumnModel().getColumn(0).setMaxWidth(63);
		table_VacanciesList.getColumnModel().getColumn(1).setMaxWidth(70);
		table_VacanciesList.getColumnModel().getColumn(2).setMaxWidth(70);
		table_VacanciesList.getColumnModel().getColumn(3).setMinWidth(70);
		table_VacanciesList.getColumnModel().getColumn(4).setMinWidth(100);
		table_VacanciesList.getColumnModel().getColumn(4).setMaxWidth(100);
	}

}
