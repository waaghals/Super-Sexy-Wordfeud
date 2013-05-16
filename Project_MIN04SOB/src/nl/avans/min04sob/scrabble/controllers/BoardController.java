package nl.avans.min04sob.scrabble.controllers;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.BoardPanelModel;
import nl.avans.min04sob.scrabble.models.TileModel;
import nl.avans.min04sob.scrabble.views.BoardPanelView;

public class BoardController extends CoreController{
private BoardPanelView bpv;
private BoardPanelModel bpm;
	public BoardController(){
		bpm = new BoardPanelModel();
		bpv = new BoardPanelView(bpm.getDataValues(),bpm.getPlayerDataValues());
		bpm.setTile(15, 14, new TileModel("het werkt"));
		this.updateBoard();
		System.out.println(bpv.gettabledata());
		
		bpm.setPlayetTile(3, new TileModel("P"));
		this.updatePlayerTiles();
	
		this.addView(bpv);
		
	}
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListeners() {
		// TODO Auto-generated method stub
		
	}
	public void updateBoard(){
		
		bpv.updatetable(bpm.getDataValues());
	}
	public void updatePlayerTiles(){
		bpv.updatePlayerTiles(bpm.getPlayerDataValues());
	}
	
	
	public BoardPanelView getBpv() {
		return bpv;
	}
	public void setBpv(BoardPanelView bpv) {
		this.bpv = bpv;
	}
	public BoardPanelModel getBpm() {
		return bpm;
	}
	public void setBpm(BoardPanelModel bpm) {
		this.bpm = bpm;
	}
	

}
