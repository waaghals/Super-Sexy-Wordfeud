package nl.avans.min04sob.mvcvoorbeeld;

import javax.swing.JTabbedPane;

import nl.avans.min04sob.scrabble.core.CoreConstraint;
import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;

public class BoardDemoController extends CoreController {

	public BoardDemoController(CoreWindow window) {
		//BoardPanel playBoard = new BoardPanel();
		
		JTabbedPane testTabs = new JTabbedPane();
		testTabs.add("Game 1", new BoardPanel());
		testTabs.add("Game 2", new BoardPanel());
		testTabs.add("Game 3", new BoardPanel());
		
		window.add(testTabs, new CoreConstraint(10, 10, 10, 0));
		window.pack();
		//addView(playBoard);
	}
}