package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import nl.avans.min04sob.scrabble.core.CorePanel;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResignPanel extends CorePanel {
	public ResignPanel() {
		this.setName("");
		setLayout(new MigLayout("", "[][][134.00px][134.00px][]", "[14px][][][]"));
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to resign?");
		add(lblAreYouSure, "cell 2 0 2 1,alignx left,aligny top");
		
		JButton btnNewButton = new JButton("Yes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(btnNewButton, "cell 2 2,alignx center");
		
		JButton btnNo = new JButton("No");
		add(btnNo, "cell 3 2,alignx center");
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
