package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.CompetitionModel;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinCompetitionView extends CorePanel {

	private JScrollPane scrollPane;
	private JList<CompetitionModel> competitionList;
	private JScrollPane scrollPane_2;
	private JList<AccountModel> playerList;
	private JButton actionButton;
	private JButton cancelButton;
	private JLabel competitionLabel;
	private JLabel playersLabel;
	private String competitionLabelText;
	private String buttonText;
	private JFrame myFrame;

	public JoinCompetitionView() {
		setLayout(new MigLayout("", "[100px:120px:120px,grow][200px:200px:220px,grow]", "[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));

		competitionLabel = new JLabel();
		add(competitionLabel, "cell 0 0,alignx left");

		playersLabel = new JLabel("Spelers in de competitie"); 
		add(playersLabel, "cell 1 0,alignx right");

		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 1 3,grow");

		competitionList = new JList<CompetitionModel>();
		scrollPane.setViewportView(competitionList);

		scrollPane_2 = new JScrollPane();
		add(scrollPane_2, "cell 1 1 1 3,grow");

		playerList = new JList<AccountModel>();
		scrollPane_2.setViewportView(playerList);

		cancelButton = new JButton("Annuleer");
		add(cancelButton, "cell 0 4,alignx left,aligny top");
		
		actionButton = new JButton();
		add(actionButton, "cell 1 4,alignx right,growy");

	}

	public void addActionListenerActieButton(ActionListener listener) {
		actionButton.addActionListener(listener);
	}

	public void addActionListenerAnnuleerButton(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	public void setText(String labelText, String button) {
		competitionLabel.setText(labelText);
		actionButton.setText(button);
		revalidate();
	}

}
