package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class CreateCompetitionView extends CorePanel {
	
	private JFrame myFrame;
	private JButton backButton;
	private JTextField textField;
	private JButton createButton;
	
	public CreateCompetitionView(){
		setLayout(new MigLayout("", "[131.00][137.00,grow]", "[][237.00][30.00]"));
		
		JLabel descriptionLabel = new JLabel("Geef de imschrijving van de competition");
		add(descriptionLabel, "cell 0 0 2 1");
		
		textField = new JTextField();
		add(textField, "cell 0 1 2 1,growx");
		textField.setColumns(10);
		
		backButton = new JButton("Terug");
		add(backButton, "cell 0 2,alignx left");
		
		createButton = new JButton("Aanmaken");
		add(createButton, "cell 1 2,alignx right");
		
		myFrame = new JFrame();
		myFrame.setTitle("Competition aanmaken");
		myFrame.setContentPane(this);
		myFrame.pack();
		myFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		myFrame.setVisible(true);
	}
	
	public String getDiscription(){
		String text = textField.getText();
		return text;
	}
	
	public void addBackButtonListener(ActionListener listener){
		backButton.addActionListener(listener);
	}
	
	public void addCreateButtonListener(ActionListener listener){
		createButton.addActionListener(listener);
	}
	
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

}
