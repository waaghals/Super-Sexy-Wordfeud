package nl.avans.min04sob.scrabble.models;

import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import nl.avans.min04sob.scrabble.core.Column;
import nl.avans.min04sob.scrabble.core.CoreTableModel;
import nl.avans.min04sob.scrabble.core.Query;

public class BoardModel extends CoreTableModel {
	Tile[][] tileData;
	Tile[][] playerTile;
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
		int boardX = 0;
		int boardY = 0;
		String query = "SELECT * FROM  `tegel` WHERE  `Bord_naam` =  'Standard'";
		try {
			ResultSet dbResult = new Query(query).select();
			
			while(dbResult.next()){
				int x = dbResult.getInt("X")-1;
				int y = dbResult.getInt("Y")-1;
				tilesHM.put(new Point(x, y),
						dbResult.getString("TegelType_soort"));
				if(x > boardX){
					boardX = x;
				}
				
				if(y > boardX){
					boardY = y;
				}
			}
			
			//Create a array the size of the board
			initDataArray(boardX +1, boardY+1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Point point : tilesHM.keySet()) {
			setValueAt(new Tile(tilesHM.get(point), 1), point.x, point.y);	//TODO set tile value
		}

		// System.out.println(tl.isEmpty());
		playerTile = new Tile[][] { { new Tile("A" ,1),
				new Tile("B", 2), new Tile("C", 2), new Tile("D", 2),
				new Tile("E", 2), new Tile("F", 2), new Tile("G", 2) } };
		
		/*
		tileData = new TileModel[][] {
				{
						// 1

						new TileTL(), new TileModel(""), new TileModel(""),
						new TileModel(""), new TileTW(), new TileModel(""),
						new TileModel(""), new TileDL(), new TileModel(""),
						new TileModel(""), new TileTW(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileTL() },
				{
						// 2

						new TileModel(""), new TileDL(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileTL(),
						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileTL(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileDL(),
						new TileModel(""), },

				{
						// 3

						new TileModel(""), new TileModel(""), new TileDW(),
						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileDL(), new TileModel(""),
						new TileDL(), new TileModel(""), new TileModel(""),
						new TileModel(""), new TileDW(), new TileModel(""),
						new TileModel(""), },

				{

						// 4

						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileTL(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileDW(),
						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileTL(), new TileModel(""),
						new TileModel(""), new TileModel(""), },

				{
						// 5

						new TileTW(), new TileModel(""), new TileModel(""),
						new TileModel(""), new TileDW(), new TileModel(""),
						new TileDL(), new TileModel(""), new TileDL(),
						new TileModel(""), new TileDW(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileTW(), },

				{
						// 6

						new TileModel(""), new TileTL(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileTL(),
						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileTL(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileTL(),
						new TileModel(""), },

				{
						// 7

						new TileModel(""), new TileModel(""), new TileDL(),
						new TileModel(""), new TileDL(), new TileModel(""),
						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileDL(),
						new TileModel(""), new TileDL(), new TileModel(""),
						new TileModel(""), },

				{
						// 8

						new TileDL(), new TileModel(""), new TileModel(""),
						new TileDW(), new TileModel(""), new TileModel(""),
						new TileModel(""), new TileStart(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileDW(),
						new TileModel(""), new TileModel(""), new TileDL(), },

				{
						// 9

						new TileModel(""), new TileModel(""), new TileDL(),
						new TileModel(""), new TileDL(), new TileModel(""),
						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileDL(),
						new TileModel(""), new TileDL(), new TileModel(""),
						new TileModel(""), },

				{
						// 10
						new TileModel(""), new TileTL(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileTL(),
						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileTL(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileTL(),
						new TileModel(""), },

				{
						// 11

						new TileTW(), new TileModel(""), new TileModel(""),
						new TileModel(""), new TileDW(), new TileModel(""),
						new TileDL(), new TileModel(""), new TileDL(),
						new TileModel(""), new TileDW(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileTW(), },
				{
						// 12

						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileTL(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileDW(),
						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileTL(), new TileModel(""),
						new TileModel(""), new TileModel(""), },
				{
						// 13

						new TileModel(""), new TileModel(""), new TileDW(),
						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileDL(), new TileModel(""),
						new TileDL(), new TileModel(""), new TileModel(""),
						new TileModel(""), new TileDW(), new TileModel(""),
						new TileModel(""), },
				{
						// 14

						new TileModel(""), new TileDL(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileTL(),
						new TileModel(""), new TileModel(""),
						new TileModel(""), new TileTL(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileDL(),
						new TileModel(""), },
				{
						// 15

						new TileTL(), new TileModel(""), new TileModel(""),
						new TileModel(""), new TileTW(), new TileModel(""),
						new TileModel(""), new TileDL(), new TileModel(""),
						new TileModel(""), new TileTW(), new TileModel(""),
						new TileModel(""), new TileModel(""), new TileTL() } };
						*/
	
		for (int i = 0; i <= boardY; i++) {
			String colName = (char)(i + 97)+ "";
			addColumn(new Column(colName, Tile.class, i));
		}
	}


	public String[][] getPlayerDataValues() {
		String[][] playerDataValues = new String[1][playerTile[0].length];
		for (int y = 0; playerTile[0].length > y; y++) {
			playerDataValues[0][y] = playerTile[0][y].getLetter();
		}
		return playerDataValues;
	}

	public void setPlayetTile(int x, Tile newtile) {
		playerTile[0][x] = newtile;
	}

	public Tile getPlayerTile(int x, int y) {
		return playerTile[y][x];
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//Tile tile = (Tile) getValueAt(rowIndex, columnIndex);
		//if(tile == null){
			return false;
		//}
		//return tile.isMutatable();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = newValue;
	}
}