package nl.avans.min04sob.scrabble.models;

import javax.xml.crypto.Data;

import nl.avans.min04sob.scrabble.core.CoreTableModel;

public class PlayerTileModel extends CoreTableModel {

	public PlayerTileModel(){
		initDataArray(1, 7);
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return true;
	}

	public void setsValueAt(Tile newValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		System.out.println(((Tile) newValue).getLetter());
		
		data[rowIndex][columnIndex] = newValue;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public void setPlayerTileData(Tile[] newletters){
		for(int counter = 0; counter < newletters.length; counter++){
			
			
			this.setsValueAt(newletters[counter], 0, counter);
			
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex < 15 && columnIndex <15){
			return data[rowIndex][columnIndex];
			}
			return null;
		}
	


	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

}
