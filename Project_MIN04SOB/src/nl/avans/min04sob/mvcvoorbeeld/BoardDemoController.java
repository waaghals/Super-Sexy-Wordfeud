package nl.avans.min04sob.mvcvoorbeeld;

import nl.avans.min04sob.scrabble.core.CoreConstraint;
import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;

public class BoardDemoController extends CoreController {

	public BoardDemoController(CoreWindow window) {
		BoardPanel playBoard = new BoardPanel();
		
		window.add(playBoard, new CoreConstraint(10, 10, 10, 0));
		window.pack();
		addView(playBoard);
	}
}
