package nl.avans.min04sob.scrabble.misc;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class MatrixUtils {

	/**
	 * Remove all surrounding null values form a matrix
	 * 
	 * @param matrix
	 * @return Object[][] the resulting cropped matrix
	 */
	public static Object[][] crop(Object[][] matrix) {
		ArrayList<Object> nominees = new ArrayList<Object>();

		// Remove empty rows
		for (int row = 0; row < matrix.length; row++) {
			if (isEmpty(matrix[row])) {
				nominees.add(matrix[row]);

			}
		}

		// Remove the nominees
		for (Object row : nominees) {
			matrix = ArrayUtils.removeElement(matrix, row);
		}
		return matrix;
	}

	/**
	 * Returns an array of Points where letter is not null
	 * 
	 * @param matrix
	 * @return Point[] a list of not null coordinates in the matrix
	 */
	public static Point[] getCoordinates(Object[][] matrix) {
		System.out.println(Arrays.deepToString(matrix));
		// matrix = crop(matrix);
		ArrayList<Point> coords = new ArrayList<Point>();
		for (int col = 0; col < matrix[0].length; col++) {
			for (int row = 0; row < matrix.length; row++) {
				if (matrix[row][col] != null) {
					coords.add(new Point(row, col));
				}
			}
		}
		return coords.toArray(new Point[coords.size()]);
	}

	/**
	 * Check if the matrix had all the elements XORed elements in a straight
	 * line
	 * 
	 * @param matrix
	 *            The XORed object matrix
	 * @return is numRow or numCols equals 1
	 */
	public static Dimension getDimension(Object[][] matrix) {
		int width = matrix.length;
		int hight = matrix[0].length;

		return new Dimension(width, hight);
	}

	public static boolean isAligned(Dimension size) {
		return size.getHeight() == 1 || size.getWidth() == 1;
	}

	/**
	 * Check if all items in an array are null
	 * 
	 * @param arr
	 * @return boolean if array only has null values
	 */
	public static boolean isEmpty(Object[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Performs a XOR on both the matrices, <- is that a word?
	 * 
	 * @param oldMatrix
	 * @param newMatrix
	 * @return Object[][] resulted XORed matrix other fields will be null
	 */
	public static Object[][] xor(Object[][] oldMatrix, Object[][] newMatrix) {
		int cols = newMatrix[0].length;
		int rows = newMatrix.length;
		Object[][] xorMatrix = new Object[rows][cols];
		try {
			for (int col = 0; col < cols; col++) {
				for (int row = 0; row < rows; row++) {

					Object field = null;
					// Row index exists in both matrices
					if (row < oldMatrix.length && row < newMatrix.length) {

						// Col index exists in both matrices
						if (col < oldMatrix[0].length
								&& col < newMatrix[0].length) {
							if (!oldMatrix[row][col]
									.equals(newMatrix[row][col])) {
								field = newMatrix[row][col];
							}

						} else {
							field = newMatrix[row][col];

						}

						// X and Y for the new matrix fall in the range
					} else if (row <= newMatrix.length
							&& col <= newMatrix[0].length) {
						field = newMatrix[row][col];

					}

					xorMatrix[row][col] = field;
				}
			}
		} catch (NullPointerException n) {
		}

		return xorMatrix;
	}
}
