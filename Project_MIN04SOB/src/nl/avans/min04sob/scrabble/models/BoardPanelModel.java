package nl.avans.min04sob.scrabble.models;



import nl.avans.min04sob.scrabble.core.CoreModel;


public class BoardPanelModel extends CoreModel {
	TileModel[][] tileData;
	TileModel[][] playerTile;
public BoardPanelModel() {
		

		
		//System.out.println(tl.isEmpty());
		playerTile = new TileModel[][]{ {new TileModel("A"),new TileModel("B"),new TileModel("C"),
									new TileModel("D"),new TileModel("E"),new TileModel("F"),new TileModel("G")
		}				
		};
		 tileData = new TileModel[][] { 
				{ 
					// 1
					new TileModel("row1"),
					new TileTL(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileTW(), new TileModel(""),
					new TileModel(""), new TileDL(), new TileModel(""), 
					new TileModel(""), new TileTW(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileTL()
				},
				{
					//2
					new TileModel("row2"),
					new TileModel(""), new TileDL(), new TileModel(""),
					new TileModel(""), new TileModel(""), new TileTL(),
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileTL(), new TileModel(""), new TileModel(""), 
					new TileModel(""), new TileDL(), new TileModel(""), 
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
					new TileModel(""), new TileModel(""), new TileModel(""),
					new TileTL(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileDW(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileTL(), 
					new TileModel(""), new TileModel(""), new TileModel(""), 
				},
				
				{
					//5
					new TileModel("row5"),
					new TileTW(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileDW(), new TileModel(""),
					new TileDL(), new TileModel(""), new TileDL(), 
					new TileModel(""), new TileDW(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileTW(), 
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
					new TileModel(""), new TileDL(), new TileModel(""),
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileModel(""), new TileDL(), new TileModel(""), 
					new TileDL(), new TileModel(""), new TileModel(""), 
				},
				
				{
					//8 
					new TileModel("row8"),
					new TileDL(), new TileModel(""), new TileModel(""),
					new TileDW(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileStart(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileDW(), 
					new TileModel(""), new TileModel(""), new TileDL(), 
				},
				
				{
					//9
					new TileModel("row9"),
					new TileModel(""), new TileModel(""), new TileDL(),
					new TileModel(""), new TileDL(), new TileModel(""),
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileModel(""), new TileDL(), new TileModel(""), 
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
					new TileTW(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileDW(), new TileModel(""),
					new TileDL(), new TileModel(""), new TileDL(), 
					new TileModel(""), new TileDW(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileTW(), 
				},
				{
					//12
					new TileModel("row12"),
					new TileModel(""), new TileModel(""), new TileModel(""),
					new TileTL(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileDW(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileTL(), 
					new TileModel(""), new TileModel(""), new TileModel(""),
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
					new TileModel(""), new TileDL(), new TileModel(""),
					new TileModel(""), new TileModel(""), new TileTL(),
					new TileModel(""), new TileModel(""), new TileModel(""), 
					new TileTL(), new TileModel(""), new TileModel(""), 
					new TileModel(""), new TileDL(), new TileModel(""),   
				},
				{
					//15
					new TileModel("row15"),
					new TileTL(), new TileModel(""), new TileModel(""),
					new TileModel(""), new TileTW(), new TileModel(""),
					new TileModel(""), new TileDL(), new TileModel(""), 
					new TileModel(""), new TileTW(), new TileModel(""), 
					new TileModel(""), new TileModel(""), new TileTL()
				}		
		};
	}
	public String[][] getDataValues(){
		String[][] dataValues = new String[tileData.length][tileData[1].length];
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
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
}