package testmains;

import java.util.ArrayList;

import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.models.Tile;

public class TestLegWoord {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameModel gm = new GameModel(0, null, null, false);
		Tile[][] board = {
				{null,null,null,null,new Tile("b",0,false),new Tile("i",0,false),new Tile("e",0,false),new Tile("r",0,false),null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
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
		
		Tile[][] newBoard = {
				{null,null,null,null,new Tile("b",0,false),new Tile("i",0,false),new Tile("e",0,false),new Tile("r",0,false),null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
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
