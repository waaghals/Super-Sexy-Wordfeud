package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.MainWindow;
import nl.avans.min04sob.scrabble.views.LoginPanel;
import nl.avans.min04sob.scrabble.views.RegisterPanel;

public class LoginController extends CoreController {
	
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private AccountModel accountModel;
	private JFrame frame;
	private final int maxpasslength, minpasslength,maxuserlength, minuserlength;
	
	public LoginController(){
		
		maxpasslength = 11;
		minpasslength = 5;
		maxuserlength = 11;
		minuserlength = 5;
		frame = new JFrame();
		
		loginPanel = new LoginPanel();
		accountModel = new AccountModel();
		registerPanel = new RegisterPanel();
		
		frame.add(loginPanel);
		
		addView(registerPanel);
		addView(loginPanel);
		addModel(accountModel);
		
		
		
		loginPanel.addActionListenerLogin(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				checkLogin();
			}
		});
		
		loginPanel.addActionListenerRegister(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loginToRegister();
			}
		});
		
		registerPanel.addActionListenerCancel(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				registerToLogin();
			}
		});
		
		registerPanel.addActionListenerRegister(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tryToRegister();
			}
		});
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void checkLogin() {
		accountModel.login(loginPanel.getUsername(), loginPanel.getPassword());
		if(accountModel.getUsername() != null){
			
		}else{
			loginPanel.setResult("Wrong username/password");
		}
	}
	
	private void tryToRegister(){
		if(registerPanel.getUsername().length() < minuserlength){
			registerPanel.setResult("Username is to short");
			return;
		}else if(registerPanel.getUsername().length() > maxuserlength){
			registerPanel.setResult("Username is to long");
			return;
		}else{
			if(accountModel.checkUsernameAvailable(registerPanel.getUsername())){
				if(registerPanel.getPassword1().length < minpasslength){
					registerPanel.setResult("Password is to short");
					return;
				}else if(registerPanel.getPassword1().length > maxpasslength){
					registerPanel.setResult("Password is to long");
					return;
				}else{
					if(Arrays.equals(registerPanel.getPassword1(), registerPanel.getPassword2())){
						accountModel.registerAccount(registerPanel.getUsername(), registerPanel.getPassword1());
						registerPanel.setResult("");
						System.out.println("true");//TODO
						return;
					}else{
						registerPanel.setResult("Passwords don't match");
						return;
					}
				}
			}else{
				registerPanel.setResult("Username already used");
				return;
			}
		}
	}
	
	private void loginToRegister(){
		frame.remove(loginPanel);
		frame.add(registerPanel);
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}
	
	private void registerToLogin(){
		frame.remove(registerPanel);
		frame.add(loginPanel);
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}
}
