package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import nl.avans.min04sob.scrabble.core.CorePanel;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class ResignPanel extends CorePanel {
	
	private JButton yesButton;
	private JButton noButton;
	
	
	public ResignPanel() {
		this.setName("");
		setLayout(new MigLayout("", "[][][134.00px][134.00px][]", "[14px][][][]"));
		
		JLabel areYouSureLabel = new JLabel("weet je zeker dat je wilt opgeven?");
		add(areYouSureLabel, "cell 2 0 2 1,alignx left,aligny top");
		
		yesButton = new JButton("Yes");
		add(yesButton, "cell 2 2,alignx center");
		
		noButton = new JButton("No");
		add(noButton, "cell 3 2,alignx center");
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	public void addResignActionListener(ActionListener listener) {
		yesButton.addActionListener(listener);
	}
	
	public void addNoResignActionListener(ActionListener listener) {
		noButton.addActionListener(listener);
	}
}
