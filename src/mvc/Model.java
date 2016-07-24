package mvc;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import comp.HeadHunterJsonModel;

public class Model implements ActionListener {
	public AbstractTableModel tableModel;
	public JTextField textField = new JTextField("ИТ");

	public Model(Controller controller) {
		tableModel=new HeadHunterJsonModel(textField.getText());
		textField.addActionListener(this);
		textField.setMargin(new Insets(2, 10, 2, 2));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((HeadHunterJsonModel) tableModel).setTable(textField.getText(), 0);
	}

}
