package testmains;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.controllers.BoardController;
import nl.avans.min04sob.scrabble.views.BoardPanel;
import nl.avans.min04sob.scrabble.views.BoardPanelView;

public class BoardMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		BoardController bp = new BoardController();
		jf.add(bp.getBpv());
		jf.setVisible(true);
		jf.pack();
	}

}
