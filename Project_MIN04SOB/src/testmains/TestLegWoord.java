package testmains;

import java.util.ArrayList;

import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.models.Tile;

public class TestLegWoord {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameModel gm = new GameModel(0, null, null, false);
		BoardModel bOld = new BoardModel();
		BoardModel bNew = new BoardModel();
		Tile[][] oldboard = {
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
		for(int y = 0;y<15;y++){
			for(int x = 0;x<15;x++){
				bOld.setValueAt(oldboard[y][x], y, x);
			}
		}
		Tile[][] playerdLetters = {
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
		
		for(int y = 0;y<15;y++){
			for(int x = 0;x<15;x++){
				bNew.setValueAt(newBoard[y][x], y, x);
			}
		}
		gm.legWoord(playerdLetters, oldboard, newBoard);
		
	}

}
