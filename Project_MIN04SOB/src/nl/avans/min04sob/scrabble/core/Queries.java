package nl.avans.min04sob.scrabble.core;

import java.io.InputStream;

@SuppressWarnings("unused")
public class Queries {
	private static final String CURRENT_LETTERS;
	private static final String ADDRESS_QUERY;
	private static final String AGE_QUERY;

	static {
		CURRENT_LETTERS = Queries.load("queries/currentLetters.sql");
		ADDRESS_QUERY = Queries.load("queries/addressQuery.sql");
		AGE_QUERY = Queries.load("queries/ageQuery.sql");
	}

	public static String load(final String path) {
		final InputStream stream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(path);

		return stream.toString();
		// return IOUtils.toString(stream);

	}
}
