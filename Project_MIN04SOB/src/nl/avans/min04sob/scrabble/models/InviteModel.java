package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;

public class InviteModel extends CoreModel {

	private int gameID;
	private ArrayList<String> competitions;
	private ArrayList<String> players;
	private AccountModel account;
	private final String selectQueryCompetitie = "SELECT 'ID', 'omschrijving' FROM 'competitie' AS 'co' JOIN 'deelnemer' AS 'dr'  ON 'co.ID' = 'dr.Competitie_ID' WHERE 'dr.Account_naam' = ?";
	private final String selectQueryPlayers = "SELECT 'Account_naam' FROM 'competitie' AS 'co' JOIN 'deelnemer' AS 'dr' ON 'co.ID' = 'dr.Competitie_ID' WHERE 'dr.Competitie_ID' = '?'";

	public InviteModel() {
		account = new AccountModel();
		competitions = new ArrayList<String>();
		players = new ArrayList<String>();

	}

	public ArrayList<String> getCompetitions() {
		try {
			ResultSet rs = new Query(selectQueryCompetitie).set(
					account.getUsername()).select();
			while (rs.next()) {
				// gameID = Integer.parseInt(rs.getString(1));
				competitions.add(rs.getString(2));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException n) {
			n.printStackTrace();
		}
		return competitions;
	}

	public ArrayList<String> getPlayers() {
		try {
			//TODO de rs variabele afmaken doormiddel van het commentaar in de .set
			ResultSet rs = new Query(selectQueryPlayers).set(/*hier moet de geselcteerde ID in komende te staan die je uit de vorige query-resultaten haalt*/).select();
			while (rs.next()) {
				players.add(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException n) {
			n.printStackTrace();
		}
		return players;
	}

	@Override
	public void update() {

	}

}
