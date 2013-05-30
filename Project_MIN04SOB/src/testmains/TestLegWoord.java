package testmains;

import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.models.Tile;

public class TestLegWoord {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameModel gm = new GameModel(0, null, null, false);
		Tile[][] board = {
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,new Tile("t",1,false),new Tile("h",0,false),new Tile("h",0,false),null,null,null,null,null,null},
				{null,null,null,null,null,null,new Tile("e",2,false),null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,new Tile("s",3,false),null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,new Tile("t",1,false),new Tile("h",0,false),new Tile("h",0,false),null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,new Tile("h",0,false),new Tile("h",0,false),null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
		};
		
		Tile[][] newBoard = {
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,new Tile("t",0,false),null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,new Tile("e",0,false),null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,new Tile("s",0,false),null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,new Tile("t",0,false),null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
		};
		gm.checkValidWord(newBoard, board);

	}

}
