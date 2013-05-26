package nl.avans.min04sob.scrabble.controllers;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.ScrabbleTableCellRenderer;
import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.Tile;
import nl.avans.min04sob.scrabble.views.BoardPanel;

public class BoardController extends CoreController{
private BoardPanel bpv;
private BoardModel bpm;
	public BoardController(){
		bpm = new BoardModel();
		bpv = new BoardPanel();
		//bpm.setTile(14, 14, new TileModel("het werkt"));
		this.updateBoard();
		
		bpm.setPlayetTile(3, new Tile("P", 3));
		this.updatePlayerTiles();
		bpv.setModel(bpm);
	
		this.addView(bpv);
		initialize();
		
	}
	@Override
	public void initialize() {
		ScrabbleTableCellRenderer renderer = new ScrabbleTableCellRenderer(bpm);
		bpv.setRenderer(renderer);
	}

	@Override
	public void addListeners() {
		// TODO Auto-generated method stub
		
	}
	public void updateBoard(){
		
		//bpv.updatetable(bpm.getDataValues());
	}
	public void updatePlayerTiles(){
	//	bpv.updatePlayerTiles(bpm.getPlayerDataValues());
	}
	
	
	public BoardPanel getBpv() {
		return bpv;
	}
	public void setBpv(BoardPanel bpv) {
		this.bpv = bpv;
	}
	public BoardModel getBpm() {
		return bpm;
	}
	public void setBpm(BoardModel bpm) {
		this.bpm = bpm;
	}
	

}
