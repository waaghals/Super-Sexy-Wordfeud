package nl.avans.min04sob.mvcvoorbeeld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.avans.min04sob.scrabble.core.CoreModel;

public class ScoreboardModel extends CoreModel {

	private ArrayList<Boardline> scoreboard;
	private static final int SCOREBOARDSIZE = 10;

	public ScoreboardModel() {
		scoreboard = new ArrayList<Boardline>();
	}

	public void addScore(Boardline newScore) {
		scoreboard.add(newScore);
		orderboard();
		
		//Tell everybody about our change
		//TODO pass the old value
		firePropertyChange("scoreboard", null, scoreboard);
	}

	//Fucking ugly code is fucking ugly
	@SuppressWarnings("unchecked")
	private void orderboard() {
		Collections.sort(scoreboard, new Comparator() {
			public int compare(Object one, Object two) {
				return ((Boardline) two).getScore()
						- ((Boardline) one).getScore();
			};
		});
		
		int index = SCOREBOARDSIZE;
		if(scoreboard.size() < SCOREBOARDSIZE){
			index = scoreboard.size();
		}

		List<Boardline> tempList = scoreboard.subList(0, index);

		ArrayList<Boardline> newArrayList = new ArrayList<Boardline>();
		for (Boardline boardline : tempList) {
			newArrayList.add(boardline);
		}
		scoreboard = newArrayList;
	}

	public class Boardline{

		private String name;
		private int score;

		
		public Boardline(String playerName, int playerScore) {
			name = playerName;
			score = playerScore;
		}

		public int getScore() {
			return score;
		}

		public String getName() {
			return name;
		}
	}
}
