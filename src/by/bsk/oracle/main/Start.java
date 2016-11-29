package by.bsk.oracle.main;

import java.awt.EventQueue;

import by.bsk.oracle.view.frame.LogInFrame;

public class Start {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrame frame = LogInFrame.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
