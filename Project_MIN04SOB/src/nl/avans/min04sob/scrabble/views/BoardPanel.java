package nl.avans.min04sob.scrabble.views;

import java.awt.Cursor;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import net.miginfocom.swing.MigLayout;
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

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		renderer.setVerticalAlignment(DefaultTableCellRenderer.CENTER);
		

		Object[][] playerTiles = new Character[][] { { 'K', 'Z', 'V', 'Q', 'N', 'V', 'Q' } };
		Character[] blaat = new Character[] { ' ', ' ', ' ', ' ', ' ' };
		
		// Create a new table instance
		JTable table = new JTable(dataValues, columnNames);
		table.setPreferredSize(new Dimension(0, 0));
		table.setPreferredScrollableViewportSize(new Dimension(0, 0));
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setRowHeight(30);

		/*for (Character character : columnNames) {
			table.getColumn(character).setCellRenderer(renderer);
		}*/
		setLayout(new MigLayout("", "[64px][5px][60px][5px][73px][5px][292px][430px]", "[475px][35px][30px][25px]"));

		// Add the table to a scrolling pane
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, "cell 0 0 7 1,grow");
		
		
		ChatPanel chat = new ChatPanel(150, 150);
		add(chat, "cell 7 0 1 3,grow");
		JTable playerTilesField = new JTable(playerTiles, blaat);
		playerTilesField.setRowHeight(30);
		playerTilesField.setCellSelectionEnabled(true);
		add(playerTilesField, "cell 0 2 7 1,growx,aligny top");
				
						JButton play = new JButton();
						play.setText("Play");
						add(play, "cell 0 3,alignx left,aligny center");
				
				JButton pass = new JButton();
				pass.setText("Pas");
				add(pass, "cell 2 3,grow");
				
				JButton swap = new JButton();
				swap.setText("Swap");
				add(swap, "cell 4 3,grow");
				
				JButton resign = new JButton();
				resign.setText("Resign");
				add(resign, "cell 6 3,alignx center,growy");


	}
}
