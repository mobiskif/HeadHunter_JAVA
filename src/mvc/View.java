package mvc;

import javax.swing.JPanel;
import comp.JPanel_Info;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class View extends JPanel {
	JPanel_Info infoPanel = new JPanel_Info();

	public View(Model model) {
		setLayout(new BorderLayout(0, 0));
		infoPanel.setTableModel(model);
		this.add(infoPanel, BorderLayout.CENTER);
	}

}
