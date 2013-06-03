package nl.avans.min04sob.scrabble.models;

import java.awt.Dimension;
import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Db;
import nl.avans.min04sob.scrabble.core.MatrixUtils;
import nl.avans.min04sob.scrabble.core.Query;

public class GameModel extends CoreModel {

	public static void main2(String[] args) {
		System.out.println("Yo sjaak, je runned de verkeerde main ;)");
		new GameModel(0, null, null, false).test();
	}

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
	public static final String STATE_DENIED = "woord is geweigerd";
	public static final String STATE_PENDING = "woord is al voorgesteld";
	public static final String STATE_SETPENDING = "woord wordt voorgesteld";
	

	private final String getGameQuery = "SELECT * FROM `spel` WHERE `ID` = ?";
	private final String getOpenQuery = "SELECT * FROM `gelegdeletter` WHERE Tegel_Y =? AND Tegel_X = ? AND Letter_Spel_ID = ?";
	private final String getScoreQuery = "SELECT `totaalscore` FROM `score` WHERE `Spel_ID` = ? AND `Account_Naam` != ?";
	private final String getTurnQuery = "SELECT LetterType_karakter, Tegel_X, Tegel_Y, BlancoLetterKarakter, beurt_ID FROM gelegdeletter, letter WHERE gelegdeletter.Letter_Spel_ID = ? AND gelegdeletter.Letter_ID = letter.ID AND gelegdeletter.beurt_ID = ? ORDER BY beurt_ID ASC;;";

	private final String getBoardQuery = "SELECT  `gl`.`Spel_ID` ,  `gl`.`Beurt_ID` ,  `l`.`LetterType_karakter` ,  `gl`.`Tegel_X` ,  `gl`.`Tegel_Y` ,  `gl`.`BlancoLetterKarakter` FROM  `gelegdeletter` AS  `gl` JOIN  `letter` AS  `l` ON ( (`l`.`Spel_ID` =  `gl`.`Spel_ID`)AND(`l`.`ID` =  `gl`.`Letter_ID`) ) JOIN  `spel`  `s` ON  `s`.`id` =  `gl`.`Spel_ID` JOIN  `letterset` AS  `ls` ON  `ls`.`code` =  `s`.`LetterSet_naam` WHERE gl.Spel_ID =?";
	private final String getPlayerTiles = "SELECT Beurt_ID,inhoud FROM plankje WHERE Spel_ID = ? AND Account_naam = ? ORDER BY Beurt_ID DESC ";

	private final String getTileValue = "Select waarde FROM lettertype WHERE karakter = ? AND LetterSet_code = ?";
	private final String yourTurnQuery = "SELECT `account_naam`, MAX(`beurt`.`id`) AS `last_turn`, `account_naam_uitdager` AS `challenger` FROM `beurt` JOIN `spel` ON `beurt`.`spel_id` = `spel`.`id` WHERE `beurt`.`spel_id` = ? GROUP BY `spel_id` ORDER BY `beurt`.`id`";
	private final String whosTurnAtTurn = "SELECT account_naam FROM `beurt` WHERE `spel_id` = ? AND ID = ?";

	private final String resignQuery = "UPDATE `spel` SET `Toestand_type` = ? WHERE `ID` = ?";

	private final String scoreQuery = "SELECT ID , score FROM beurt WHERE score IS NOT NULL AND score != 0 AND Account_naam = ?";
	//private final String getnumberofturns = "SELECT max(beurt_ID) FROM gelegdeletter, letter WHERE gelegdeletter.Letter_Spel_ID = ? AND gelegdeletter.Letter_ID = letter.ID ";
	
	private final String getWordFromDatabase = "SELECT * FROM woordenboek WHERE woord = ?;";
	

	private final String getnumberofturns = "SELECT max(beurt_ID) FROM gelegdeletter JOIN letter ON gelegdeletter.Letter_ID = letter.ID  WHERE gelegdeletter.Spel_ID = ?";
	private final boolean observer;

