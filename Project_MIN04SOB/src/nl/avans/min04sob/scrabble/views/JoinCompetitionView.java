package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
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
	private JButton actieButton;
	private JButton annuleerButton;
	private JLabel competitionLabel;
	private JLabel spelersLabel;
	private String competitionLabelText;
	private String buttonText;

	public JoinCompetitionView() {
		setLayout(new MigLayout("", "[100px:120px:120px,grow][200px:200px:220px,grow]", "[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));

		competitionLabel = new JLabel("Beschikbare competities");
		add(competitionLabel, "cell 0 0,alignx left");

		spelersLabel = new JLabel("Spelers in de competitie"); 
		add(spelersLabel, "cell 1 0,alignx right");

		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 1 3,grow");

		competitionList = new JList<CompetitionModel>();
		scrollPane.setViewportView(competitionList);

		scrollPane_2 = new JScrollPane();
		add(scrollPane_2, "cell 1 1 1 3,grow");

		playerList = new JList<AccountModel>();
		scrollPane_2.setViewportView(playerList);

		annuleerButton = new JButton("Annuleer");
		add(annuleerButton, "cell 0 4,alignx left,aligny top");
		
		actieButton = new JButton("Competitie deelnemen");
		add(actieButton, "cell 1 4,alignx right,growy");
	}

	public void addActionListenerActieButton(ActionListener listener) {
		actieButton.addActionListener(listener);
	}

	public void addActionListenerAnnuleerButton(ActionListener listener) {
		annuleerButton.addActionListener(listener);
	}
	
	public CompetitionModel selectedCompetition(){
		if(competitionList.getValueIsAdjusting()){
			competitionList.getSelectedValue();
		}
		return competitionList.getSelectedValue();
		
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	public void setText(String labelText, String button) {
		competitionLabelText = labelText;
		buttonText = button;
	}

}
