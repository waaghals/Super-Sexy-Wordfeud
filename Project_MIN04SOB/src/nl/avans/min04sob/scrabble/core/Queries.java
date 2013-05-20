package nl.avans.min04sob.scrabble.core;

import java.io.InputStream;

/*
 * 
 * Take from http://stackoverflow.com/a/4265371
 */
@SuppressWarnings("unused")
public class Queries {
	private static final String CURRENT_TILES;

	static {
		CURRENT_TILES = Queries.load("queries/currentTiles.sql");
	}

	public static String load(final String path) {
		final InputStream stream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(path);
		return stream.toString();
	}
}
