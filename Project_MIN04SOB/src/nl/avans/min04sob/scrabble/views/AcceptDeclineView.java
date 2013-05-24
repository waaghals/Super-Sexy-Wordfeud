package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JList;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.models.GameModel;

public class AcceptDeclineView extends CorePanel{

	private JList wordList;
	private GameModel gameModel = new GameModel();
	private JFrame myFrame;
	
	public AcceptDeclineView()
	{
		setLayout(new MigLayout(
				"",
				"[100px:120px:120px,grow][100px:142.00px:100px,grow][100px:100px:100px]",
				"[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));
		wordList = new JList(gameModel.getRequestedWords());
		
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
