package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.avans.min04sob.scrabble.Playerstash;
import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;
import nl.avans.min04sob.scrabble.views.BoardPanel;

public class GameModel extends CoreModel {

	private CompetitionModel competition;
	private AccountModel opponent;
	private AccountModel challenger;
	private int gameId;
	private String state;
	private String boardName;
	private String letterSet;
	
	//private JLabel player;
	//private JLabel enemy;
	//private JLabel score;
	//private JLabel aantalLettersOver;

	private JPanel myPanel = new JPanel();
	private JPanel myPanel2 = new JPanel();
	private JFrame myFrame = new JFrame();

	private Playerstash playerStash = new Playerstash();
	private BoardPanel boardPanel = new BoardPanel();

	//private JButton vorigScherm;
	//private JButton speelWoord;
	//private JButton swapLetters;
	//private JButton resign;

	private boolean isSpace;
	private String legLetter;
	private Object[][] boardData;

	public static final String STATE_FINISHED = "Finished";
	public static final String STATE_PLAYING = "Playing";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_RESIGNED = "Resigned";
	
	public GameModel() {

		boardPanel.setPreferredSize(new Dimension(300, 300));
		//vorigScherm = new JButton("vorig scherm");
		//speelWoord = new JButton("speel woord");
		//swapLetters = new JButton("swap letters");
		//resign = new JButton("resign");

		//player = new JLabel();
		//enemy = new JLabel();
		//score = new JLabel();
		//aantalLettersOver = new JLabel();

		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
		//myPanel.add(player);
		myPanel.add(boardPanel);
		//myPanel.add(playerStash);

		//myPanel2.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
		//myPanel2.add(vorigScherm);
		//myPanel2.add(score);
		//myPanel2.add(aantalLettersOver);
		//myPanel2.add(speelWoord);
		//myPanel2.add(swapLetters);
		//myPanel2.add(resign);
	}

	public GameModel(int gameId, AccountModel currentUser) {
		String query = "SELECT * FROM `spel` WHERE `ID` = " + gameId;
		try {
			ResultSet dbResult = Dbconnect.select(query);

			if (dbResult.first()) {
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
	
	public void isSpace() {
		if (legLetter == null) {
			isSpace = true;
			legLetter();
		} else {
			isSpace = false;
			System.out.println("er ligt al een letter");
		}
	}

	public void legLetter() {
		
	}

	@Override
	public void update() {
		// TODO fire property change for new games and changed game states
	}

	public String toString() {
		return competition.getName() + " - " + opponent.getUsername();
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
		String query = "SELECT `totaalscore` FROM `score` WHERE `Spel_ID` = '"
				+ gameId + "' AND `Account_Naam` != " + opponent.getUsername();
		try {
			ResultSet dbResult = Dbconnect.select(query);

			return dbResult.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void fillBoardData(int gameId){
		boardData = new Object[15][15];
		String query ="SELECT LetterType_karkakter, Tegel_X, Tegel_Y, BlancoLetterKarakter FROM gelegdeletter WHERE gelegdeletter.Letter_Spel_ID ='"+gameId+"' AND gelegdeletter.Letter_ID = letter.ID ORDER BY beurt_ID ASC;";
		try{
			ResultSet letters = Dbconnect.select(query);
			while(letters.next()){
				int x = letters.getInt(2) - 1;//x
				int y = letters.getInt(3) - 1;//y
				if(letters.getString(1).equals("?")){
					boardData[y][x] = letters.getString(4);
				}else{
					boardData[y][x] = letters.getString(1);
				}
			}
			
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
}
