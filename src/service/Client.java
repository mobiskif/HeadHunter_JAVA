package service;

import javax.swing.JLabel;

public class Client implements Runnable {
	JLabel statusLabel;

	public Client(JLabel lbl) {
		statusLabel = lbl;
	}
	
	@Override
	public void run() {
		int i = 0;
		while (true) {
			statusLabel.setText(" Client: " + i++);
			try {Thread.sleep(1500);}
			catch (Exception e) {}
		}
	}
}
