package nl.avans.min04sob.scrabble.views;

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

public class ChatPanel extends CorePanel {

	private JTextField chatFieldSend;
	private JButton chatSendButton;
	private JTextArea chatField;
	private JScrollPane chatScroll;

	public ChatPanel(int xchatsize, int ychatsize) {
		setLayout(new MigLayout("", "[100px:1000px][100px:100px:100px][100px:100px:100px][100px:100px:100px]", "[100px:1000px][100px:150px:200px][100px:150px:200px][30px:30px]"));
		chatField = new JTextArea();
		chatScroll = new JScrollPane(chatField);

		chatSendButton = new JButton();
		chatSendButton.setText("Verstuur");

		add(chatScroll, "cell 0 0 4 3,grow");
		chatFieldSend = new JTextField();
		add(chatFieldSend, "cell 0 3 3 1,grow");
		add(chatSendButton, "cell 3 3,grow");
	}

	@Override
	@SuppressWarnings("unchecked")
	public void modelPropertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("chatupdate")) {

			ArrayList<String> messages = (ArrayList<String>) evt.getNewValue();
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

	public void addToChatField(String message) {
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