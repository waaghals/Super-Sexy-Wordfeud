package nl.avans.min04sob.mvcvoorbeeld;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nl.avans.min04sob.scrabble.core.CoreConstraint;
import nl.avans.min04sob.scrabble.core.CorePanel;

public class ChatPanel extends CorePanel {

	JButton generatorButton;
	JTable chatTable;
	JScrollPane scrollPane;

	public ChatPanel() {
		chatTable = new JTable();
		scrollPane = new JScrollPane(chatTable);
		
		add(scrollPane, new CoreConstraint(10, 10, 0, 0));

		generatorButton = new JButton();
		generatorButton.setText("Add Message");
		add(generatorButton, new CoreConstraint(10, 1, 0, 11));
	}

	public void addGeneratorButtonActionListner(ActionListener listener) {
		generatorButton.addActionListener(listener);
	}

	public void setGeneratorButtonText(String text) {
		generatorButton.setText(text);
	}
	
	public void setChatTableModel(ChatTableModelPATRICKVOORBEEELD model){
		chatTable.setModel(model);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "chatmessage":
			repaint();
			chatTable.revalidate();
			
			
			JScrollBar vertical = scrollPane.getVerticalScrollBar();
			vertical.setValue( vertical.getMaximum());
			break;

		default:
			break;
		}
	}

}
