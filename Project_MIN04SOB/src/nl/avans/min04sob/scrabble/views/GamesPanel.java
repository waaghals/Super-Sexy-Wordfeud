package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.models.GameModel;

public class GamesPanel extends CorePanel {

	private JList<GameModel> gameList;
	DefaultListModel<GameModel> listModel;  
;
	
	public GamesPanel(){
		initialize();
		addComponents();
	}
	
	public void initialize(){
		listModel = new DefaultListModel<GameModel>();  
		gameList = new JList<GameModel>(listModel);
		gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gameList.setLayoutOrientation(JList.VERTICAL);
		gameList.setVisibleRowCount(-1);
	}
	
	private void addComponents(){
		JScrollPane pane = new JScrollPane(gameList);
		pane.setPreferredSize(getPreferredSize());
		add(pane);
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
	
	public void addGames(GameModel[] games){
		if(games == null){
			return;
		}
		for (GameModel game : games) {
			listModel.addElement(game);
		}
	}
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "newGame":
			gameList.repaint();
			break;

		default:
			break;
		}

	}

	public GameModel getSelectedValue() {
		return gameList.getSelectedValue();
	}

}
