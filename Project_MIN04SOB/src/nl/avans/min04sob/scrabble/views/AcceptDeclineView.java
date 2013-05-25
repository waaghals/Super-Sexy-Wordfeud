package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JList;

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

	private JList wordList;
	private JFrame myFrame;
	private JButton terugButton;
	private JButton accepteerButton;
	private JButton weigerButton;
	private boolean status;
	private JLabel voorgesteldeWoordenLabel;
	private JScrollPane scrollPane;
	private JList voorgesteldeWoordenList;
	
	public AcceptDeclineView()
	{
		setLayout(new MigLayout("", "[65px:30px:120px][100px:142.00px:100px,grow][65px:67.00px:100px]", "[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));
		
		wordList = new JList();
		
		terugButton = new JButton("Terug");
		add(terugButton, "cell 2 0");
		terugButton.addActionListener(new ActionListener() {
		public void addActionListenerBack(ActionListener listener) {
			terugButton.addActionListener(listener);
		} 
			public void actionPerformed(ActionEvent arg0) {
				myFrame.dispose();
			}
		});
		
		voorgesteldeWoordenLabel = new JLabel("voorgestelde woorden");
		add(voorgesteldeWoordenLabel, "cell 0 0");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 2 3,grow");
		
		voorgesteldeWoordenList = new JList();
		scrollPane.setViewportView(voorgesteldeWoordenList);
		
		accepteerButton = new JButton("Accepteren");
		add(accepteerButton, "cell 0 4,alignx left");
		accepteerButton.addActionListener(new ActionListener() {
		public void addActionListenerAccept(ActionListener listener) {
			accepteerButton.addActionListener(listener);
		} 
			public void actionPerformed(ActionEvent arg0) {
				status = true;
			}
		});
		
		weigerButton = new JButton("Weigeren");
		add(weigerButton, "cell 1 4,alignx right");
		weigerButton.addActionListener(new ActionListener() {
			public void addActionListenerDecline(ActionListener listener) {
				weigerButton.addActionListener(listener);
			} 
				public void actionPerformed(ActionEvent arg0) {
					status = false;
				}
			});
		
	}
	
	public void fillJList(JList list)
	{
		wordList = list;
		list.add(wordList);
	}
	
	public boolean getStatus(){
		return status;
	}
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
