package testmains;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.controllers.BoardController;

public class BoardMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		BoardController bp = new BoardController(false);
		jf.add(bp.getBpv());
		jf.setVisible(true);
		jf.pack();
	}

}
