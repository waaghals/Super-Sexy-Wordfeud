package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.CompetitionModel;
import javax.swing.JTable;

public class CompetitionScoreView extends CorePanel {

	private JScrollPane scrollPane;
	private JList<CompetitionModel> competitionList;
	private JButton actionButton;
	private JButton cancelButton;
	private JLabel competitionLabel;
	private JLabel playersLabel;
	private String[] columnNames;
	private JTable table;

	public CompetitionScoreView() {
		setLayout(new MigLayout("",
				"[100px:120px:120px,grow][200px:230.00px:220px,grow]",
				"[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));

		columnNames = new String[] { "account_naam",
				"aantal gespeelde webstrijden", "totaal aantal punten",
				"gemiddeld aantal punten per wedstrijd",
				"aantal webstrijden gewonnen/verloren", "bayesian-average" };
		
		Object[][] data = {
			    {"Kathy", "6",
			     "7", 250, 600,700},
			     {"Kathy", "6",
				     "7", 250, 600,700},
				     {"Kathy", "6",
					     "7", 250, 600,700},
					     {"Kathy", "6",
						     "7", 250, 600,700},
						     {"Kathy", "6",
							     "7", 250, 600,700},
			};

		competitionLabel = new JLabel("Beschikbare competities");
		add(competitionLabel, "cell 0 0,alignx left");

		playersLabel = new JLabel("Spelers in de competitie");
		add(playersLabel, "cell 1 0,alignx right");

		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 1 3,grow");

		competitionList = new JList<CompetitionModel>();
		scrollPane.setViewportView(competitionList);

		table = new JTable(data, columnNames);
		add(table, "cell 1 1 1 3,grow");

		cancelButton = new JButton("Annuleer");
		add(cancelButton, "cell 0 4,alignx left,aligny top");

		actionButton = new JButton("Competitie deelnemen");
		add(actionButton, "cell 1 4,alignx right,growy");

	}

	public void addActionListenerActieButton(ActionListener listener) {
		actionButton.addActionListener(listener);
	}

	public void addActionListenerAnnuleerButton(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}

	public void fillAvailableCompetitions(
			CompetitionModel[] availableCompetitions) {
		competitionList.setListData(availableCompetitions);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	public CompetitionModel selectedCompetition() {
		return competitionList.getSelectedValue();

	}

	public void setText(String labelText, String button) {
		competitionLabel.setText(labelText);
		actionButton.setText(button);
		revalidate();
	}

}
