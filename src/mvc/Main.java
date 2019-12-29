package mvc;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("API hh.ru (c) Parkhimovich 2016");
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

