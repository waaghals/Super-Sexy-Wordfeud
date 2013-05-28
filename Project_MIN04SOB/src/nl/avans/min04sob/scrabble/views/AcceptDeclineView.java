package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.controllers.AcceptDeclineController;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.models.GameModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AcceptDeclineView extends CorePanel{

	private JList<String> wordList;
	private JFrame myFrame;
	private JButton backButton;
	private JButton acceptButton;
	private JButton deniedButton;
	private JLabel requestedWordLabel;
	private JScrollPane scrollPane;
	
	public AcceptDeclineView()
	{
		setLayout(new MigLayout("", "[65px:95.00px:120px][100px:95.00px:100px,grow][65px:67.00px:100px]", "[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));
		
		wordList = new JList<String>();
		wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		backButton = new JButton("Terug");
		add(backButton, "cell 2 0");
		
		requestedWordLabel = new JLabel("voorgestelde woorden");
		add(requestedWordLabel, "cell 0 0 2 1");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 2 3,grow");
		
		scrollPane.setViewportView(wordList);
		
		acceptButton = new JButton("Accepteren");
		add(acceptButton, "cell 0 4,alignx left");
		
		deniedButton = new JButton("Weigeren");
		add(deniedButton, "cell 1 4,alignx right");
		
		myFrame = new JFrame();
		myFrame.setTitle("Voorgestelde woorden");
		myFrame.setAlwaysOnTop(true);
		myFrame.setContentPane(this);
		myFrame.pack();
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setVisible(true);
	}
	
	public void fillJList(String[] list)
	{
		wordList.removeAll();
		wordList.setListData(list);
		
	}
	

	public void addAcceptActionListener(ActionListener listener){
		acceptButton.addActionListener(listener);
	}
	
	public void addBackActionListener(ActionListener listener){
		backButton.addActionListener(listener);
	}
	
	public void addDeniedActionListener(ActionListener listener){
		deniedButton.addActionListener(listener);
	}
	
	public String getSelectedWord(){
		String returnObject = null;
		returnObject = wordList.getSelectedValue().toString();
		
		return returnObject;
	}
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	public void removeVenster(){
		myFrame.dispose();
	}
}
