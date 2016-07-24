package service;

import javax.swing.JLabel;

public class Server implements Runnable {
	JLabel infoLabel;

	public Server(JLabel lbl) {
		infoLabel = lbl;
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			//System.out.println(this.getClass().getName());
			infoLabel.setText(" Server: " + i++);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}

		}
	}
}
