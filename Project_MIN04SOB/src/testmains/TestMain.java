package testmains;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import nl.avans.min04sob.scrabble.controllers.MainController;

public class TestMain implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		new Thread(new MainController()).start();
	}

	@Override
	public void run() {
		new MainController();
	}
}
