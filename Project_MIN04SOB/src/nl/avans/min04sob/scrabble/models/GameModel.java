package nl.avans.min04sob.scrabble.models;

import java.awt.Dimension;
import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

import nl.avans.min04sob.scrabble.controllers.BoardController;
import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.MatrixUtils;
import nl.avans.min04sob.scrabble.core.Query;

public class GameModel extends CoreModel {

	private CompetitionModel competition;
	private AccountModel opponent;
	private AccountModel challenger;
	private int gameId;
	private String state;
	private String boardName;
	private String letterSet;
	private boolean isFirstMove; // TODO set this value and modify it

	private StashModel stash = new StashModel();

	private BoardController boardcontroller = new BoardController();

	private String[][] boardData;
	private int lastTurn;

	public static final String STATE_FINISHED = "Finished";
	public static final String STATE_PLAYING = "Playing";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_RESIGNED = "Resigned";

	private final String getGameQuery = "SELECT * FROM `spel` WHERE `ID` = ?";
	private final String getOpenQuery = "SELECT * FROM `gelegdeletter` WHERE Tegel_Y =? AND Tegel_X = ? AND Letter_Spel_ID = ?";
	private final String getScoreQuery = "SELECT `totaalscore` FROM `score` WHERE `Spel_ID` = ? AND `Account_Naam` != ?";
	private final String getBoardQuery = "SELECT LetterType_karkakter, Tegel_X, Tegel_Y, BlancoLetterKarakter, beurt_ID FROM gelegdeletter, letter WHERE gelegdeletter.Letter_Spel_ID =? AND gelegdeletter.Letter_ID = letter.ID AND gelegdeletter.beurt_ID > ? ORDER BY beurt_ID ASC;";

	public GameModel() {
		boardcontroller.getBpv().setPreferredSize(new Dimension(300, 300));
		lastTurn = 0;
		boardData = new String[15][15];
	}

	public GameModel(int gameId, AccountModel currentUser) {
		try {
			ResultSet dbResult = new Query(getGameQuery).set(gameId).select();

			if (Query.getNumRows(dbResult) == 1) {
				dbResult.next();
				this.gameId = gameId;
				competition = new CompetitionModel(dbResult.getInt(2));
				state = dbResult.getString(3);
				String challengerName = dbResult.getString(4);
				String challengeeName = dbResult.getString(5);

				boardName = dbResult.getString(9);
				letterSet = dbResult.getString(10);

				if (challengerName.equals(currentUser.getUsername())) {
					opponent = new AccountModel(challengeeName);
					challenger = currentUser;
				} else {
					opponent = new AccountModel(challengerName);
					challenger = opponent;
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

	public void checkDirection() {

	}

	public void playWord(HashMap<Point, Tile> tiles) {
		String[][] Boardcurrent = new String[boardcontroller.getBpm().tileData.length][boardcontroller
				.getBpm().tileData[1].length];
		for (int y = 0; boardcontroller.getBpm().tileData.length > y; y++) {
			for (int x = 0; boardcontroller.getBpm().tileData[y].length > x; x++) {
				Boardcurrent[y][x] = boardcontroller.getBpm().tileData[y][x]
						.getLetter();

			}
		}

		String[][] compared = compareArrays(compared, Boardcurrent);
		String oldnumberx = compared[0][1];
		boolean verticalLine = true;
		for (String[] s : compared) {
			if (!oldnumberx.equals(s[1])) {

				verticalLine = false;
			}
		}
		String oldnumbery = compared[0][2];
		boolean horizontalLine = true;
		for (String[] s : compared) {
			if (!oldnumberx.equals(s[1])) {

				horizontalLine = false;
			}
		}
	}

	@Override
	public void update() {
		// TODO fire property change for new games and changed game states
	}

	public String toString() {
		return gameId + "";
		// return competition.getName() + " - " + opponent.getUsername();
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

	public int getScore() {
		try {
			ResultSet rs = new Query(getScoreQuery).set(gameId)
					.set(opponent.getUsername()).select();

			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void getBoardFromDatabase() {
		try {
			ResultSet rs = new Query(getBoardQuery).set(gameId).set(lastTurn)
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

	public static void main(String[] args) {
		System.out.println("Yo sjaak, je runned de verkeerde main ;)");
		new GameModel().test();
	}

	public void checkValidMove(BoardModel oldBoard, BoardModel newBoard)
			throws InvalidMoveException {

		Tile[][] oldData = (Tile[][]) oldBoard.getData();
		Tile[][] newData = (Tile[][]) newBoard.getData();

		// First find out which letters where played
		Tile[][] playedLetters = (Tile[][]) MatrixUtils.xor(oldData, newData);
		if (isFirstMove) {
			boolean onStar = false;
			Point starCoord = getStartPoint(oldBoard);

			// Coords for all currently played letters
			Point[] coords = MatrixUtils.getLetterCoords(playedLetters);
			for (Point point : coords) {
				if (starCoord == point) {
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

		if (!MatrixUtils.isAligned(playedLetters)) {
			throw new InvalidMoveException(InvalidMoveException.NOT_ALIGNED);
		}

	}

	private Point getStartPoint(BoardModel board) {
		// odd numbers of tile, meaning we have a center tile
		// The database is setup in a way that a star tile doesn't need to be in
		// the
		// Center tile, so check it from the database
		int width = board.getData().length;
		int height = board.getData()[0].length;
		Point coord = null;

		if (height % 2 == 1 && width % 2 == 1) {
			coord = new Point(height / 2, width / 2);
			if (board.getMultiplier(coord) == BoardModel.STAR) {
				return coord;
			}
		} else {
			// Take the hard approach and find the start tile manually
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					coord = new Point(i, j);
					if (board.getMultiplier(coord) == BoardModel.STAR) {
						return coord;
					}
				}
			}
		}

		return coord;
	}

}