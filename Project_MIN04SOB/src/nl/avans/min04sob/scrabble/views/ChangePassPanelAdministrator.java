package nl.avans.min04sob.scrabble.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.models.AccountModel;

public class ChangePassPanelAdministrator extends CorePanel{
 
	private JLabel NamePass;
	private JTextField Newpass;
	private JLabel errorLabel;
	private JButton back;
	private JButton change;
	private JList<String> accountList;
	private JScrollPane scrollPane;
	
	public ChangePassPanelAdministrator(){
		setPreferredSize(new Dimension(460, 220));
		setLayout(new MigLayout("", "[120px][100.00px][115.00px][100px]", "[30px][30px][30px][30px]"));
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 0 2 12,grow");
		accountList = new JList<String>();
		accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(accountList);
		NamePass = new JLabel("nieuw wachtwoord");
		add(NamePass, "cell 0 13,alignx right,aligny center");
		Newpass = new JTextField(11);
		add(Newpass, "cell 1 13 2 1,grow"); 
		errorLabel = new JLabel("");
		add(errorLabel,"cell 1 0,grow");
		back = new JButton("Back");
		add(back, "cell 1 15,grow");
		change =new JButton("Change");
		add(change, "flowy,cell 2 15,grow");
		change.setEnabled(false);
		
		accountList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				change.setEnabled(true);	
			}
			
		});
	}
	

	public void BackaddActionListener(ActionListener listener)
	{
		back.addActionListener(listener);
	}
	public void ChangeaddActionListener(ActionListener listener)
	{
		change.addActionListener(listener);
	}
	
	
	
	public void fillPlayerList(String[] players) {
		accountList.setListData(players);
	
	}
	public String getSelectedPlayer(){
		return accountList.getSelectedValue();
	}
	public String getSelectedNewPass(){
		return Newpass.getText();
	}
	
	public void setText(String msg)
	{
		change.setText(msg);
	}
	
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}

/*/


/*/