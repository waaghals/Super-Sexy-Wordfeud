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
	private GameModel gameModel = new GameModel();
	private JFrame myFrame;
	//private AcceptDeclineController adController;
	
	public AcceptDeclineView()
	{
		setLayout(new MigLayout("", "[65px:30px:120px][100px:142.00px:100px,grow][65px:67.00px:100px]", "[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));
		
		JButton terugButton = new JButton("Terug");
		terugButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel lblVoorgesteldeWoorden = new JLabel("voorgestelde woorden");
		add(lblVoorgesteldeWoorden, "cell 0 0");
		add(terugButton, "cell 2 0");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 2 3,grow");
		
		JList voorgesteldeWoordenList = new JList();
		scrollPane.setViewportView(voorgesteldeWoordenList);
		
		JButton accepteerButton = new JButton("Accepteren");
		add(accepteerButton, "cell 0 4,alignx left");
		
		JButton weigerButton = new JButton("Weigeren");
		add(weigerButton, "cell 1 4,alignx right");
		wordList = new JList(gameModel.getRequestedWords());
		
	}
	
	public void fillJList(JList list)
	{
		wordList = list;
	}
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
