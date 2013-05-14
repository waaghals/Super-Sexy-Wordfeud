package nl.avans.min04sob.scrabble.views;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;

import nl.avans.min04sob.scrabble.core.CorePanel;

public class TileView {
private String letter;
private boolean empty = true;
private boolean start = false;
private boolean dubbelw = false;
private boolean tripplew = false;
private boolean dubbell = false;
private boolean tripplel =false;
	public TileView(String letter){
		switch (letter){
		case "": 
			this.empty =true;
			this.letter = "";
		break;
		case "dubbelw":
			this.dubbell = true;
			this.letter = "DW";
			this.empty =true;
		break;
		case "tripplew":
			this.tripplew = true;
			this.letter = "TW";
			this.empty =true;
			break;
		case "dubbell":
			this.dubbell = true;
			this.letter = "DL";
			this.empty =true;
			break;
		case "tripplel":
			this.tripplel = true;
			this.letter = "TL";
			this.empty =true;
		default:
			this.letter = letter;
			this.empty = false;
			this.empty =true;
			break;
		}	
		
        this.empty = false;    
    }
	
	
	public void configratie(){
	
		
	}
				
	

	public void modelPropertyChange(PropertyChangeEvent evt) {
		
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isDubbelw() {
		return dubbelw;
	}

	public void setDubbelw(boolean dubbelw) {
		this.dubbelw = dubbelw;
	}

	public boolean isTripplew() {
		return tripplew;
	}

	public void setTripplew(boolean tripplew) {
		this.tripplew = tripplew;
	}

	public boolean isDubbell() {
		return dubbell;
	}

	public void setDubbell(boolean dubbell) {
		this.dubbell = dubbell;
	}

	public boolean isTripplel() {
		return tripplel;
	}

	public void setTripplel(boolean tripplel) {
		this.tripplel = tripplel;
	}
	
	
	
	
	
}
