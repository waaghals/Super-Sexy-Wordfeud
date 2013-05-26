package nl.avans.min04sob.scrabble.views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.core.TileTable;
import nl.avans.min04sob.scrabble.core.TileTranfserHandler;
import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.Tile;

public class BoardPanel extends CorePanel {
	private JButton resign;
	private JButton swap;
	private JButton pass;
	private JButton play;
	private JButton volgende;
	private JButton vorige;
	
	private JTable playBoard;
	private JTable playerTilesField;
	


		public BoardPanel(boolean isObserver) {
			/**
			 * 
			 * Main playing board
			 */
		
			playBoard = new TileTable();
			playBoard.setBorder(new LineBorder(new Color(0, 0, 0)));
			playBoard.setPreferredSize(new Dimension(0, 0));
			playBoard.setPreferredScrollableViewportSize(new Dimension(0, 0));
			playBoard.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			playBoard.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			playBoard.setFillsViewportHeight(true);
			playBoard.getTableHeader().setReorderingAllowed(false);
			playBoard.getTableHeader().setResizingAllowed(false);
			playBoard.setRowHeight(30);
			playBoard.setEnabled(true);
			playBoard.validate();
			

			add(playBoard, "cell 0 0 6 1,grow");
			


			/**
			 * 
			 * Set the basic stuff such as layout
			 */
			
			setLayout(new MigLayout(
					"",
					"[75px:100px,grow][150px:n][5px][75px:100px][75px:100px][75px:100px]",
					"[450px][:30px:30px][25px]"));

		

			/**
			 * 
			 * Player letter rack
			 */

			playerTilesField = new JTable(1, 7);
			playerTilesField.setBorder(new LineBorder(new Color(0, 0, 0)));
			playerTilesField.setRowHeight(30);
			playerTilesField.setCellSelectionEnabled(true);


			add(playerTilesField, "cell 0 2 9 1,growx,aligny top");
			
			if(!(isObserver)){
				playBoard.setDragEnabled(true);
				playBoard.setDropMode(DropMode.USE_SELECTION);
				playBoard.setTransferHandler(new TileTranfserHandler());
			
				playerTilesField.setDragEnabled(true);
				playerTilesField.setDropMode(DropMode.USE_SELECTION);
				playerTilesField.setTransferHandler(new TileTranfserHandler());
			
			
			
					
					play = new JButton();
					play.setText("Play");
					add(play, "cell 1 3,alignx left,aligny center");
					
					pass = new JButton();
					pass.setText("Pas");
					add(pass, "cell 3 3,grow");
					
					swap = new JButton();
					swap.setText("Swap");
					add(swap, "cell 5 3,grow");
					
					resign = new JButton();
					resign.setText("Resign");
					add(resign, "cell 8 3,alignx center,growy");

			}else{
				volgende = new JButton();
				volgende.setText("volgende");
				add(volgende, "cell 3 3,grow");
				
				vorige = new JButton();
				vorige.setText("vorige");
				add(vorige, "alignx left,aligny center");
				
			}
		}
		public void addVolgendeActionListener(ActionListener listener){
			volgende.addActionListener(listener);
		}
		public void addVorigeActionListener(ActionListener listener){
			vorige.addActionListener(listener);
		}
	
		

	

	public void addResignActionListener(ActionListener listener) {
		resign.addActionListener(listener);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	public void setPlayerTiles(Tile[] playerTiles) {
		for (int y = 0; playerTiles.length > y + 1; y++) {
			playerTilesField.setValueAt(playerTiles[y], 0, y);
		}

	}

	public void setRenderer(TableCellRenderer renderer) {
		playBoard.setDefaultRenderer(Tile.class, renderer);
	}

	public void setModel(BoardModel bpm) {
		playBoard.setModel(bpm);
	}

}
