package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.Dbconnect;
import nl.avans.min04sob.scrabble.models.MainScreenUserModel;
import nl.avans.min04sob.scrabble.views.MainScreenChangePass;
import nl.avans.min04sob.scrabble.views.MainScreenTotalPanel;
import nl.avans.min04sob.scrabble.views.MainScreenUserInfoPanel;

public class MainScreenUserController extends CoreController{
	
	private MainScreenUserModel msum;
	private MainScreenTotalPanel mstp;
	private MainScreenUserInfoPanel msuip;
	private MainScreenChangePass mscp;
	private JFrame frame;
	
	public MainScreenUserController(String username, int playerid, int moderator){
		msum = new MainScreenUserModel(username, playerid, moderator);
		mstp = new MainScreenTotalPanel();
		msuip = new MainScreenUserInfoPanel();
		mscp = new MainScreenChangePass();
		frame = new JFrame();
		msuip.setUsername(username);
		if(moderator==1){
			msuip.setAdmin();
		}
		
		msuip.addActionListenerLogout(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				logout();
			}	
		});
		
		msuip.addActionListenerChangePass(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				changePass();
			}	
		});
		
		msuip.addActionListenerAdmin(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		mstp.addTopPanel(msuip);
		
		frame.add(mstp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private void logout(){
		LoginController lc = new LoginController();
		frame.dispose();
	}
	
	private void changePass(){
		mstp.addCenterPanel(mscp);
	}
}
