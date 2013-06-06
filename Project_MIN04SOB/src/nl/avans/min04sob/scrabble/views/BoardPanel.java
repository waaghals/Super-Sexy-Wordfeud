package nl.avans.min04sob.scrabble.views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.core.Role;
import nl.avans.min04sob.scrabble.core.mvc.CorePanel;
import nl.avans.min04sob.scrabble.misc.TileTable;
import nl.avans.min04sob.scrabble.misc.TileTransferHandler;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.Tile;

public class BoardPanel extends CorePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2899677237066103421L;
	private JButton resignButton;
	private JButton swapButton;
	private JButton passButton;
	private JButton play;
	private JButton nextButton;
	private JButton prevButton;

	private JTable playBoard;
	private JTable playerTilesField;

	private ActionListener resignActionListener;

	private final JLabel browseText;
	private final JLabel turnTextLabel;
	private final JLabel spelersLabel;

	private JLabel turnLabel;

	private JLabel opponentNameLabel;

	private JLabel opponentScoreLabel;

	private JLabel playerNameLabel;

	private JLabel playerScoreLabel;
	private DefaultTableModel playerStash;
	private JButton refreshButton;

	public BoardPanel() {
		playerStash = new DefaultTableModel(1, 8);
		setLayout(new MigLayout(
				"",
				"[75px:75px][75px:75px][100px:100px:100px][100px:100px:100px][100px:100px:100px][125px][100px][]",
				"[30px][30px][][:390px:390px][30px][30px][30px]"));
		/**
		 * 
		 * Main playing board
		 */

		playBoard = new TileTable();
		playBoard.setBorder(new LineBorder(new Color(0, 0, 0)));
		playBoard.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		playBoard.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playBoard.getTableHeader().setReorderingAllowed(false);
		playBoard.getTableHeader().setResizingAllowed(false);
		playBoard.setRowHeight(30);
		playBoard.setEnabled(true);
		playBoard.validate();

		spelersLabel = new JLabel("Spelers");
		add(spelersLabel, "cell 5 0,alignx left");

		add(playBoard, "cell 0 0 5 4");

		/**
		 * 
		 * Set the basic stuff such as layout
		 */

		/**
		 * 
		 * Player letter rack
		 */

		playerNameLabel = new JLabel("<player>");
		add(playerNameLabel, "cell 5 1,alignx right");

		playerScoreLabel = new JLabel("<score>");
		add(playerScoreLabel, "cell 6 1");

		opponentNameLabel = new JLabel("<opponent>");
		add(opponentNameLabel, "cell 5 2,alignx right");

		opponentScoreLabel = new JLabel("<score>");
		add(opponentScoreLabel, "cell 6 2");

		playerTilesField = new TileTable();
		playerTilesField.setModel(playerStash);
		playerTilesField.setBorder(new LineBorder(new Color(0, 0, 0)));
		playerTilesField.setCursor(Cursor
				.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		playerTilesField.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerTilesField.getTableHeader().setReorderingAllowed(false);
		playerTilesField.getTableHeader().setResizingAllowed(false);
		playerTilesField.setRowHeight(30);
		playerTilesField.setEnabled(true);
		playerTilesField.setCellSelectionEnabled(true);
		playerTilesField.validate();
		playerTilesField.setDefaultRenderer(Tile.class,
				new DefaultTableCellRenderer());

		add(playerTilesField, "cell 0 4 5 1,growx,aligny top");

		// if (!(isObserver)) {
		playBoard.setDragEnabled(true);
		playBoard.setDropMode(DropMode.USE_SELECTION);
		playBoard.setTransferHandler(new TileTransferHandler());

		playerTilesField.setDragEnabled(true);
		playerTilesField.setDropMode(DropMode.USE_SELECTION);
		playerTilesField.setTransferHandler(new TileTransferHandler());

		play = new JButton();
		play.setEnabled(false);
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		turnTextLabel = new JLabel("Beurt");
		add(turnTextLabel, "cell 5 4,alignx right");

		turnLabel = new JLabel("<player>");
		add(turnLabel, "cell 6 4");
		play.setText("Spelen");
		add(play, "flowx,cell 0 5 2 1,grow");

		swapButton = new JButton();
		swapButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		swapButton.setText("Wissel");
		add(swapButton, "cell 2 5,grow");

		passButton = new JButton();
		passButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		passButton.setText("Passen");
		add(passButton, "cell 3 5,grow");

		resignButton = new JButton();
		resignButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		resignButton.setText("Opgeven");
		add(resignButton, "cell 4 5,grow");

		nextButton = new JButton();
		nextButton.setFont(new Font("Dialog", Font.PLAIN, 12));

		prevButton = new JButton();
		prevButton.setFont(new Font("Dialog", Font.PLAIN, 12));

		refreshButton = new JButton("Refresh");
		add(refreshButton, "cell 1 6,grow");

		browseText = new JLabel("Bladeren");
		add(browseText, "cell 2 6,alignx right");
		prevButton.setText("Vorige");

		add(prevButton, "cell 3 6,grow");
		nextButton.setText("Volgende");

		// } else {

		add(nextButton, "cell 4 6,grow");

		// }\

	}

	public void atValue() {
		playerStash.setValueAt(new Tile("A", 12, Tile.MUTATABLE, 45), 0, 2);
	}

	public void addNextActionListener(ActionListener listener) {
		nextButton.addActionListener(listener);

	}

	public void addPreviousActionListener(ActionListener listener) {
		prevButton.addActionListener(listener);

	}

	public void addRefreshActionListener(ActionListener listener) {
		refreshButton.addActionListener(listener);
	}

	public void addResignActionListener(ActionListener listener) {
		resignActionListener = listener;
		resignButton.addActionListener(resignActionListener);
	}

	// swap button
	public void addSwapActionListener(ActionListener listener) {
		swapButton.addActionListener(listener);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		System.out.println("BoardPanel: " + evt.getPropertyName());
		switch (evt.getPropertyName()) {
		case Event.LOGIN:
			AccountModel account = (AccountModel) evt.getNewValue();
			if (account.isRole(Role.OBSERVER)) {

			}

			break;
		case Event.MOVE:
			boolean playerTurn = (boolean) evt.getNewValue();
			if (playerTurn) {
				play.setEnabled(true);
			} else {
				play.setEnabled(false);
			}
			break;
		}

	}

	public void setLabelPlayerTurn(String currTurnPlayerName) {
		this.turnLabel.setText(currTurnPlayerName);
	}

	public void setLabelScore(int currTurnScore) {
		this.playerScoreLabel.setText(Integer.toString(currTurnScore));
	}

	public void setLabelsNamesScores(String playerName, String playerScore,
			String opponentName, String opponentscore) {
		this.playerNameLabel.setText(playerName);
		this.playerScoreLabel.setText(playerScore);
		this.opponentNameLabel.setText(opponentName);
		this.opponentScoreLabel.setText(opponentscore);
	}

	public void setModel(BoardModel bpm) {
		playBoard.setModel(bpm);
	}

	public void setOpponent(String name) {
		opponentNameLabel.setText(name);
	}

	public void setPlayer(String name) {
		playerNameLabel.setText(name);
	}

	public void setPlayerTiles(Tile[] playerTiles) {
		for (int y = 0; playerTiles.length > y; y++) {
			playerStash.setValueAt(playerTiles[y], 0, y);
		}
	}

	public void setRenderer(TableCellRenderer renderer) {
		playBoard.setDefaultRenderer(Tile.class, renderer);
	}

	public void update() {

		this.repaint();
	}

}
