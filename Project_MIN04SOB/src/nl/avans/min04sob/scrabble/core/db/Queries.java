package nl.avans.min04sob.scrabble.core.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * 
 * Take from http://stackoverflow.com/a/4265371
 */
public class Queries {
	public static final String CURRENT_TILES;
	public static final String RANKING;

	static {
		CURRENT_TILES = Queries.readFile("queries/currentTiles.sql");
		RANKING = Queries.readFile("queries/ranking.sql");
	}

	private static String readFile(final String file) {
		BufferedReader reader = null;
		try {
			
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
}
