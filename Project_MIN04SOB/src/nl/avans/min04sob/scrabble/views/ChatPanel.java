package nl.avans.min04sob.scrabble.views;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.core.Event;

public class ChatPanel extends CorePanel {

	private JTextField chatFieldSend;
	private JButton chatSendButton;
	private JTextArea chatField;
	private JScrollPane chatScroll;

	public ChatPanel() {
		
		setLayout(new MigLayout("", "[::300px][300px][100px][100px]", "[::300px][400px][100px:150px:200px][30px]"));
		chatField = new JTextArea();
		chatField.setEnabled(false);
		chatField.setWrapStyleWord(true);
		chatField.setLineWrap(true);
		chatField.setDisabledTextColor(Color.BLACK);
		chatScroll = new JScrollPane(chatField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		chatSendButton = new JButton();
		chatSendButton.setText("Verstuur");
		add(chatScroll, "cell 0 0 4 3,grow");
		chatFieldSend = new JTextField();
		add(chatFieldSend, "cell 0 3 3 1,grow");
		add(chatSendButton, "cell 3 3,grow");
		chatFieldSend.setEnabled(false);
	}


	public void addListenerChatField(KeyListener key) {
		chatFieldSend.addKeyListener(key);
	}

	public void addListenerChatSendButton(ActionListener action) {
		chatSendButton.addActionListener(action);
	}

	public void addToChatField(String message) {
		chatField.append(message);
		chatField.setCaretPosition(chatField.getDocument().getLength());
	}

	public void empty() {
		chatField.setText("");
	}

	public JTextArea getChatField() {
		return chatField;
	}

	public JTextField getChatFieldSend() {
		return chatFieldSend;
	}

	public synchronized String getChatFieldSendText() {
		return chatFieldSend.getText();
	}

	public String getChatFieldText() {
		return chatField.getText();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void modelPropertyChange(PropertyChangeEvent evt) {
		
		switch (evt.getPropertyName()) {
		case Event.CHATUPDATE:
			ArrayList<String> messages = (ArrayList<String>) evt.getNewValue();
			for (String string : messages) {
				addToChatField(string);
			}
			break;
		case Event.LOGIN:
			chatFieldSend.setEnabled(true);
			break;
		case Event.LOGOUT:
			chatFieldSend.setEnabled(false);
			break;
		default:
			break;
		}
	}

	public void setChatField(JTextArea chatfield) {
		this.chatField = chatfield;
	}

	public void setChatFieldSend(JTextField chatfieldsend) {
		this.chatFieldSend = chatfieldsend;
	}

	public void setChatFieldSendText(String message) {
		chatFieldSend.setText(message);
	}


	public void setChatFieldText(String text) {
		chatField.setText(text);
	}
}
