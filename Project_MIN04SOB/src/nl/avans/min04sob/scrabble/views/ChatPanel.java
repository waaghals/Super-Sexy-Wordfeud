package nl.avans.min04sob.scrabble.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import nl.avans.min04sob.scrabble.core.CorePanel;

public class ChatPanel extends CorePanel {

	private JScrollPane chatSlider;
	private JTextArea chatField;
	private JTextField chatFieldSend;
	private JButton chatSendButton;

	public ChatPanel(int xchatsize, int ychatsize) {
		chatField = new JTextArea();
		chatFieldSend = new JTextField();
		chatSendButton = new JButton();
		chatSlider = new JScrollPane(chatField);

		chatSendButton.setText("Send");
		chatSendButton.setPreferredSize(new Dimension(75, 25));
		chatSendButton.setMaximumSize(chatSendButton.getPreferredSize());

		chatFieldSend.setPreferredSize(new Dimension(125, 25));
		chatFieldSend.setMaximumSize(chatFieldSend.getPreferredSize());

		chatField.setWrapStyleWord(true);
		chatField.setLineWrap(true);
		chatField.setEditable(false);

		chatSlider
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatSlider
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatSlider.setPreferredSize(new Dimension(xchatsize, ychatsize));

		this.setLayout(new BorderLayout());
		this.add(chatSlider, BorderLayout.NORTH);
		this.add(chatFieldSend, BorderLayout.CENTER);
		this.add(chatSendButton, BorderLayout.EAST);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void modelPropertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("chat")){

			ArrayList<String> messages = (ArrayList<String>) evt.getNewValue();
			chatField.setText("");
			for (String string : messages) {
				addToChatField(string);
			}
		}

	}

	public void addListenerChatField(KeyListener key) {
		chatFieldSend.addKeyListener(key);
	}

	public void addListenerChatSendButton(ActionListener action) {
		chatSendButton.addActionListener(action);
	}

	public JTextArea getChatField() {
		return chatField;
	}

	public void setChatField(JTextArea chatfield) {
		this.chatField = chatfield;
	}

	public JTextField getChatFieldSend() {
		return chatFieldSend;
	}

	public void setChatFieldSend(JTextField chatfieldsend) {
		this.chatFieldSend = chatfieldsend;
	}
	
	public void addToChatField(String message){
		chatField.append(message);
		chatField.setCaretPosition(chatField.getDocument().getLength());
	}

	public void setChatFieldText(String text) {
		chatField.setText(text);
	}

	public String getChatFieldText() {
		return chatField.getText();
	}

	public String getChatFieldSendText() {
		return chatFieldSend.getText();
	}
	
	public void setChatFieldSendText(String message) {
		chatFieldSend.setText(message);
	}
}