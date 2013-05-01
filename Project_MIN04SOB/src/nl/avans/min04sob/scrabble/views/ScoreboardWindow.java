package nl.avans.min04sob.scrabble.views;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.models.ScoreboardTableModel;
import nl.avans.min04sob.scrabble.models.ScoreboardModel.Boardline;

public class ScoreboardWindow extends CoreWindow {

	JButton generatorButton;
	//JPanel panel;

	public ScoreboardWindow() {
		//panel = new JPanel();
		//panel.setPreferredSize(new Dimension(250,200));
		generatorButton = new JButton();
		generatorButton.setText("Add random");
		add(generatorButton);
		//add(panel);
	}

	public JButton getGeneratorButton() {
		return generatorButton;
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "scoreboardtable":
			repaint();
			break;

		default:
			break;
		}
	}
}
