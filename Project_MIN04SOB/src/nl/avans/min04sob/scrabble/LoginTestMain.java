package nl.avans.min04sob.scrabble;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.views.LoginPanel;

public class LoginTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new LoginPanel());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}
