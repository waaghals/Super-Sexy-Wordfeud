package nl.avans.min04sob.scrabble.models;

import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;

public class BoardModel extends CoreModel {
	TileModel[][] tileData;
	TileModel[][] playerTile;
	Point coordinates;
	HashMap<Point, String> tilesHM = new HashMap<Point, String>();

	public BoardModel() {
		coordinates = new Point();
		String query = "SELECT `X`, `Y`, `TegelType_soort` FROM `tegel` WHERE 'TegelType_soort' <> '--'";
		try {
			ResultSet dbResult = new Query(query).select();
			tilesHM.put(new Point(dbResult.getInt("X"), dbResult.getInt("y")),
					dbResult.getString("TegelType_soort"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// System.out.println(tl.isEmpty());
		playerTile = new TileModel[][] { { new TileModel("A"),
				new TileModel("B"), new TileModel("C"), new TileModel("D"),
				new TileModel("E"), new TileModel("F"), new TileModel("G") } };
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
	}

	public String[][] getDataValues() {
		String[][] dataValues = new String[tileData.length][tileData[1].length];
		for (int y = 0; tileData.length > y; y++) {
			for (int x = 0; tileData[y].length > x; x++) {
				dataValues[y][x] = tileData[y][x].getLetter();

			}
		}

		return dataValues;
	}

	public String[][] getPlayerDataValues() {
		String[][] playerDataValues = new String[1][playerTile[0].length];
		for (int y = 0; playerTile[0].length > y; y++) {
			playerDataValues[0][y] = playerTile[0][y].getLetter();
		}
		return playerDataValues;
	}

	public TileModel getTile(int x, int y) {
		return tileData[y][x];
	}

	public void setTile(int x, int y, TileModel newtile) {
		tileData[y][x] = newtile;

	}

	public void setPlayetTile(int x, TileModel newtile) {
		playerTile[0][x] = newtile;
	}

	public TileModel getPlayerTile(int x, int y) {
		return playerTile[y][x];
	}

	public int getMultiplier(Point coord) {
		if (tilesHM.containsKey(coord)) {
			switch (tilesHM.get(coord)) {
			case "DW":
				return 4; // 4 moet dus voor DW staan
			case "TW":
				return 5; // 5 moet dus voor TW staan
			case "DL":
				return 2;
			case "TL":
				return 3;
			default:
				return 1; // is de begintegel
			}
		} else {
			return 1; // gewoon vakje
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
}