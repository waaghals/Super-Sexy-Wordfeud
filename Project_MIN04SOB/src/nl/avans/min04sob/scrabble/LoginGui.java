package nl.avans.min04sob.scrabble;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import nl.avans.min04sob.scrabble.core.Dbconnect;

public class LoginGui extends JFrame{

	private JPanel loginscreen, registerscreen;
	private JLabel result;
	private JTextField usernameField;
	private JPasswordField passwordField,passwordField1,passwordField2;
	
	public LoginGui(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		showLogin();
		pack();
		setVisible(true);
		
	}
	
	public void showLogin(){
		loginscreen = new JPanel();
		loginscreen.setLayout(null);
		loginscreen.setPreferredSize(new Dimension(200,300));
		
		result = new JLabel("");
		JLabel usernameLabel = new JLabel("Username :");
		usernameField = new JTextField();
		JLabel passwordLabel = new JLabel("Password :");
		passwordField = new JPasswordField();
		JButton loginButton = new JButton("Login");
		JButton registerButton = new JButton("Register");
		
		result.setBounds(25, 5, 150, 25);
		usernameLabel.setBounds(25, 40, 150, 30);
		usernameField.setBounds(25, 75, 150, 30);
		passwordLabel.setBounds(25, 110, 150, 30);
		passwordField.setBounds(25, 145, 150, 30);
		loginButton.setBounds(25, 180, 150, 30);
		registerButton.setBounds(25, 215, 150, 30);
		
		loginscreen.add(result);
		loginscreen.add(usernameLabel);
		loginscreen.add(usernameField);
		loginscreen.add(passwordLabel);
		loginscreen.add(passwordField);
		loginscreen.add(loginButton);
		loginscreen.add(registerButton);
		
		add(loginscreen);
		
		registerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				remove(loginscreen);
				showRegister();
				revalidate();
				repaint();
				pack();
			}
		});
	}

	public void showRegister(){
		registerscreen = new JPanel();
		registerscreen.setLayout(null);
		registerscreen.setPreferredSize(new Dimension(200,300));
		
		result = new JLabel("");
		JLabel usernameLabel = new JLabel("Username :");
		usernameField = new JTextField();
		JLabel passwordLabel1 = new JLabel("Password :");
		passwordField1 = new JPasswordField();
		JLabel passwordLabel2 = new JLabel("Password :");
		passwordField2 = new JPasswordField();
		JButton cancel = new JButton("Cancel");
		JButton register = new JButton("Register");
		
		
		result.setBounds(20, 5, 160, 25);
		usernameLabel.setBounds(20, 35, 160, 25);
		usernameField.setBounds(20, 65, 160, 25);
		passwordLabel1.setBounds(20, 95, 160, 25);
		passwordField1.setBounds(20,125, 160, 25);
		passwordLabel2.setBounds(20,155, 160, 25);
		passwordField2.setBounds(20,185, 160, 25);
		register.setBounds(20, 215, 160, 25);
		cancel.setBounds(20, 245, 160, 25);
		
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				remove(registerscreen);
				showLogin();
				revalidate();
				repaint();
				pack();
			}
		});
		register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					if(validateRegister()){
						Dbconnect.getInstance().query("INSERT INTO account(username, account.password) VALUES('"+usernameField.getText()+"','"+passwordField1.getText()+"');");
						remove(registerscreen);
						showLogin();
						revalidate();
						repaint();
						pack();
								
								}
				}catch(Exception ex){}
			}
		});
		
		registerscreen.add(result);
		registerscreen.add(usernameLabel);
		registerscreen.add(usernameField);
		registerscreen.add(passwordLabel1);
		registerscreen.add(passwordField1);
		registerscreen.add(passwordLabel2);
		registerscreen.add(passwordField2);
		registerscreen.add(register);
		registerscreen.add(cancel);
		
		
		add(registerscreen);
	}
	public boolean validatelogin(){
		ResultSet login =Dbconnect.getInstance().select("SELECT password FROM account WHERE username ='"+usernameField.getText()+"';");
		try{
			if(login.next()){
				if(passwordField.getText().equals(login.getString(1))){
					return true;
				}else{
					result.setText("Wrong password");
					return false;
				}
			}else{
				result.setText("Username does not exist");
				return false;
			}
		
		}catch(Exception e){
			return false;
		}
	}
	public boolean validateRegister(){
		ResultSet check = Dbconnect.getInstance().select("SELECT * FROM account WHERE username='"+usernameField.getText()+"';");
		try{
			if(usernameField.getText().length()<5){
				result.setText("Username is to short");
				return false;
			}else if(usernameField.getText().length()>11){
				result.setText("Username is to long");
				return false;
			}else{
				if(check.next()){
					result.setText("This username is used");
					return false;
				}else{
					if(passwordField1.getText().length()<5){
						result.setText("Password is to short");
						return false;
					}else if(passwordField1.getText().length()>11){
						result.setText("Password is to long");
						return false;
					}else{
						if(passwordField1.getText().equals(passwordField2.getText())){
							return true;
						}else{
							result.setText("The passwords dont match");
							return false;
						}
					}
				}
			}
			
		}catch(Exception ex){
			return false;
		}
	}
}
