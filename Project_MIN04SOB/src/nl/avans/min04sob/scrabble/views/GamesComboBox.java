package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.GameModel;

public class GamesComboBox extends CorePanel {

	private JComboBox<GameModel> gameList;
	
	public GamesComboBox(){
		initialize();
		
	}
	
	public void initialize(){
		gameList = new JComboBox<GameModel>();
		
		setLayout(new MigLayout("", "[100px][100px]", "[100px][100px]"));
	
		JScrollPane pane = new JScrollPane(gameList);
		add(pane, "cell 0 0 2 2,grow");
	}
	
	public void addGameListListener(ActionListener listenener){
		gameList.addActionListener(listenener);
	}
	
	public void addGame(GameModel game){
		if(game == null){
			return;
		}
		gameList.addItem(game);
	}
	
	public void addGames(ArrayList<GameModel> arrayList){
		if(arrayList == null){
			return;
		}
		gameList.removeAll();
		for (GameModel game : arrayList) {
			gameList.addItem(game);
		}
	}
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case Event.NEWGAME:
			gameList.repaint();
			break;
		case Event.LOGIN:
			AccountModel account = (AccountModel) evt.getNewValue();
			addGames(account.getOpenGames());
			break;
		case Event.LOGOUT:
			gameList.removeAll();
			break;
		default:
			break;
		}

	}

	public GameModel getSelectedGame() {
		return (GameModel) gameList.getSelectedItem();
	}
}
