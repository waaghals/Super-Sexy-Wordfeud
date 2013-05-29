package nl.avans.min04sob.scrabble.views;

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
	public ChallengeView2() {
		setLayout(new MigLayout("", "[150px:150px:150px,grow][100px:100px:100px,grow]", "[14px][40px:40px:40px][40px:40px:40px,grow][grow][40px:40px:40px,grow]"));
		
		JLabel challengeLabel = new JLabel("Uitdagingen");
		add(challengeLabel, "cell 0 0 2 1,alignx left,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 1 3,grow");
		
		JList challengeList = new JList();
		scrollPane.setViewportView(challengeList);
		
		JButton acceptButton = new JButton("Accepteer");
		add(acceptButton, "cell 1 1,alignx center");
		
		JButton declineButton = new JButton("Negeer");
		add(declineButton, "cell 1 2,alignx center");
		
		JButton backButton = new JButton("Ga terug");
		add(backButton, "cell 0 4");
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
