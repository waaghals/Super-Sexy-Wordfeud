package nl.avans.min04sob.scrabble;

import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

import nl.avans.min04sob.scrabble.core.CorePanel;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

public class BoardPanel extends CorePanel {
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the panel.
	 */
	public BoardPanel() {
		setLayout(new MigLayout("", "[25px][25px][25px][25px][25px][25px][25px][25px][25px][25px][25px][][][][][25px]", "[25px][25px][25px][25px][25px][25px][25px][25px][25px][25px][25px][][][][25px]"));
		
		table_1 = new JTable();
		add(table_1, "cell 0 0 10 10,grow");
		
		textField = new JTextField();
		add(textField, "cell 0 10,growx");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		add(textField_1, "cell 1 10,growx");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		add(textField_2, "cell 2 10,growx");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		add(textField_3, "cell 3 10,growx");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		add(textField_4, "cell 4 10,growx");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		add(textField_5, "cell 5 10,growx");
		
		JButton btnPlay = new JButton("Play");
		add(btnPlay, "cell 10 10 3 1");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		add(textField_6, "cell 6 11,growx");

	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
