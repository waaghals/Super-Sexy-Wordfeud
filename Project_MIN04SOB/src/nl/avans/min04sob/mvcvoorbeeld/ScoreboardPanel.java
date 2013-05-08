package nl.avans.min04sob.mvcvoorbeeld;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nl.avans.min04sob.scrabble.core.CoreConstraint;
import nl.avans.min04sob.scrabble.core.CorePanel;

public class ScoreboardPanel extends CorePanel {

	JButton generatorButton;
	ScoreboardTableModel sbModel;
	JTable sbTable;
	JScrollPane pane;

	public ScoreboardPanel() {
		sbModel = new ScoreboardTableModel();
		sbTable = new JTable();
		pane = new JScrollPane(sbTable);

		add(pane, new CoreConstraint(10, 10, 0, 1));

		generatorButton = new JButton();
		generatorButton.setText("Add Score");
		add(generatorButton, new CoreConstraint(10, 1, 0, 11));
	}

	public void addGeneratorButtonActionListner(ActionListener listener) {
		generatorButton.addActionListener(listener);
	}

	public void setGeneratorButtonText(String text) {
		generatorButton.setText(text);
	}
	
	public void setScoreboardTableModel(ScoreboardTableModel model){
		sbTable.setModel(model);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "scoreboardtable":
			repaint();
			sbTable.revalidate();
			break;

		default:
			break;
		}
	}
}
