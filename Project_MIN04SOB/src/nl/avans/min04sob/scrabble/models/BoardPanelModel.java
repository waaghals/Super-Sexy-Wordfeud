package nl.avans.min04sob.scrabble.models;



import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;


public class BoardPanelModel extends CoreModel {
	TileModel[][] tileData;
	TileModel[][] playerTile;	
	HashMap<Point,String> tilesHM = new HashMap<Point,String>(); 
public BoardPanelModel() {
	
	try {
		ResultSet dbResult = Dbconnect
				.select("SELECT `X`, `Y`, `TegelType_soort` FROM `tegel` WHERE 'TegelType_soort' <> '--'" );
		while (dbResult.next()) {
            tilesHM.put(new Point(dbResult.getInt("X"),dbResult.getInt("y")),
            		dbResult.getString("TegelType_soort"));
        }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		//System.out.println(tl.isEmpty());
		playerTile = new TileModel[][]{ {new TileModel("T"),new TileModel("T"),new TileModel("T"),
									new TileModel("T"),new TileModel("T"),new TileModel("T")
		}		
		};
		 tileData = new TileModel[][] { 
				{ 
					// 1
					new TileModel("row1"),
					new TileTW(), new TileModel(""), new TileModel(""),
					new TileDL(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileTW(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileDL(), 
					new TileModel(""), new TileModel(""), new TileTW()
				},
				{
					//2
					new TileModel("row2"),
					new TileModel(""), new TileDW(), new TileModel(""),
					new TileModel(""), new TileModel(""), new TileTL(),
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileTL(), new TileModel(""), new TileModel(""), 
					new TileModel(""), new TileDW(), new TileModel(""), 
				},
				
				{
					//3
					new TileModel("row3"),
					new TileModel(""), new TileModel(""), new TileDW(),
					new TileModel(""), new TileModel(""), new TileModel(""),
					new TileDL(), new TileModel(""), new TileDL(), 
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileDW(), new TileModel(""), new TileModel(""), 
				},
				
				{
					
					//4
					new TileModel("row4"),
					new TileDL(), new TileModel(""), new TileModel(""),
					new TileDW(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileDL(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileDW(), 
					new TileModel(""), new TileModel(""), new TileDL(), 
				},
				
				{
					//5
					new TileModel("row5"),
					new TileModel(""), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileDW(), new TileModel(""),
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileModel(""), new TileDL(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileModel(""), 
				},
				
				{
					//6
					new TileModel("row6"),
					new TileModel(""), new TileTL(), new TileModel(""),
					new TileModel(""), new TileModel(""), new TileTL(),
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileTL(), new TileModel(""), new TileModel(""), 
					new TileModel(""), new TileTL(), new TileModel(""), 
				},
				
				{ 
					//7
					new TileModel("row7"),
					new TileModel(""), new TileModel(""), new TileDL(),
					new TileModel(""), new TileModel(""), new TileModel(""),
					new TileDL(), new TileModel(""), new TileDL(), 
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileDL(), new TileModel(""), new TileModel(""), 
				},
				
				{
					//8 
					new TileModel("row8"),
					new TileTW(), new TileModel(""), new TileModel(""),
					new TileDL(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileStart(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileDL(), 
					new TileModel(""), new TileModel(""), new TileTW(), 
				},
				
				{
					//9
					new TileModel("row9"),
					new TileModel(""), new TileModel(""), new TileDL(),
					new TileModel(""), new TileModel(""), new TileModel(""),
					new TileDL(), new TileModel(""), new TileDL(), 
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileDL(), new TileModel(""), new TileModel(""), 
				},
				
				{
					//10
					new TileModel("row10"),
					new TileModel(""), new TileTL(), new TileModel(""),
					new TileModel(""), new TileModel(""), new TileTL(),
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileTL(), new TileModel(""), new TileModel(""), 
					new TileModel(""), new TileTL(), new TileModel(""), 
				},
				
				{
					//11
					new TileModel("row11"),
					new TileModel(""), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileDW(), new TileModel(""),
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileModel(""), new TileDL(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileModel(""), 
				},
				
				{
					//12
					new TileModel("row12"),
					new TileDL(), new TileModel(""), new TileModel(""),
					new TileDW(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileDL(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileDW(), 
					new TileModel(""), new TileModel(""), new TileDL(), 
				},
				
				{
					//13
					new TileModel("row13"),
					new TileModel(""), new TileModel(""), new TileDW(),
					new TileModel(""), new TileModel(""), new TileModel(""),
					new TileDL(), new TileModel(""), new TileDL(), 
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileDW(), new TileModel(""), new TileModel(""),  
				},
				{
					//14
					new TileModel("row14"),
					new TileModel(""), new TileDW(), new TileModel(""),
					new TileModel(""), new TileModel(""), new TileTL(),
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileTL(), new TileModel(""), new TileModel(""), 
					new TileModel(""), new TileDW(), new TileModel(""),  
				},
				{
					//15
					new TileModel("row15"),
					new TileTW(), new TileModel(""), new TileModel(""),
					new TileDL(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileTW(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileDL(), 
					new TileModel(""), new TileModel(""), new TileTW() 
				}
				
				
		};
		 
		 
		 
		/*
		String dataValues[][] = {
				{ "", tl.getLetter(), tl.getLetter(), "", "DL", "", "", "", "TW", "", "", "", "",
						"", "", "TW" },
				{ "2", "", "DW", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "DW", "" },
				{ "3", "", "", "DW", "", "", "", "DL", "", "DL", "", "", "",
						"DW", "", "" },
				{ "4", "DL", "", "", "DW", "", "", "", "DL", "", "", "", "DW",
						"", "", "DL" },
				{ "5", "", "", "", "", "DW", "", "", "", "", "", "DW", "", "",
						"", "" },
				{ "6", "", "TL", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "TL", "" },
				{ "7", "", "", "DL", "", "", "", "DL", "", "DL", "", "", "",
						"DL", "", "" },
				{ "8", "TW", "", "", "DL", "", "", "", "*", "", "", "", "DL",
						"", "", "TW" },
				{ "9", "", "", "DL", "", "", "", "DL", "", "DL", "", "", "",
						"DL", "", "" },
				{ "10", "", "TL", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "TL", "" },
				{ "11", "", "", "", "", "DW", "", "", "", "", "", "DW", "", "",
						"", "" },
				{ "12", "DL", "", "", "DW", "", "", "", "DL", "", "", "", "DW",
						"", "", "DL" },
				{ "13", "", "", "DW", "", "", "", "DL", "", "DL", "", "", "",
						"DW", "", "" },
				{ "14", "", "DW", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "DW", "" },
				{ "15", "TW", "", "", "DL", "", "", "", "TW", "", "", "", "DL",
						"", "", "TW" } };
			*/

	}
	public String[][] getDataValues(){
		String[][] dataValues = new String[tileData.length+1][tileData[1].length+1];
		for(int y = 0;tileData.length > y; y++){
			for(int x = 0;tileData[y].length > x;x++){
				dataValues[y][x] = tileData[y][x].getLetter();
			
			}
		}
		return dataValues;
	}
	public String[][] getPlayerDataValues(){
		String[][] playerDataValues = new String[1][playerTile[0].length];
		for(int y = 0; playerTile[0].length > y; y++){
			playerDataValues[0][y] = playerTile[0][y].getLetter();
		}
		return playerDataValues;
	}
	public TileModel getTile(int x,int y){
		return tileData[y][x];
	}
	public void setTile(int x , int y,TileModel newtile){
		tileData[y][x] = newtile;
		
	}
	public void setPlayetTile(int x,TileModel newtile){
		playerTile[0][x] = newtile;
	}
	public TileModel getPlayerTile(int x,int y){
		return playerTile[y][x];
	}
	
	public void getMultiplier(Point coord){
		if(tilesHM.containsKey(coord)){
			switch(tilesHM.get(coord)){
			case "DW": 
			case "TW": 
			case "DL": //letter opvragen en *2
			case "TL": //letter opvragen en *3
			}
		}
		
	}







	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
