package nl.avans.min04sob.scrabble.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import nl.avans.min04sob.scrabble.core.CorePanel;


public class ChatPanel extends CorePanel {
	private JScrollPane chatslider;
	private JTextArea chatfield;
	private JTextField chatfieldsend;
	private JButton chatsendbutton;
	
	
	public ChatPanel(int xchatsize, int ychatsize) {
		chatfield = new JTextArea();
		chatfieldsend = new JTextField();
		chatsendbutton = new JButton();
		chatslider = new JScrollPane(chatfield);
		
		
		
		
		chatsendbutton.setText("Send");
		chatsendbutton.setPreferredSize(new Dimension(75,25));
		chatsendbutton.setMaximumSize(chatsendbutton.getPreferredSize());
		
		chatfieldsend.setPreferredSize(new Dimension(125, 25));
		chatfieldsend.setMaximumSize(chatfieldsend.getPreferredSize());
		
		chatfield.setWrapStyleWord(true);
		chatfield.setLineWrap(true);
		chatfield.setEditable(false);
		
		chatslider.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatslider.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatslider.setPreferredSize(new Dimension(xchatsize,ychatsize));
		
		

		this.setLayout(new BorderLayout());
		this.add(chatslider, BorderLayout.NORTH);
		this.add(chatfieldsend, BorderLayout.CENTER);
		this.add(chatsendbutton, BorderLayout.EAST);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	public void addlistenerchatfield(KeyListener key){
		chatfieldsend.addKeyListener(key);
	}
	public void addlistenerchatsendbutton(ActionListener action){
		chatsendbutton.addActionListener(action);
	}

	public JTextArea getChatfield() {
		return chatfield;
	}

	public void setChatfield(JTextArea chatfield) {
		this.chatfield = chatfield;
	}

	public JTextField getChatfieldsend() {
		return chatfieldsend;
	}

	public void setChatfieldsend(JTextField chatfieldsend) {
		this.chatfieldsend = chatfieldsend;
	}

	
	
}