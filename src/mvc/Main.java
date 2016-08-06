package mvc;

import java.applet.Applet;
import javax.swing.JFrame;

import mvc.View;

@SuppressWarnings("serial")
public class Main extends Applet {

	public Main() {
		super();
		this.setBounds(0,0, 940, 350);
		this.setVisible(true);
		this.add(
				new View(
						new Model(
								new Controller()
						)
				)
		);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("\"API hh.ru\" (c) Parkhimovich 2016 (" + Runtime.getRuntime().availableProcessors() + " CPU)");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(200,200, 940, 350);
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