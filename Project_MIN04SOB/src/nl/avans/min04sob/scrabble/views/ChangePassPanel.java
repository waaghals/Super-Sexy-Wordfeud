package nl.avans.min04sob.scrabble.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import nl.avans.min04sob.scrabble.core.CorePanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

public class ChangePassPanel extends CorePanel{
	
	private JLabel oldPassLabel, newPass1Label, newPass2Label, oldPassResult, newPass1Result, newPass2Result;
	private JPasswordField oldPass, newPass1, newPass2; 
	private JButton btnCancel;
	private JButton btnChange;
	
	public ChangePassPanel(){
		setPreferredSize(new Dimension(365,180));
		setLayout(new MigLayout("", "[115.00px][115.00px][115.00px]", "[20px][20px][20px][][]"));
		oldPassLabel = new JLabel("Old password :");
		add(oldPassLabel, "cell 0 0,alignx center,aligny center");
		oldPass = new JPasswordField(11);
		add(oldPass, "cell 1 0,growx,aligny center");
		oldPassResult = new JLabel("");
		add(oldPassResult, "cell 2 0,alignx center,aligny center");
		newPass1Label = new JLabel("New pass :");
		add(newPass1Label, "cell 0 1,alignx center,aligny center");
		newPass1 = new JPasswordField(11);
		add(newPass1, "cell 1 1,growx,aligny center");
		newPass1Result = new JLabel("");
		add(newPass1Result, "cell 2 1,alignx center,aligny center");
		newPass2Label = new JLabel("Confirm :");
		add(newPass2Label, "cell 0 2,alignx center,aligny center");
		newPass2 = new JPasswordField(11);
		add(newPass2, "cell 1 2,growx,aligny center");
		newPass2Result = new JLabel("");
		add(newPass2Result, "cell 2 2,alignx center,aligny center");
		
		btnCancel = new JButton("Cancel");
		add(btnCancel, "cell 0 4,growx");
		
		btnChange = new JButton("Change");
		add(btnChange, "cell 2 4,growx");
	}

	public void addCancelActionListener(ActionListener listener){
		btnCancel.addActionListener(listener);
	}
	
	public void addChangeActionListener(ActionListener listener){
		btnChange.addActionListener(listener);
	}
	
	public String getOldPass(){
		return new String(oldPass.getPassword());
	}
	
	public String getNewPass1(){
		return new String(newPass1.getPassword());
	}
	
	public String getNewPass2(){
		return new String(newPass2.getPassword());
	}
	
	public void setOldPassGood(boolean good, String discription){
		if(good){
			oldPass.setBackground(Color.WHITE);
			oldPassResult.setText(discription);
		}else{
			oldPass.setBackground(Color.RED);
			oldPassResult.setText(discription);
		}
		
	}
	
	public void setNewPass1Good(boolean good, String discription){
		if(good){
			newPass1.setBackground(Color.WHITE);
			newPass1Result.setText(discription);
		}else{
			newPass1.setBackground(Color.RED);
			newPass1Result.setText(discription);
		}
		
	}
	
	public void setNewPass2Good(boolean good, String discription){
		if(good){
			newPass2.setBackground(Color.WHITE);
			newPass2Result.setText(discription);
		}else{
			newPass2.setBackground(Color.RED);
			newPass2Result.setText(discription);
		}
		
	}
	
	public void passwordChange(){
		btnChange.setEnabled(false);
	}
	public void modelPropertyChange(PropertyChangeEvent evt) {
	}
}