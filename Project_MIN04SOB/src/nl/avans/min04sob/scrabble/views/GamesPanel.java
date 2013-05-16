package nl.avans.min04sob.scrabble.views;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.GameModel;

public class GamesPanel extends CorePanel {

	private JList<GameModel> gameList;
	DefaultListModel<GameModel> listModel;  
;
	
	public GamesPanel(){
		initialize();
		
	}
	
	public void initialize(){
		listModel = new DefaultListModel<GameModel>();  
		gameList = new JList<GameModel>(listModel);
		gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gameList.setLayoutOrientation(JList.VERTICAL);
		gameList.setVisibleRowCount(-1);
		
		setLayout(new MigLayout("", "[100px][100px]", "[100px][100px]"));
	
		JScrollPane pane = new JScrollPane(gameList);
		add(pane, "cell 0 0 2 2,grow");
	}
	
	public void addGameListListener(ListSelectionListener listenener){
		gameList.getSelectionModel().addListSelectionListener(listenener);
	}
	
	public void addGame(GameModel game){
		if(game == null){
			return;
		}
		listModel.addElement(game);
	}
	
	public void addGames(ArrayList<GameModel> arrayList){
		if(arrayList == null){
			return;
		}
		listModel.clear();
		for (GameModel game : arrayList) {
			listModel.addElement(game);
		}
	}
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "newGame":
			gameList.repaint();
			break;
		case "login":
			AccountModel account = (AccountModel) evt.getNewValue();
			addGames(account.getOpenGames());
			break;

		default:
			break;
		}

	}

	public GameModel getSelectedValue() {
		return gameList.getSelectedValue();
	}
}
