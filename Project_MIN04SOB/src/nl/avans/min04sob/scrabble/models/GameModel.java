package nl.avans.min04sob.scrabble.models;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import nl.avans.min04sob.scrabble.controllers.BoardController;
import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.MatrixUtils;
import nl.avans.min04sob.scrabble.core.Query;

public class GameModel extends CoreModel {

	private CompetitionModel competition;
	private AccountModel opponent;
	private AccountModel challenger;
	private AccountModel currentUser;
	private int gameId;
	private String state;
	private String boardName;
	private String letterSet;
	private boolean iamchallenger;
	private int currentobserveturn;

	private StashModel stash = new StashModel();

	// private BoardController boardcontroller;
	private BoardModel boardModel;

	@Deprecated
	private String[][] boardData;
	private int lastTurn;

	public static final String STATE_FINISHED = "Finished";
	public static final String STATE_PLAYING = "Playing";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_RESIGNED = "Resigned";

	private final String getGameQuery = "SELECT * FROM `spel` WHERE `ID` = ?";
	private final String getOpenQuery = "SELECT * FROM `gelegdeletter` WHERE Tegel_Y =? AND Tegel_X = ? AND Letter_Spel_ID = ?";
	private final String getScoreQuery = "SELECT `totaalscore` FROM `score` WHERE `Spel_ID` = ? AND `Account_Naam` != ?";
	private final String getTurnQuery = "SELECT LetterType_karakter, Tegel_X, Tegel_Y, BlancoLetterKarakter, beurt_ID FROM gelegdeletter, letter WHERE gelegdeletter.Letter_Spel_ID = ? AND gelegdeletter.Letter_ID = letter.ID AND gelegdeletter.beurt_ID = ? ORDER BY beurt_ID ASC;;";
	private final String getBoardQuery = "select 	`gl`.`Letter_Spel_ID`,	`gl`.`Beurt_ID` ,	 `l`.`LetterType_karakter`	 ,`gl`.`Tegel_X`,	 `gl`.`Tegel_Y`	  , `gl`.`BlancoLetterKarakter` from `gelegdeletter` AS `gl` join `letter` AS `l` on ((`l`.`Spel_ID` = `gl`.`Letter_Spel_ID`) and (`l`.`ID` = `gl`.`Letter_ID`) )JOIN `spel` `s`ON `s`.`id` = `gl`.`Letter_Spel_ID`JOIN `letterset` AS `ls` ON `ls`.`code` = `s`.`LetterSet_naam` where gl.letter_Spel_ID = ?";

	private final String getPlayerTiles = "SELECT Beurt_ID,inhoud FROM plankje WHERE Spel_ID = ? AND Account_naam = ? ORDER BY Beurt_ID DESC ";

	private final String getTileValue = "Select waarde FROM lettertype WHERE karakter = ? AND LetterSet_code = ?";

	private final String yourTurnQuery = "SELECT `account_naam`, MAX(`id`) FROM `beurt` WHERE `spel_id` = ? GROUP BY `spel_id` ORDER BY `id`";
	private final String whosTurnAtTurn = "SELECT account_naam FROM `beurt` WHERE `spel_id` = ? AND ID = ?";
	private final String resignQuery = "UPDATE `spel` SET `Toestand_type` = ? WHERE `ID` = ?";
	private final String scoreQuery = "SELECT ID , score FROM beurt WHERE score IS NOT NULL AND score != 0 AND Account_naam = ?";
	private final String getnumberofturns = "SELECT max(beurt_ID) FROM gelegdeletter, letter WHERE gelegdeletter.Letter_Spel_ID = ? AND gelegdeletter.Letter_ID = letter.ID ";
	private final boolean observer;

