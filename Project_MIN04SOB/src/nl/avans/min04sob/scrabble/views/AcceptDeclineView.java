package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.core.mvc.CorePanel;

public class AcceptDeclineView extends CorePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8789911575495095451L;
	private JList<String> wordList;
	private JFrame myFrame;
	private JButton backButton;
	private JButton acceptButton;
	private JButton deniedButton;
	private JLabel requestedWordLabel;
	private JScrollPane scrollPane;

	public AcceptDeclineView() {
		setLayout(new MigLayout("", "[80px:100px][80px:100px,grow][60px:75px]",
				"[][100px:200px:500px,grow][][100px:150px:100px,grow][100px:100px:25px]"));

		wordList = new JList<String>();
		wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		requestedWordLabel = new JLabel("Voorgestelde woorden");
		add(requestedWordLabel, "cell 0 0 2 1");

		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 2 3,grow");

		scrollPane.setViewportView(wordList);

		acceptButton = new JButton("Accepteren");
		acceptButton.setEnabled(false);
		add(acceptButton, "cell 0 4,alignx left");

		deniedButton = new JButton("Weigeren");
		add(deniedButton, "cell 1 4,alignx right");
		deniedButton.setEnabled(false);

		myFrame = new JFrame();
		myFrame.setTitle("Voorgestelde woorden");
		myFrame.setAlwaysOnTop(true);
		myFrame.setContentPane(this);
		backButton = new JButton("Terug");
		add(backButton, "cell 2 4");
		myFrame.pack();
		myFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		myFrame.setVisible(true);
	}

	public void addAcceptActionListener(ActionListener listener) {
		acceptButton.addActionListener(listener);
	}

	public void addBackActionListener(ActionListener listener) {
		backButton.addActionListener(listener);
	}

	public void addDeniedActionListener(ActionListener listener) {
		deniedButton.addActionListener(listener);
	}

	public void addListSelectionListener(ListSelectionListener listener){
		wordList.addListSelectionListener(listener);
	}
	
	public void fillWordList(String[] list) {
		wordList.removeAll();
		wordList.setListData(list);
	}

	public String getSelectedWord() {
		String returnObject = null;
		returnObject = wordList.getSelectedValue().toString();

		return returnObject;
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case Event.NEWWORD:
			// TODO add to list
			break;

		default:
			break;
		}
	}

	public void removeWindow() {
		myFrame.dispose();
	}
	
	public void setButtonsEnabled(boolean enabled){
		acceptButton.setEnabled(enabled);
		deniedButton.setEnabled(enabled);
	}
}
