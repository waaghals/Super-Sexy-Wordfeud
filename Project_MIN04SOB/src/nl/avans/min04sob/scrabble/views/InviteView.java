package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InviteView extends CorePanel {

	private JButton actieButton;
	private JButton annuleerButton;
	private JLabel competitionLabel;
	private JLabel spelersLabel;
	private String competitionLabelText;
	private String spelersLabelText;
	private String actieButtonText;

	public InviteView() {
		setLayout(new MigLayout(
				"",
				"[100px:120px:120px,grow][100px:142.00px:100px,grow][100px:100px:100px]",
				"[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));

		competitionLabel = new JLabel(competitionLabelText); // aanpassen naar
																// variabelle
		add(competitionLabel, "cell 0 0");

		spelersLabel = new JLabel(spelersLabelText); // aanpassen naar
														// variabelle
		add(spelersLabel, "cell 1 0");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 1 3,grow");

		JList competitionList = new JList();
		scrollPane.setViewportView(competitionList);

		JScrollPane scrollPane_2 = new JScrollPane();
		add(scrollPane_2, "cell 1 1 2 3,grow");

		JList playerList = new JList();
		scrollPane_2.setViewportView(playerList);

		JButton annuleerButton = new JButton("Annuleer");
		add(annuleerButton, "cell 0 4,alignx left,aligny top");

		JButton actieButton = new JButton(actieButtonText); // aanpassen naar
															// variabelle
		add(actieButton, "cell 2 4,growx,aligny top");
	}

	public void addActionListenerActieButton(ActionListener listener) {
		actieButton.addActionListener(listener);
	}

	public void addActionListenerAnnuleerButton(ActionListener listener) {
		annuleerButton.addActionListener(listener);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	public void setButtons(String competitionLabelText,
			String spelersLabelText, String actieButtonText) {
		this.competitionLabelText = competitionLabelText;
		this.spelersLabelText = spelersLabelText;
		this.actieButtonText = actieButtonText;
	}

}
