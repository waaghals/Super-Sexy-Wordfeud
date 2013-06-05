package nl.avans.min04sob.scrabble.models;

import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import nl.avans.min04sob.scrabble.core.db.Db;
import nl.avans.min04sob.scrabble.core.db.Query;
import nl.avans.min04sob.scrabble.core.mvc.CoreTableModel;
import nl.avans.min04sob.scrabble.misc.Column;

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
		try{
			return data[rowIndex][columnIndex];
		} catch (ArrayIndexOutOfBoundsException e){
			//Ugly, I know. Working with a deadline you known?
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
	
	public void removeMutatable(){		
		for (int x = 0; x < getColumnCount(); x++) {
			for (int y = 0; y < getRowCount(); y++) {
				Tile tile = (Tile) getValueAt(y, x);
				if(tile != null && tile.isMutatable()){
					setValueAt(null, y, x);
				}
			}
		}
	}
}