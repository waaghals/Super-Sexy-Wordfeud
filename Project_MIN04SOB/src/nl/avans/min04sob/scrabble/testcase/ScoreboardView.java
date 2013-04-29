package nl.avans.min04sob.scrabble.testcase;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.testcase.ScoreboardModel.Boardline;

public class ScoreboardView extends CoreWindow {

	JButton generatorButton;
	JTable table;
	JPanel panel;

	public ScoreboardView() {
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(250,200));
		generatorButton = new JButton();
		generatorButton.setText("Add random");
		add(generatorButton);
		add(panel);
	}

	public JButton getGeneratorButton() {
		return generatorButton;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "scoreboard":
			updateTable((ArrayList<Boardline>) evt.getNewValue());
			break;

		default:
			break;
		}
	}

	private void updateTable(ArrayList<Boardline> tableData) {
		table = new JTable(new ScoreboardTableModel(tableData));
		panel.removeAll();
		panel.add(table);
		panel.revalidate();
		revalidate();
	}
}
