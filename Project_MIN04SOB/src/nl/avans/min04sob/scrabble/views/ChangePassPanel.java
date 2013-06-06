package nl.avans.min04sob.scrabble.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.mvc.CorePanel;

public class ChangePassPanel extends CorePanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6251194491019139860L;
	private JLabel oldPassLabel, newPass1Label, newPass2Label, oldPassResult, newPass1Result, newPass2Result;
	private JPasswordField oldPass, newPass1, newPass2; 
	private JButton btnCancel;
	private JButton btnChange;
	
	public ChangePassPanel(){
		setPreferredSize(new Dimension(460, 140));
		setLayout(new MigLayout("", "[120px][100.00px][115.00px][100px]", "[30px][30px][30px][30px]"));
		oldPassLabel = new JLabel("Oud wachtwoord");
		add(oldPassLabel, "cell 0 0,alignx right,aligny center");
		oldPass = new JPasswordField(11);
		add(oldPass, "cell 1 0 2 1,grow");
		oldPassResult = new JLabel("");
		add(oldPassResult, "cell 3 0,alignx center,aligny center");
		newPass1Label = new JLabel("Nieuw wachtwoord");
		add(newPass1Label, "cell 0 1,alignx right,aligny center");
		newPass1 = new JPasswordField(11);
		add(newPass1, "cell 1 1 2 1,grow");
		newPass1Result = new JLabel("");
		add(newPass1Result, "cell 3 1,alignx center,aligny center");
		newPass2Label = new JLabel("Herhaal nieuw wachtwoord");
		add(newPass2Label, "cell 0 2,alignx right,aligny center");
		newPass2 = new JPasswordField(11);
		add(newPass2, "cell 1 2 2 1,grow");
		newPass2Result = new JLabel("");
		add(newPass2Result, "cell 3 2,alignx center,aligny center");
		
		btnChange = new JButton("Veranderen");
		add(btnChange, "cell 0 3,grow");
		
		btnCancel = new JButton("Annuleren");
		add(btnCancel, "flowy,cell 2 3,grow");
	}

	public void addCancelActionListener(ActionListener listener){
		btnCancel.addActionListener(listener);
	}
	
	public void addChangeActionListener(ActionListener listener){
		btnChange.addActionListener(listener);
	}
	
	public void addKeyListenerNewPass1(KeyAdapter listener) {
		newPass1.addKeyListener(listener);
	}
	
	public void addKeyListenerNewPass2(KeyAdapter listener) {
		newPass2.addKeyListener(listener);
	}
	
	public void addKeyListenerOldPass(KeyAdapter listener) {
		oldPass.addKeyListener(listener);
	}
	
	public JPasswordField get1NewPass() {
		return newPass1;
	}
	
	public JPasswordField get2NewPass() {
		return newPass2;
	}
	
	public String getNewPass1(){
		return new String(newPass1.getPassword());
	}
	
	public String getNewPass2(){
		return new String(newPass2.getPassword());
	}
	
	public String getOldPass(){
		return new String(oldPass.getPassword());
	}
	
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
	}
	
	public void passwordChange(){
		btnChange.setEnabled(false);
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
	public void setOldPassGood(boolean good, String discription){
		if(good){
			oldPass.setBackground(Color.WHITE);
			oldPassResult.setText(discription);
		}else{
			oldPass.setBackground(Color.RED);
			oldPassResult.setText(discription);
		}
		
	}
}
