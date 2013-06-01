package testmains;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import nl.avans.min04sob.scrabble.controllers.MainController;

public class TestMain implements Runnable {

	public static final ExecutorService executor = Executors.newFixedThreadPool(10);
	
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
		executor.submit(new MainController());
		//new Thread(new MainController()).start();
	
	}

	@Override
	public void run() {
		new MainController();
		executor.shutdown();
	}
}
