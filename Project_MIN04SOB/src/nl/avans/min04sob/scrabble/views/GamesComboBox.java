package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;


import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import javax.swing.event.ChangeListener;


import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.models.Role;

import javax.swing.JLabel;

public class GamesComboBox extends CorePanel {

	private JComboBox<GameModel> gameList;
	private JCheckBox observer;

	public GamesComboBox(){
		initialize();
}

	public void initialize() {

		setLayout(new MigLayout("", "[75px:100px:150px][75px:100px:150px][75px:100px:150px]",
				"[20px:30px:30px][20px:30px:30px]"));


		JLabel selectLabel = new JLabel("Selecteer een spel");
		add(selectLabel, "cell 0 0 2 1");
		gameList = new JComboBox<GameModel>();

		add(gameList, "cell 0 1 2 1,grow");
		gameList.setEnabled(false);
		observer = new JCheckBox();

	}

	public void addGameListListener(ActionListener listenener) {
		gameList.addActionListener(listenener);
	}

	public void addObserverCheckBoxListener(ChangeListener listenener) {

		observer.addChangeListener(listenener);

	}

	public void addGame(GameModel game) {
		if (game == null) {
			return;
		}
		gameList.addItem(game);
	}

	public void addGames(ArrayList<GameModel> arrayList) {
		if (arrayList == null) {
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
			gameList.setEnabled(true);
			if(account.isRole(Role.OBSERVER)){
				
				
				add(observer,"cell 0 2 3 1 ,grow");
				observer.setEnabled(true);
				observer.setText("bekijk een spel");
			}
			observer.setEnabled(true);
			break;
		case Event.LOGOUT:
			gameList.removeAll();
			gameList.setEnabled(false);

			observer.setEnabled(false);

			observer.setSelected(false);
			this.remove(observer);
			break;
		default:
			break;
		}

	}

	public boolean checkBoxIsSelected() {
		return observer.isSelected();
	}

	public GameModel getSelectedGame() {
		return (GameModel) gameList.getSelectedItem();
	}
}
