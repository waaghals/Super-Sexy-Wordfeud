package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.mvc.CorePanel;
import nl.avans.min04sob.scrabble.models.Tile;

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
	
	public void addListListener(MouseAdapter listener){
		list.addMouseListener(listener);
	}
	
	public void addButtonListener(ActionListener listener){
		swapButton.addActionListener(listener);
	}
	
	public void getSelectedIndexes(){
		list.getSelectedIndices();
	}
	
	public List<Tile> getSelectedTiles(){
		//return list.getSelectedValue();
		return list.getSelectedValuesList();
	}
	


	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

}
