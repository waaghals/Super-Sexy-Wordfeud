package nl.avans.min04sob.scrabble.views;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.mvc.CorePanel;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.CompetitionModel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CompetitionView extends CorePanel{
	
	private JLabel competitionsLabel;
	private JLabel playersInCompetitonLabel;
	private JScrollPane scrollPane;
	private JList<CompetitionModel> competitionsList;
	private JScrollPane scrollPane_1;
	private JList<AccountModel> playerList;
	private JButton backButton;
	private JButton actionButton;
 
	
	public CompetitionView(){
		
		setLayout(new MigLayout("", "[75px:n][:400px:1000px,growprio 70,grow 250][250px,growprio 30,grow][150px:n][57.00]", "[20px:20px:20px][150px:800px:1000px,grow][25px:25px:25px]"));
		super.setMinimumSize(new Dimension(600, 400));
		competitionsLabel = new JLabel();
		add(competitionsLabel, "cell 0 0 2 1,alignx left");
		
		playersInCompetitonLabel = new JLabel();
		add(playersInCompetitonLabel, "cell 2 0,alignx right");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 2 1,grow");
		
		competitionsList = new JList<CompetitionModel>();
		competitionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(competitionsList);
		
		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 2 1 2 1,grow");
		
		playerList = new JList<AccountModel>();
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(playerList);
		
		backButton = new JButton("Terug");
		add(backButton, "cell 0 2,alignx left");
		
		actionButton = new JButton();
		actionButton.setHorizontalAlignment(SwingConstants.RIGHT);
		actionButton.setEnabled(false); //test

		add(actionButton, "cell 3 2,alignx right");
		
		competitionsList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(actionButton.getText().equals("Competitie deelnemen")){
				actionButton.setEnabled(true);	
				}
			}
		}); 
		
		playerList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(actionButton.getText().equals("1 uitdaging per tegenstander")||actionButton.getText().equals("Speler uitdagen")){
					actionButton.setEnabled(true);	
				}	
			}
		}); 
	}

	public void addActionButtonListener(ActionListener listener){
		actionButton.addActionListener(listener);
	}
	
	public void addBackListener(ActionListener listener){
		backButton.addActionListener(listener);
	}
	
	public void addCompetitionListListener(MouseAdapter listener){
		competitionsList.addMouseListener(listener);
	}
	
	public void fillCompetitions(CompetitionModel[] comp) {
		competitionsList.setListData(comp);
	}
	
	public void fillPlayerList(AccountModel[] usersFromCompetition) {
		playerList.setListData(usersFromCompetition);
	}
	
	public void clearCompList() {
		competitionsList.removeAll();
	}
	
	public void clearPlayerList() {
		playerList.removeAll();
	}

	public int getIndex(){
		return competitionsList.getSelectedIndex();
	}

	public JList<CompetitionModel> getList(){
		return competitionsList;
	}
	
	
	/* public void fillAvailableCompetitions(CompetitionModel[] availableCompetitions) {
		competitionsList.setListData(availableCompetitions);
		actionButton.setEnabled(true);
	}

	public void fillAllCompetitions(String[] allCompetitions) {
		competitionsList.setListData(allCompetitions);
	}
	*/ 
	
	public CompetitionModel getSelectedCompetition(){
		return competitionsList.getSelectedValue();
	}
	
	public AccountModel getSelectedPlayer(){
		return playerList.getSelectedValue();
	}
	
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	public void removeIndex(int index){
		competitionsList.remove(index);
	}
	
	public void setText(String labelText,String labelText2, String button, boolean visible) {
		playersInCompetitonLabel.setText(labelText2);
		competitionsLabel.setText(labelText);
		actionButton.setText(button);
		actionButton.setVisible(visible);
		revalidate();
	}

	public void changeActionText() {
		actionButton.setText("1 uitdaging per tegenstander");
		revalidate();
	}
	/*/
	Competitie deelnemen
	1 uitdaging per tegenstander
	Verwijderen uit Competitie
	Verwijder Competitie
	Speler uitdagen
	/*/
	public void disableList(){
		playerList.setEnabled(false);
	}
	
	
}
