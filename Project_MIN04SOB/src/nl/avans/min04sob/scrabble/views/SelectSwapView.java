package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.models.Tile;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

public class SelectSwapView extends CorePanel {
	
	private Tile[][] letters;
	private Tile[] lettersArray;
	private JButton swapButton;
	private JList<Tile> list;
		
	public SelectSwapView(Tile[][] tl) {
		setLayout(new MigLayout("", "[200.00]", "[30px][150.00px][]"));
		
		letters = tl;
			
		JLabel lblSelecteerDeLetters = new JLabel("Selecteer de letters die je wilt wisselen");
		add(lblSelecteerDeLetters, "cell 0 0");
		
		list = new JList<Tile>();
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(list, "cell 0 1,grow");
		
		swapButton = new JButton("Wisselen");
		add(swapButton, "cell 0 2,alignx right");
		makeArray();
		fillList();
		
		}
	
	public void makeArray(){
		lettersArray = letters[0];
	}
	
	public void fillList(){
		list.setListData(lettersArray);
	}


	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

}
