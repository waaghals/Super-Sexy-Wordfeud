package nl.avans.min04sob.scrabble.models;

import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import nl.avans.min04sob.scrabble.core.Column;
import nl.avans.min04sob.scrabble.core.CoreTableModel;
import nl.avans.min04sob.scrabble.core.Db;
import nl.avans.min04sob.scrabble.core.Query;

public class BoardModel extends CoreTableModel {
	Tile[][] tileData;
	Point coordinates;
	HashMap<Point, String> tilesHM = new HashMap<Point, String>();

	public static final int DW = 1;
	public static final int TW = 2;
	public static final int DL = 3;
	public static final int TL = 4;
	public static final int STAR = 5;
	public static final int EMPTY = 6;

	public BoardModel() {
		coordinates = new Point();
		setBoardToDefault();

		for (Point point : tilesHM.keySet()) {
			// setValueAt(new Tile(tilesHM.get(point), 1), point.x, point.y);
			// //TODO set tile value
		}
		
	/*	setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  4, 0);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  4, 1);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  4, 2);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  4, 3);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  4, 4);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  4, 5);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  4, 6);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  4, 7);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  4, 8);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  1, 7);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  1, 7);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  2, 7);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  3, 7);
		setValueAt(new Tile("X", 5, Tile.NOT_MUTATABLE),  4, 7);
		
		setValueAt(new Tile("Q", 10, Tile.MUTATABLE),  5, 6);
		setValueAt(new Tile("Q", 10, Tile.NOT_MUTATABLE),  7, 7);
		setValueAt(new Tile("Q", 10, Tile.NOT_MUTATABLE),  7, 8);*/
		// System.out.println(tl.isEmpty());		
	}

	public int getMultiplier(Point coord) {
		if (tilesHM.containsKey(coord)) {
			switch (tilesHM.get(coord)) {
			case "DW":
				return DW;
			case "TW":
				return TW;
			case "DL":
				return DL;
			case "TL":
				return TL;
			case "*":
				return STAR;
			}
		}
		return EMPTY; // gewoon vakje
	}

	public Point getStartPoint() {
		// odd numbers of tile, meaning we have a center tile
		// The database is setup in a way that a star tile doesn't need to be in
		// the
		// Center tile, so check it from the database
		int width = getData().length;
		int height = getData()[0].length;
		Point coord = null;

		if (height % 2 == 1 && width % 2 == 1) {
			coord = new Point((height / 2) + 1, (width / 2) + 1);
			if (getMultiplier(coord) == BoardModel.STAR) {
				return coord;
			}
		}

		// Take the hard approach and find the start tile manually
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				coord = new Point(i, j);
				if (getMultiplier(coord) == BoardModel.STAR) {
					return coord;
				}
			}
		}

		return coord;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex < 15 && columnIndex <15){
		return data[rowIndex][columnIndex];
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// Tile tile = (Tile) getValueAt(rowIndex, columnIndex);
		// if(tile == null){
		return false;
		// }
		// return tile.isMutatable();
	}
	public void setBoardToDefault(){
		int boardX = 0;
		int boardY = 0;
		String query = "SELECT * FROM  `tegel` WHERE  `Bord_naam` =  'Standard'";
		try {
			Future<ResultSet> worker = Db.run(new Query(query));
			ResultSet rs = worker.get();

			while (rs.next()) {
				int x = rs.getInt("X") - 1;
				int y = rs.getInt("Y") - 1;
				tilesHM.put(new Point(x, y),
						rs.getString("TegelType_soort"));

				if (x > boardX) {
					boardX = x;
				}

				if (y > boardX) {
					boardY = y;
				}
			}

			// Create a array the size of the board
			initDataArray(boardX + 1, boardY + 1);

		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		for (int i = 0; i <= boardY; i++) {
			String colName = (char) (i + 97) + "";
			addColumn(new Column(colName, Tile.class, i));
		}
	}

	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = newValue;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}