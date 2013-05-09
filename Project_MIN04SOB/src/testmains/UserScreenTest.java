package testmains;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.views.ChatandGamePanel;
import nl.avans.min04sob.scrabble.views.MainScreenUserVersion;

public class UserScreenTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainScreenUserVersion screen = new MainScreenUserVersion("aaron", 1, false);
		//ChatandGamePanel screen = new ChatandGamePanel(1);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(screen);
		frame.pack();
		frame.setVisible(true);
	}

}
