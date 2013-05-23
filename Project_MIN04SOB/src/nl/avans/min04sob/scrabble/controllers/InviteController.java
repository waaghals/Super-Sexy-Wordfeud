package nl.avans.min04sob.scrabble.controllers;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.views.InviteView;

public class InviteController extends CoreController {

	private InviteView inviteView;
	private JFrame window;
	
	public InviteController(){
		initialize();
	}
	
	@Override
	public void initialize() {
		window = new JFrame();
		
		inviteView = new InviteView();
		addView(inviteView);
		
		window.add(inviteView);
	}

	@Override
	public void addListeners() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] arg){
		new InviteView();
		
	}
}