	public GameModel(int gameId, AccountModel user, BoardModel boardModel,
			boolean observer) {
		this.observer = observer;
		this.boardModel = boardModel;
		currentUser = user;

		try {
			Future<ResultSet> worker = Db.run(new Query(getGameQuery)
					.set(gameId));

			ResultSet dbResult = worker.get();
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
						opponent = new AccountModel(challengeeName);
						challenger = new AccountModel(challengerName);
						iamchallenger = true;
					} else {
						opponent = new AccountModel(challengerName);
						challenger = new AccountModel(challengeeName);
						iamchallenger = false;
					}
				} else {
					opponent = new AccountModel(challengeeName);
					challenger = new AccountModel(challengerName);
					iamchallenger = false;

				}

			}

		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
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

	public void checkValidWord(Tile[][] playedLetters, Tile[][] newBoard) {
		// verticaal woord
		ArrayList<ArrayList<Tile>> verticalenWoorden = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>> horizontalenWoorden = new ArrayList<ArrayList<Tile>>();
		for(int y = 0; y <15;y++){
			for(int x = 0 ; x < 15; x++){
				if(playedLetters[y][x] != null){
					//er is op dit coordinaat een letter neergelegd en de x en de y worden opgeslagen.
					int counterY = y;
					int counterX = x;
					boolean beenLeft = false;
					ArrayList<Tile> verticaalWoord = new ArrayList<Tile>();
					ArrayList<Tile> horizontaalWoord = new ArrayList<Tile>();
					while(counterX >0){
						//hij gaat een letter naar links tot hij een lege plaats tegenkomt.
						if(newBoard[counterY][counterX-1] != null && (!beenLeft)){
							counterX--;
						}else if(newBoard[counterY][counterX+1] != null && newBoard[counterY][counterX] != null){
							beenLeft = true;
							// als hij nog niet terug rechts is slaat hij de letter op in de array en telt hij en gaat hij naar de volgende;
							horizontaalWoord.add(newBoard[counterY][counterX]);
							counterX++;
						}else if(newBoard[counterY][counterX] != null){
							horizontaalWoord.add(newBoard[counterY][counterX]);
							break;
						}
					}
					counterY = y;
					counterX = x;
					boolean beenTop = false;
					while(counterY > 0){
						if(newBoard[counterY-1][counterX] != null && (!beenTop)){
							counterY--;
						}else if(newBoard[counterY+1][counterX] != null && newBoard[counterY][counterX] != null){
							beenTop = true;
							verticaalWoord.add(newBoard[counterY][counterX]);
							counterY++;
						}else if(newBoard[counterY][counterX] != null){
							verticaalWoord.add(newBoard[counterY][counterX]);
							break;
						}
					}
					if(!verticalenWoorden.contains(verticaalWoord)){
						if(verticaalWoord.size() > 1){
							verticalenWoorden.add(verticaalWoord);
						}
					}
					if(!horizontalenWoorden.contains(horizontaalWoord)){
						if(horizontaalWoord.size() > 1){
							horizontalenWoorden.add(horizontaalWoord);
						}
					}
				}
			}
		}
		ArrayList<String> teVergelijkenWoorden = new ArrayList<String>();
		for(ArrayList<Tile> tiles: verticalenWoorden){
			if(tiles.size() > 1){
				String output = "";
				for(Tile t :tiles){
					output += t.getLetter();		
				}
				teVergelijkenWoorden.add(output);
			}
		}
		for(ArrayList<Tile> tiles: horizontalenWoorden){
			if(tiles.size() > 1){
				String output = "";
				for(Tile t :tiles){
					output += t.getLetter();
				}
				teVergelijkenWoorden.add(output);
			}
		}
		for (String s:teVergelijkenWoorden){
			System.out.println(s);
		}
		

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

	@Deprecated
	public String[][] getboardData() {
		return this.boardData;
	}

	public void getBoardFromDatabase() {
		try {
			Future<ResultSet> worker = Db.run(new Query(getBoardQuery)
					.set(gameId));

			ResultSet rs = worker.get();
			while (rs.next()) {
				int x = rs.getInt(4) - 1;// x
				int y = rs.getInt(5) - 1;// y
				if (x > -1 && y > -1) {
					if (rs.getString(3).equals("?")) {

						Future<ResultSet> worker2 = Db.run(new Query(
								getTileValue).set(rs.getString(6)).set(
								letterSet));
						ResultSet tilewaarde = worker2.get();
						tilewaarde.next();
						boardModel
								.setValueAt(new Tile(rs.getString(6),
										tilewaarde.getInt(1),
										Tile.NOT_MUTATABLE), y, x);

					} else {
						// TODO add letter value in query

						Future<ResultSet> worker1 = Db.run(new Query(
								getTileValue).set(rs.getString(3)).set(
								letterSet));
						ResultSet tilewaarde = worker1.get();
						tilewaarde.next();
						boardModel
								.setValueAt(new Tile(rs.getString(3),
										tilewaarde.getInt(1),
										Tile.NOT_MUTATABLE), y, x);
					}
				}
			}

		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
	}

	private void checkWordsInDatabase(ArrayList<String> words) throws Exception{
		for(String s:words){
			System.out.println(s);
			try {
				String query = "INSERT INTO woordenboek(woord, `status`) VALUES(?,'Pending')";
				ResultSet wordresult = new Query(getWordFromDatabase).set(s).select();
				if(Query.getNumRows(wordresult) == 0){
					new Query(query).set(s).exec();
					throw new Exception(STATE_SETPENDING);
				}else if(wordresult.next()){
					String statusString = wordresult.getString("status");
					if(statusString.equals("Denied")){
						throw new Exception(STATE_DENIED);
					}else if(statusString.equals("Pending")){
						throw new Exception(STATE_PENDING);
					}else if(statusString.equals("Accepted")){
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public BoardModel getBoardModel() {
		return boardModel;
	}

	public String getBoardName() {
		return boardName;
	}

	public AccountModel getChallenger() {
		return challenger;
	}

	public CompetitionModel getCompetition() {
		return competition;
	}

	public int getCurrentobserveturn() {
		return currentobserveturn;
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

	public int getGameId() {
		return gameId;
	}

	public void getlastrunFromDatabase() {
		try {
			Future<ResultSet> worker = Db.run(new Query(getTurnQuery).set(
					gameId).set(lastTurn));
			ResultSet rs = worker.get();
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

		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
	}

	public String getLetterSet() {
		return letterSet;
	}

	public int getNumberOfTotalTurns() {

		try {
			Future<ResultSet> worker = Db.run(new Query(getnumberofturns)
					.set(gameId));
			ResultSet dbResultamountofturns = worker.get();
			dbResultamountofturns.next();
			return dbResultamountofturns.getInt(1);
		} catch (SQLException | InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public AccountModel getOpponent() {
		return opponent;
	}

	public String[] getRequestedWords() {
		String[] words = null;
		String query = "SELECT `woord` FROM `nieuwwoord` WHERE `status` = `pending`";
		try {
			Future<ResultSet> worker = Db.run(new Query(query));
			ResultSet res = worker.get();
			int numRows = Query.getNumRows(res);

			words = new String[numRows];
			int i = 0;
			while (res.next()) {
				words[i] = res.getString(1);
				i++;
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}

		return words;
	}

	public int getScore(String userName) {
		try {
			Future<ResultSet> worker = Db.run(new Query(getScoreQuery).set(
					gameId).set(userName));

			ResultSet rs = worker.get();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getScore(Tile[][] playedLetters, ArrayList[] woorden,
			BoardModel currentBoard) {
		int Score = 0;
		for (int wordCounter = 0; woorden.length < wordCounter + 1; wordCounter++) {
			int scoreofcurrentword = 0;
			boolean times3 = false;
			boolean times2 = false;
			for (int letterCounter = 0; woorden[wordCounter].size() < letterCounter; letterCounter++) {
				int scoreofcurrentletter = 0;
				scoreofcurrentletter = ((Tile) woorden[wordCounter]
						.get(letterCounter)).getValue();
				// check if any played letter are on special tiles

				for (int xpos = 0; playedLetters.length > xpos; xpos++) {
					for (int ypos = 0; playedLetters[xpos].length > ypos; ypos++) {
						if (((Tile) woorden[wordCounter].get(letterCounter) == playedLetters[xpos][ypos])) {

							switch (currentBoard.getMultiplier(new Point(xpos,
									ypos))) {
							case 4:
								// Triple letter
								scoreofcurrentletter = scoreofcurrentletter * 3;
								break;
							case 3:
								// dubbel letter
								scoreofcurrentletter = scoreofcurrentletter * 2;
								break;
							case 2:
								// tripple woord
								times3 = true;

								break;
							case 1:
								// dubble woord
								times2 = true;

								break;
							}
						}
						if (currentBoard.getMultiplier(new Point(xpos, ypos)) == 1) {

						}

					}
				}
				scoreofcurrentword = +scoreofcurrentletter;

			}
			if (times3) {
				scoreofcurrentword = scoreofcurrentword * 3;

			} else if (times2) {
				scoreofcurrentword = scoreofcurrentword * 2;
			}

			Score = +scoreofcurrentword;

		}

		return Score;
	}

	public String getState() {
		return state;
	}

	public Tile[] getTiles() {

		return null;
	}

	public boolean isFree(int x, int y) {
		try {
			Future<ResultSet> worker = Db.run(new Query(getOpenQuery)
					.set(y + 1).set(x).set(gameId));
			ResultSet rs = worker.get();
			if (Query.getNumRows(rs) == 0) {
				return true;
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return false;
	}

	public boolean isIamchallenger() {
		return iamchallenger;
	}

	public boolean isObserver() {
		return observer;
	}

	public void requestWord(String word) {
		String status = "pending";

		String query = "INSERT INTO `woordenboek` (`woord`, `status`) VALUES (?, ?)";
		try {
			Db.run(new Query(query).set(word).set(status));
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public void resign() {
		try {
			Db.run(new Query(resignQuery).set(STATE_RESIGNED).set(
					this.getGameId()));
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public String score(int toTurn) {
		String score = "";

		try {
			Future<ResultSet> worker = Db.run(new Query(scoreQuery)
					.set(challenger.getUsername()));
			int ch = scorecounter(worker.get(), toTurn);

			
			Future<ResultSet> worker1 = Db.run(new Query(scoreQuery).set(opponent.getUsername()));
			int op = scorecounter(
					worker1.get(),
					toTurn);

			score = ": " + Integer.toString(ch) + " points";

			score += " , : " + Integer.toString(op) + " points";

		} catch (SQLException | InterruptedException | ExecutionException e) {
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

	public void setCurrentobserveturn(int currentobserveturn) {
		System.out.println(currentobserveturn);
		this.currentobserveturn = currentobserveturn;
	}

	public void setPlayerLetterFromDatabase() {
		try {
			Future<ResultSet> worker = Db.run(new Query(getPlayerTiles).set(
					getGameId()).set(currentUser.getUsername()));
			ResultSet res = worker.get();
			String[] letters;
			if (!(Query.getNumRows(res) == 0)) {
				res.next();

				letters = res.getString(2).split(",");
				for (int x = 0; letters.length > x; x++) {

					Future<ResultSet> worker1 = Db.run(new Query(getTileValue)
							.set(letters[x]).set(letterSet));
					ResultSet tilewaarde = worker1.get();
					tilewaarde.next();

					/*
					 * boardModel.setPlayetTile(x, new Tile(letters[x],
					 * tilewaarde.getInt(1), Tile.MUTATABLE));
					 */
				}
			}

		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
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

	@Override
	public String toString() {
		// return gameId + "";
		return "(" + gameId + ") " + competition.getDesc() + " - "
				+ opponent.getUsername();
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

	public void updateboardfromdatabasetoturn(int turn_id) {
		try { // "SELECT LetterType_karakter, Tegel_X, Tegel_Y, BlancoLetterKarakter, beurt_ID FROM gelegdeletter, letter WHERE gelegdeletter.Letter_Spel_ID = ? AND gelegdeletter.Letter_ID = letter.ID AND gelegdeletter.beurt_ID = ? ORDER BY beurt_ID ASC;;";

			Future<ResultSet> worker = Db.run(new Query(getTurnQuery).set(
					gameId).set(turn_id));
			ResultSet rs = worker.get();
			while (rs.next()) {
				int x = rs.getInt(2) - 1;// x
				int y = rs.getInt(3) - 1;// y
				lastTurn = rs.getInt(5);
				if (rs.getString(1).equals("?")) {
					Future<ResultSet> worker1 = Db.run(new Query(getTileValue)
							.set(rs.getString(4)).set(letterSet));
					ResultSet tilewaarde = worker1.get();
					tilewaarde.next();
					boardModel.setValueAt(
							new Tile(rs.getString(4), tilewaarde.getInt(1),
									Tile.NOT_MUTATABLE), y, x);

				} else {
					Future<ResultSet> worker2 = Db.run(new Query(getTileValue)
							.set(rs.getString(1)).set(letterSet));
					ResultSet tilewaarde = worker2.get();
					tilewaarde.next();
					boardModel.setValueAt(
							new Tile(rs.getString(1), tilewaarde.getInt(1),
									Tile.NOT_MUTATABLE), y, x);
				}
			}
			boardModel.update();

		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
	}

	public boolean whosturn() {
		// dont use unless observing
		// use yourturn instead

		try {
			System.out.println(getGameId() + "  " + currentobserveturn);
			Future<ResultSet> worker = Db.run(new Query(whosTurnAtTurn).set(
					getGameId()).set(currentobserveturn));
			ResultSet res = worker.get();
			int numRows = Query.getNumRows(res);

			if (numRows == 0) {
				return true;
			}
			res.next();
			String lastturnplayername = res.getString(1);

			if (lastturnplayername.equals(challenger.getUsername())) {
				return false;
			} else {
				return true;
			}

		} catch (SQLException | InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean yourturn() {
		try {
			Future<ResultSet> worker = Db.run(new Query(yourTurnQuery)
					.set(getGameId()));
			ResultSet res = worker.get();

			int turnCount = Query.getNumRows(res);

			// If it is the first turn
			if (turnCount == 0) {
				// If the currentUser is the challenger return true else false
				return iamchallenger;
			}
			res.next();
			String lastturnplayername = res.getString("account_naam");

			// A user has the move is he is not the last person who made a move
			return !lastturnplayername.equals(currentUser.getUsername());

		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return false;
	}
	public int getvalueforLetter(String letter) {
	
		try {
			Future<ResultSet> worker = Db.run(new Query(getTileValue).set(letter).set(letterSet));
			ResultSet tileValue = worker.get();
		
		
			tileValue.next();
			return tileValue.getInt(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
