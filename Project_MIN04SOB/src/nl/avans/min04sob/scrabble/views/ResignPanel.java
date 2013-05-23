package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import nl.avans.min04sob.scrabble.core.CorePanel;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class ResignPanel extends CorePanel {
	
	private JButton btnYes;
	private JButton btnNo;
	
	
	public ResignPanel() {
		this.setName("");
		setLayout(new MigLayout("", "[][][134.00px][134.00px][]", "[14px][][][]"));
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to resign?");
		add(lblAreYouSure, "cell 2 0 2 1,alignx left,aligny top");
		
		btnYes = new JButton("Yes");
		add(btnYes, "cell 2 2,alignx center");
		
		btnNo = new JButton("No");
		add(btnNo, "cell 3 2,alignx center");
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	public void addResignActionListener(ActionListener listener) {
		btnYes.addActionListener(listener);
	}
	
	public void addNoResignActionListener(ActionListener listener) {
		btnNo.addActionListener(listener);
	}
}
