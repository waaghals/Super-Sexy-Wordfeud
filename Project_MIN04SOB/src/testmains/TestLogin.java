package testmains;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.views.ChangePassPanel;

public class TestLogin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new ChangePassPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
