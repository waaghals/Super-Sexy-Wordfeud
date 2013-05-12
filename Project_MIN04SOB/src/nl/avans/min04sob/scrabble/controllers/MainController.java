package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.views.ChangePassPanel;
import nl.avans.min04sob.scrabble.views.MenuView;
import nl.avans.min04sob.scrabble.views.TotalPanel;
import nl.avans.min04sob.scrabble.views.UserInfoPanel;

public class MainController extends CoreController {

	private TotalPanel mstp;
	private UserInfoPanel msuip;
	private ChangePassPanel mscp;
	private JFrame frame;
	private MenuView menu;
	private AccountModel account;

	public MainController(String username, int playerid, int moderator) {
		
		
		msuip.setUsername(username);
		if (moderator == 1) {
			msuip.setAdmin();
		}

		
		addView(menu);
		addModel(account);
		
		frame.setJMenuBar(menu);

		mstp.addTopPanel(msuip);

		frame.add(mstp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
	
	@Override
	public void initialize() {
		mstp = new TotalPanel();
		msuip = new UserInfoPanel();
		mscp = new ChangePassPanel();
		menu = new MenuView();
		account = new AccountModel();

		frame = new JFrame();
		
	}

	@Override
	public void addListeners() {
		msuip.addActionListenerLogout(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account.logout();
			}
		});

		msuip.addActionListenerChangePass(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePass();
			}
		});

		msuip.addActionListenerAdmin(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		menu.addChangePassItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePass();
			}
		});
		
		menu.addLoginItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController loginC = new LoginController();
				loginC.addView(menu);
			}
		});
		
		menu.addLogoutItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account.logout();
			}
		});
	}

	private void changePass() {
		mstp.addCenterPanel(mscp);
	}

	
}
