package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.mvc.CorePanel;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.CompetitionModel;

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
		
		setLayout(new MigLayout("", "[200px:220px:260px][155px:160.00px:170px]", "[20px:20px:20px][200px:200px:200px][25px:25px:25px]"));
		
		competitionsLabel = new JLabel();
		add(competitionsLabel, "cell 0 0,alignx left");
		
		playersInCompetitonLabel = new JLabel();
		add(playersInCompetitonLabel, "cell 1 0,alignx right");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1,grow");
		
		competitionsList = new JList<CompetitionModel>();
		competitionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(competitionsList);
		
		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 1 1,grow");
		
		playerList = new JList<AccountModel>();
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(playerList);
		
		backButton = new JButton("Terug");
		add(backButton, "cell 0 2,alignx left");
		
		actionButton = new JButton();
		actionButton.setEnabled(false); //test

		add(actionButton, "cell 1 2,alignx right");
		
		competitionsList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				if(actionButton.getText().equals("Competitie deelnemen")){
				actionButton.setEnabled(true);	
				}
			}
		}); 
		
		playerList.addListSelectionListener(new ListSelectionListener(){
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
		playerList.enable(false);
	}
	
	
}
