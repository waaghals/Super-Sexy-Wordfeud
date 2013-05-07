package nl.avans.min04sob.scrabble;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Chat extends JPanel {
	private JScrollPane chatslider;
	private JTextArea chatfield;
	private JTextField chatfieldsend;
	private JButton chatsendbutton;
	
	public Chat() {
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
		chatslider.setPreferredSize(new Dimension(250, 200));
		

		this.addlisteners();
		this.setLayout(new BorderLayout());
		this.add(chatslider, BorderLayout.NORTH);
		this.add(chatfieldsend, BorderLayout.CENTER);
		this.add(chatsendbutton, BorderLayout.EAST);
	}

	public void addchatmessages(String newmessage) {
		if (newmessage != null || newmessage != "") {
			if (!chatfield.getText().equals("")) {
				chatfield.setText(chatfield.getText() + "\n" + newmessage);
			} else {
				chatfield.setText(newmessage);
			}
		}
	}

	private void addlisteners() {
		chatfieldsend.addKeyListener(new KeyListener() {

			
			public void keyPressed(KeyEvent arg0) {
				

			}

			
			public void keyReleased(KeyEvent arg0) {
			
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					send();

				}
			}

			
			public void keyTyped(KeyEvent arg0) {
				

			}

		});
		this.chatsendbutton.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				send();

			}

		});
	}

	public void send() {
		if (!chatfieldsend.getText().equals("") && !chatfieldsend.getText().equals(" ")) {
			
			addchatmessages(chatfieldsend.getText());
			chatfieldsend.setText("");
		
		}
	}
}