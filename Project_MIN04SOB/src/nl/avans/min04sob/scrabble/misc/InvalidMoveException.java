package nl.avans.min04sob.scrabble.misc;

import org.apache.commons.lang3.ArrayUtils;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception {
	public static final int NOT_ALIGNED = 1;
	public static final int NOT_CONNECTED = 2;
	public static final int NOT_ON_START = 3;
	public static final int NO_LETTERS_PUT = 4;

	@SuppressWarnings("unused")
	private int errorType;

	public InvalidMoveException(int error) {
		if (ArrayUtils.contains(new int[] { NOT_ALIGNED, NOT_CONNECTED,
				NOT_ON_START, NO_LETTERS_PUT }, error)) {
			errorType = error;
			return;
		}
		throw new IllegalArgumentException("Not a valid error value for InvalidMoveException");
	}
}
