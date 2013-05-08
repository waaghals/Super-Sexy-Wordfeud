package nl.avans.min04sob.mvcvoorbeeld;


public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainWindow gameScreen = new MainWindow();
		new BoardDemoController(gameScreen);
		new ScoreboardController(gameScreen);
		new ChatControllerPATRICKVOORBEELD(gameScreen);
		gameScreen.pack();
	}
}
