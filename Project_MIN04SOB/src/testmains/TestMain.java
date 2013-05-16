package testmains;

import javax.swing.SwingUtilities;

import nl.avans.min04sob.scrabble.controllers.MainController;



public class TestMain implements Runnable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new MainController()).start();
	}

	@Override
	public void run() {
		new MainController();
	}
}
