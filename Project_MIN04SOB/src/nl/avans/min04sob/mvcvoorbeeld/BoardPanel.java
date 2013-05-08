package nl.avans.min04sob.mvcvoorbeeld;

import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import nl.avans.min04sob.scrabble.core.CoreConstraint;
import nl.avans.min04sob.scrabble.core.CorePanel;

public class BoardPanel extends CorePanel {

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	public BoardPanel() {
		String dataValues[][] = {
				{ "1", "TW", "", "", "DL", "", "", "", "TW", "", "", "", "",
						"", "", "TW" },
				{ "2", "", "DW", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "DW", "" },
				{ "3", "", "", "DW", "", "", "", "DL", "", "DL", "", "", "",
						"DW", "", "" },
				{ "4", "DL", "", "", "DW", "", "", "", "DL", "", "", "", "DW",
						"", "", "DL" },
				{ "5", "", "", "", "", "DW", "", "", "", "", "", "DW", "", "",
						"", "" },
				{ "6", "", "TL", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "TL", "" },
				{ "7", "", "", "DL", "", "", "", "DL", "", "DL", "", "", "",
						"DL", "", "" },
				{ "8", "TW", "", "", "DL", "", "", "", "*", "", "", "", "DL",
						"", "", "TW" },
				{ "9", "", "", "DL", "", "", "", "DL", "", "DL", "", "", "",
						"DL", "", "" },
				{ "10", "", "TL", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "TL", "" },
				{ "11", "", "", "", "", "DW", "", "", "", "", "", "DW", "", "",
						"", "" },
				{ "12", "DL", "", "", "DW", "", "", "", "DL", "", "", "", "DW",
						"", "", "DL" },
				{ "13", "", "", "DW", "", "", "", "DL", "", "DL", "", "", "",
						"DW", "14", "" },
				{ "14", "", "DW", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "DW", "" },
				{ "15", "TW", "", "", "DL", "", "", "", "TW", "", "", "", "DL",
						"", "", "TW" } };

		Character columnNames[] = { ' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O' };

		// Create a new table instance
		JTable table = new JTable(dataValues, columnNames);

		// Add the table to a scrolling pane
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, new CoreConstraint(10, 10, 0, 0));
		
		JTextField textField = new JTextField();
		textField.setText("A B Q E H E F");
		add(textField, new CoreConstraint(8, 1, 0, 11));
		
		JButton button = new JButton();
		button.setText("Kut");
		add(button, new CoreConstraint(1, 2, 9, 11));
	}
}
