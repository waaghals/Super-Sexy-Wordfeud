package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.ChatModel;
import nl.avans.min04sob.scrabble.views.ChatPanel;

public class ChatController extends CoreController{
private ChatPanel chatpanel;
private ChatModel chatmodel;
private JFrame frame;
private final int xsizechat, ysizechat , checkmessagestimer;

	public ChatController(){
		xsizechat = 150;
		ysizechat = 250;
		checkmessagestimer = 10;
	chatpanel = new ChatPanel(xsizechat,ysizechat);
	chatmodel = new ChatModel(1,1);
	frame = new JFrame();
	frame.add(chatpanel);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();
	frame.setVisible(true);
	
	addView(chatpanel);
	this.startcheckingmessages();
	
	
	
	
	
	
	chatpanel.addlistenerchatfield(new KeyListener(){

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				send();
				
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	});
	chatpanel.addlistenerchatsendbutton(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			send();
			

			}
			
		
		
	});
}
	public void send(){
if (!chatpanel.getChatfieldsend().getText().equals("") && !chatpanel.getChatfieldsend().getText().equals(" ")) {
			
			chatmodel.send(chatpanel.getChatfieldsend().getText());
			chatpanel.getChatfieldsend().setText("");
		}
	}
	public void startcheckingmessages(){
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
		    chatpanel.getChatfield().setText(chatpanel.getChatfield().getText() +chatmodel.newmessages());
		  }
		}, 0, this.checkmessagestimer, TimeUnit.SECONDS);
	}
}
