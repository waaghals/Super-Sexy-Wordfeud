package nl.avans.min04sob.scrabble.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import nl.avans.min04sob.scrabble.core.CorePanel;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;

public class ChallengeView2 extends CorePanel {

	private JButton acceptButton;
	private JButton declineButton;
	private JButton backButton;
	private JLabel challengeLabel;
	private JPanel challengeList;
	private JScrollPane scrollPane;
	final ArrayList<JCheckBox> checkbox;
	private String selectedChallenge="";
	ButtonGroup buttongroup = new ButtonGroup();
	
	public ChallengeView2() {
		 
		setLayout(new MigLayout("", "[150px:150px:150px,grow][100px:100px:100px,grow]", "[14px][40px:40px:40px][40px:40px:40px,grow][grow][140px:40px:140px,grow]"));
		checkbox = new ArrayList<JCheckBox>();
		challengeLabel = new JLabel("Uitdagingen");
		add(challengeLabel, "cell 0 0 2 1,alignx left,aligny top");
		
		acceptButton = new JButton("Accepteer");
		acceptButton .setEnabled(false);
		add(acceptButton, "cell 1 1,alignx center");
		
		declineButton = new JButton("Weiger");
		add(declineButton, "cell 1 2,alignx center");
		declineButton.setEnabled(false);
		
		backButton = new JButton("Ga terug");
		add(backButton, "cell 0 10");
	}

	public void addActionListenerAccept(ActionListener listener) {
		acceptButton.addActionListener(listener);
	}
	
	public void addActionListenerDecline(ActionListener listener) {
		declineButton.addActionListener(listener);
	}
	
	public void addActionListenerBack(ActionListener listener) {
		backButton.addActionListener(listener);
	}
	
	public void showChallenge(){
		challengeList = new JPanel();		
		challengeList.setLayout(new BoxLayout(challengeList,BoxLayout.PAGE_AXIS));
		this.add(challengeList, "cell 0 1 2 1");
		if(checkbox.size()==0)
		{
			challengeList.add(new JLabel("No challenges"));
		}
		else
		{
			declineButton.setEnabled(true);
			acceptButton.setEnabled(true);
			int index=0;
			while(index<checkbox.size()){
				add(checkbox.get(index), "cell 0 1 0 0,grow");
				buttongroup.add(checkbox.get(index));
				final int a = index;
				checkbox.get(index).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setSelectedChallenge(checkbox.get(a).getText());
				}
			});
				challengeList.add(checkbox.get(index));
				index++;
			}
		} 
	}
	
	public void receiveChallenge(String challenge)
	{
		checkbox.add(new JCheckBox(challenge));
	}
	
	public void setSelectedChallenge( String challenge)
	{
		selectedChallenge = challenge;
	}
	
	public String getSelectedChallenge()
	{
		return selectedChallenge;
	}
	
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
