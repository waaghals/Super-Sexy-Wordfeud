package testmains;

import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.models.Tile;

public class TestLegWoord {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AccountModel am = new AccountModel();
		am.login("aaron", "aaron".toCharArray());
		
		BoardModel bOld = new BoardModel();
		GameModel gm = new GameModel(592, am, bOld, null, false);
		bOld.setBoardToDefault();
		Tile[][] oldboard = {
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
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
		};		
		
		//for(int y = 0;y<15;y++){
			//for(int x = 0;x<15;x++){
				//bOld.setValueAt(oldboard[y][x], y, x);
			//}
		//}
		Tile[][] playerdLetters = {
				{null,null,null,null,new Tile("b",4,false, 9),new Tile("i",2,false, 43),new Tile("e",1,false, 21),new Tile("r",2,false, 78),null,null,null,null,null,null,null},
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
				{null,null,null,null,new Tile("b",4,false, 9),new Tile("i",2,false, 43),new Tile("e",1,false, 21),new Tile("r",2,false, 78),null,null,null,null,null,null,null},
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
		
	//	for(int y = 0;y<15;y++){
		//	for(int x = 0;x<15;x++){
			//	bNew.setValueAt(newBoard[y][x], y, x);
			//}
	//	}
		gm.playWord(playerdLetters, newBoard);
		
	}

}
