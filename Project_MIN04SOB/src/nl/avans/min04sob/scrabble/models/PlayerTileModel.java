package nl.avans.min04sob.scrabble.models;

import nl.avans.min04sob.scrabble.core.CoreTableModel;

public class PlayerTileModel extends CoreTableModel {
Tile[][] tileData;
	
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		data[rowIndex][columnIndex] = newValue;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public void setPlayerTileData(Tile[] newletters){
		for(int counter = 0; counter < 7; counter++){
			this.setValueAt(newletters[counter], 0, counter);
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex < 15 && columnIndex <15){
			return data[rowIndex][columnIndex];
			}
			return null;
		}
	
	public Tile[][] getTiles(){
		return tileData;
	}

}