	public GameModel(int gameId, AccountModel user, BoardModel boardModel,
			boolean observer) {
		this.observer = observer;
		this.boardModel = boardModel;
		currentUser = user;

		try {

			ResultSet dbResult = new Query(getGameQuery).set(gameId).select();
			int numRows = Query.getNumRows(dbResult);
			if (numRows == 1) {
				dbResult.next();
				this.gameId = gameId;
				currentobserveturn = getNumberOfTotalTurns();
				competition = new CompetitionModel(
						dbResult.getInt("competitie_id"));
				state = dbResult.getString("toestand_type");
				String challengerName = dbResult
						.getString("account_naam_uitdager");

				String challengeeName = dbResult
						.getString("account_naam_tegenstander");

				boardName = dbResult.getString(9);
				letterSet = dbResult.getString(10);
				if (!(observer)) {
					if (challengerName.equals(currentUser.getUsername())) {
						opponent = new AccountModel(challengeeName, false);
						challenger = currentUser;
						iamchallenger = true;
					} else {
						opponent = new AccountModel(challengerName, false);
						challenger = opponent;
						iamchallenger = false;
					}
				} else {
					opponent = new AccountModel(challengeeName, true);
					challenger = new AccountModel(challengerName, true);
					iamchallenger = false;

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isFree(int x, int y) {
		try {
			ResultSet rs = new Query(getOpenQuery).set(y + 1).set(x)
					.set(gameId).select();
			if (Query.getNumRows(rs) == 0) {
				return true;
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return false;
	}

	public String[][] compareArrays(String[][] bord, String[][] database) {
		String[][] returns = new String[7][3];
		int counter = 0;
		for (int y = 0; y < 15; y++) {
			for (int x = 0; x < 15; x++) {
				if (bord[y][x].equals(database[y][x])) {
				} else {
					returns[counter][0] = bord[y][x];
					returns[counter][1] = new String(x + "");
					returns[counter][2] = new String(y + "");
				}
			}
		}
		return returns;
	}

	public Tile[] getTiles() {

		return null;
	}

	/*
	 * TODO public void playWord(HashMap<Point, Tile> tiles) { String[][]
	 * Bnaam_uitdagers; String challengeeName =
	 * dbResult.getString("account_naam_tegenstander"); oardcurrent = new
	 * String[boardcontroller.getBpm().tileData.length][boardcontroller
	 * .getBpm().tileData[1].length]; for (int y = 0;
	 * boardcontroller.getBpm().tileData.length > y; y++) { for (int x = 0;
	 * boardcontroller.getBpm().tileData[y].length > x; x++) {
	 * Boardcurrent[y][x] = boardcontroller.getBpm().tileData[y][x]
	 * .getLetter();
	 * 
	 * } }
	 * 
	 * String[][] compared = compareArrays(compared, Boardcurrent); String
	 * oldnumberx = compared[0][1]; boolean verticalLine = true; for (String[] s
	 * : compared) { if (!oldnumberx.equals(s[1])) {
	 * 
	 * verticalLine = false; } } String oldnumbery = compared[0][2]; boolean
	 * horizontalLine = true; for (String[] s : compared) { if
	 * (!oldnumberx.equals(s[1])) {
	 * 
	 * horizontalLine = false; } } }
	 */
	@Override
	public void update() {
		// TODO fire property change for new games and changed game states
		// TODO also fire property change for a when the player needs to make a
		// new move,
		// and only update the board when the opponent actually plays a word.
	}

	public String toString() {
		// return gameId + "";
		return "(" + gameId + ") " + competition.getDesc() + " - "
				+ opponent.getUsername();
	}

	public CompetitionModel getCompetition() {
		return competition;
	}

	public AccountModel getOpponent() {
		return opponent;
	}

	public int getGameId() {
		return gameId;
	}

	@Deprecated
	public String[][] getboardData() {
		return this.boardData;
	}

	public String getState() {
		return state;
	}

	public String getBoardName() {
		return boardName;
	}

	public String getLetterSet() {
		return letterSet;
	}

	public AccountModel getChallenger() {
		return challenger;
	}

	public int getScore(String userName) {
		try {
			ResultSet rs = new Query(getScoreQuery).set(gameId).set(userName)
					.select();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void getlastrunFromDatabase() {
		try {
			ResultSet rs = new Query(getTurnQuery).set(gameId).set(lastTurn)
					.select();
			while (rs.next()) {
				int x = rs.getInt(2) - 1;// x
				int y = rs.getInt(3) - 1;// y
				lastTurn = rs.getInt(5);
				if (rs.getString(1).equals("?")) {
					boardData[y][x] = rs.getString(4);
				} else {
					boardData[y][x] = rs.getString(1);
				}
			}

		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public void requestWord(String word) {
		String status = "pending";

		String query = "INSERT INTO `woordenboek` (`woord`, `status`) VALUES (?, ?)";
		try {
			new Query(query).set(word).set(status).exec();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public String[] getRequestedWords() {
		String[] words = null;
		String query = "SELECT `woord` FROM `nieuwwoord` WHERE `status` = `pending`";
		try {
			ResultSet res = new Query(query).select();
			int numRows = Query.getNumRows(res);

			words = new String[numRows];
			int i = 0;
			while (res.next()) {
				words[i] = res.getString(1);
				i++;
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}

		return words;
	}

	private void test() {
		Integer[][] matrix = new Integer[][] {
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) } };

		Integer[][] matrix2 = new Integer[][] {
				{ new Integer(1), new Integer(99), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(99), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(1) },
				{ new Integer(1), new Integer(1), new Integer(1),
						new Integer(1), new Integer(99) } };

		Object[][] newMatrix = MatrixUtils.xor(matrix, matrix2);
		System.out.println(Arrays.deepToString(newMatrix));
		System.out.println();
		newMatrix = MatrixUtils.crop(newMatrix);
		System.out.println();
		System.out.println(Arrays.deepToString(newMatrix));
	}


	public static void main2(String[] args) {
		System.out.println("Yo sjaak, je runned de verkeerde main ;)");
		new GameModel(0, null,null,false).test();
	}

	public void checkValidMove(BoardModel oldBoard, BoardModel newBoard)
			throws InvalidMoveException {

		Tile[][] oldData = (Tile[][]) oldBoard.getData();
		Tile[][] newData = (Tile[][]) newBoard.getData();

		// First find out which letters where played
		Tile[][] playedLetters = (Tile[][]) MatrixUtils.xor(oldData, newData);
		Point[] letterPositions = MatrixUtils.getCoordinates(playedLetters);

		if (yourturn()) {
			boolean onStar = false;
			Point starCoord = oldBoard.getStartPoint();

			// Coords for all currently played letters

			for (Point letterPos : letterPositions) {
				if (starCoord.equals(letterPos)) {
					onStar = true;
					break;
				}
			}

			if (!onStar) {
				throw new InvalidMoveException(
						InvalidMoveException.NOT_ON_START);
			}
		}

		if (!MatrixUtils.isEmpty(playedLetters)) {
			throw new InvalidMoveException(InvalidMoveException.NO_LETTERS_PUT);
		}

		playedLetters = (Tile[][]) MatrixUtils.crop(playedLetters);

		Dimension playedWordSize = MatrixUtils.getDimension(playedLetters);
		if (!MatrixUtils.isAligned(playedWordSize)) {
			throw new InvalidMoveException(InvalidMoveException.NOT_ALIGNED);
		}

		double max = -1;
		if (playedWordSize.getHeight() == 1) {

			// Check horizontally connected
			for (Point letterPos : letterPositions) {
				if (max != letterPos.getY() - 1 && max != -1) {
					throw new InvalidMoveException(
							InvalidMoveException.NOT_CONNECTED);
				}
				max = letterPos.getY();
			}
		} else {
			// Check vertically connected
			for (Point letterPos : letterPositions) {
				if (max != letterPos.getX() - 1 && max != -1) {
					throw new InvalidMoveException(
							InvalidMoveException.NOT_CONNECTED);
				}
				max = letterPos.getX();
			}
		}

		// Everything went better than expected.jpg :)
	}

	public BoardModel getBoardModel() {
		return boardModel;
	}

	public boolean yourturn() {

		// String query =
		// "SELECT `woord` FROM `nieuwwoord` WHERE `status` = `pending`";
		// String query =
		// "SELECT `account_naam`, MAX(`id`) FROM `beurt` WHERE `spel_id` = ? GROUP BY `spel_id` ORDER BY `id`";
		try {
			ResultSet res = new Query(yourTurnQuery).set(getGameId()).select();

			int turnCount = Query.getNumRows(res);

			// If it is the first turn
			if (turnCount == 0) {
				// If the currentUser is the challenger return true else false
				return iamchallenger;
			}
			res.next();
			String lastturnplayername = res.getString("account_naam");

			// Get the last turn made
			// The challenger makes the first move

			if (lastturnplayername.equals(currentUser.getUsername())) {
				// If he is challenger
				if (iamchallenger) {
					return false;
				}
				return true;
			} else if (iamchallenger) {
				return true;

			}
			return false;

		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return (Boolean) null;

	}

	public boolean whosturn() {
		// dont use unless observing
		// use yourturn instead
		if (observer) {
			ResultSet res;
			try {
				res = new Query(whosTurnAtTurn).set(getGameId()).set(currentobserveturn).select();
				

				res.next();
				String lastturnplayername = res.getString("account_naam");
				
				if (lastturnplayername.equals(challenger.getUsername())) {
					return false;
				} else {
					return true;
				}
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return (Boolean) null;

	}
	public boolean isObserver(){
		return observer;
	}

	public String score(int toTurn) {
		String score = "";

		try {

			int ch = scorecounter(
					new Query(scoreQuery).set(challenger.getUsername())
							.select(), toTurn);

			int op = scorecounter(
					new Query(scoreQuery).set(opponent.getUsername()).select(),
					toTurn);

			score = ": " + Integer.toString(ch) + " points";

			score += " , : " + Integer.toString(op) + " points";

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return score;

	}

	private int scorecounter(ResultSet s, int toTurn) {
		int counter = 0;
		try {
			while (s.next()) {
				if (s.getInt(1) < toTurn + 1) {

					counter += s.getInt(2);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}

	public int getCurrentValueForThisTurn() {

		// Tile[][] oldData = (Tile[][]) boardModel.getData();
		// Tile[][] newData = (Tile[][]) getBoardFromDatabase();

		// First find out which letters where played
		// Tile[][] playedLetters = (Tile[][]) MatrixUtils.xor(oldData,
		// newData);
		// Point[] letterPositions = MatrixUtils.getCoordinates(playedLetters);
		// TODO deze methode maken
		// kan pas als woordleggen werkt
		return 0;
	}

	public void getBoardFromDatabase() {
		try {
			ResultSet rs = new Query(getBoardQuery).set(gameId).select();
			while (rs.next()) {
				int x = rs.getInt(4) - 1;// x
				int y = rs.getInt(5) - 1;// y
				if (x > -1 && y > -1) {
					if (rs.getString(3).equals("?")) {
						ResultSet tilewaarde = new Query(getTileValue)
								.set(rs.getString(6)).set(letterSet).select();
						tilewaarde.next();
						boardModel
								.setValueAt(new Tile(rs.getString(6),
										tilewaarde.getInt(1),
										Tile.NOT_MUTATABLE), y, x);

					} else {
						// TODO add letter value in query
						ResultSet tilewaarde = new Query(getTileValue)
								.set(rs.getString(3)).set(letterSet).select();
						tilewaarde.next();
						boardModel
								.setValueAt(new Tile(rs.getString(3),
										tilewaarde.getInt(1),
										Tile.NOT_MUTATABLE), y, x);
					}
				}
			}

		} catch (SQLException sql) {
			sql.printStackTrace();
		}

	}

	public void updateboardfromdatabasetoturn(int turn_id) {
		try { // "SELECT LetterType_karakter, Tegel_X, Tegel_Y, BlancoLetterKarakter, beurt_ID FROM gelegdeletter, letter WHERE gelegdeletter.Letter_Spel_ID = ? AND gelegdeletter.Letter_ID = letter.ID AND gelegdeletter.beurt_ID = ? ORDER BY beurt_ID ASC;;";
			ResultSet rs = new Query(getTurnQuery).set(gameId).set(turn_id)
					.select();
			while (rs.next()) {
				int x = rs.getInt(2) - 1;// x
				int y = rs.getInt(3) - 1;// y
				lastTurn = rs.getInt(5);
				if (rs.getString(1).equals("?")) {
					ResultSet tilewaarde = new Query(getTileValue)
							.set(rs.getString(4)).set(letterSet).select();
					tilewaarde.next();
					boardModel.setValueAt(
							new Tile(rs.getString(4), tilewaarde.getInt(1),
									Tile.NOT_MUTATABLE), y, x);

				} else {
					ResultSet tilewaarde = new Query(getTileValue)
							.set(rs.getString(1)).set(letterSet).select();
					tilewaarde.next();
					boardModel.setValueAt(
							new Tile(rs.getString(1), tilewaarde.getInt(1),
									Tile.NOT_MUTATABLE), y, x);
				}
			}
			boardModel.update();

		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public int getCurrentobserveturn() {
		return currentobserveturn;
	}

	public void setCurrentobserveturn(int currentobserveturn) {
		System.out.println(currentobserveturn);
		this.currentobserveturn = currentobserveturn;
	}

	public void Resign() {
		try {
			new Query(resignQuery).set(STATE_RESIGNED).set(this.getGameId())
					.exec();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public int getNumberOfTotalTurns() {

		try {
			ResultSet dbResultamountofturns = new Query(getnumberofturns).set(
					gameId).select();
			dbResultamountofturns.next();
			return dbResultamountofturns.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public void setPlayerLetterFromDatabase() {
		try {
			ResultSet res = new Query(getPlayerTiles).set(getGameId())
					.set(currentUser.getUsername()).select();
			String[] letters;
			if (!(Query.getNumRows(res) == 0)) {
				letters = res.getString(2).split(",");
				for (int x = 0; letters.length > x; x++) {
					ResultSet tilewaarde = new Query(getTileValue)
							.set(letters[x]).set(letterSet).select();
					tilewaarde.next();

					boardModel.setPlayetTile(x,
							new Tile(letters[x], tilewaarde.getInt(1),
									Tile.MUTATABLE));
				}
			}

		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public boolean isIamchallenger() {
		return iamchallenger;
	}

}
