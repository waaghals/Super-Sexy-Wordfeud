package nl.avans.min04sob.scrabble.models;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.controllers.BoardController;
import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;

public class GameModel extends CoreModel {

	private CompetitionModel competition;
	private AccountModel opponent;
	private AccountModel challenger;
	private int gameId;
	private String state;
	private String boardName;
	private String letterSet;

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

	public GameModel(int gameID, AccountModel currentUser) {
		try {
			ResultSet dbResult = new Query(getGameQuery).set(gameId).select();

			if (Query.getNumRows(dbResult) == 1) {
				dbResult.next();
				this.gameId = gameID;
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

	public void legWoord() {
		String[][] Boardcurrent = new String[boardcontroller.getBpm().tileData.length ][boardcontroller.getBpm().tileData[1].length ];
			    for(int y = 0;boardcontroller.getBpm().tileData.length  > y; y++){
			      for(int x = 0;boardcontroller.getBpm().tileData[y].length > x;x++){
			       Boardcurrent[y][x] = boardcontroller.getBpm().tileData[y][x].getLetter();
			            
			      }
			    }
			     
		String[][] compared = compareArrays(compared, Boardcurrent);
		String oldnumberx = compared[0][1];
		boolean verticalLine = true;
		for (String[] s : compared) {
			if (oldnumberx.equals(s[1])) {
			} else {
				verticalLine = false;
			}
		}
		String oldnumbery = compared[0][2];
		boolean horizontalLine = true;
		for (String[] s : compared) {
			if (oldnumberx.equals(s[1])) {
			} else {
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
		//return competition.getName() + " - " + opponent.getUsername();
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
	public String[][] getboardData(){
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
			int numRows  = Query.getNumRows(res);
			
			words = new String[numRows];
			int i = 0;
			while(res.next()){
				words[i] = res.getString(1);
				i++;		
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}

		return words;
	}

	public BoardController getBoardcontroller() {
		return boardcontroller;
	}

	
}