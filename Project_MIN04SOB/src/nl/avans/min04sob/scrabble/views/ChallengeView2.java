package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import nl.avans.min04sob.scrabble.core.CorePanel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
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
	private JScrollPane scrollPane;
	private JList challengeList;

	
	
	public ChallengeView2() {
		setLayout(new MigLayout("", "[150px:150px:150px,grow][100px:100px:100px,grow]", "[14px][40px:40px:40px][40px:40px:40px,grow][grow][40px:40px:40px,grow]"));
		
		challengeLabel = new JLabel("Uitdagingen");
		add(challengeLabel, "cell 0 0 2 1,alignx left,aligny top");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 1 3,grow");
		
		challengeList = new JList();
		scrollPane.setViewportView(challengeList);
		
		acceptButton = new JButton("Accepteer");
		add(acceptButton, "cell 1 1,alignx center");
		
		declineButton = new JButton("Negeer");
		add(declineButton, "cell 1 2,alignx center");
		
		backButton = new JButton("Ga terug");
		add(backButton, "cell 0 4");
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
	
	
	
	
	
	
	
	
	
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
