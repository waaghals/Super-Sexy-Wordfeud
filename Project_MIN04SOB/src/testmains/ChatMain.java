package testmains;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.controllers.ChatController;

public class ChatMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ChatController cc = new ChatController(1, 10);

		JFrame jf = new JFrame();
		jf.add(cc.getchatpanel());
		jf.setVisible(true);
		jf.pack();
	
	}

}
