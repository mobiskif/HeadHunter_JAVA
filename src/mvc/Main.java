package mvc;

import java.applet.Applet;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends Applet {

	public Main() {
		super();
		this.add(
				new View(
						new Model(
								new Controller()
						)
				)
		);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("\"HH.ru\" (c) Parkhimovich 2016 (" + Runtime.getRuntime().availableProcessors() + " CPU)");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(200,200, 800, 400);
		f.setVisible(true);
		f.add(
				new View(
						new Model(
								new Controller()
						)
				)
		);
		f.pack();
	}
}