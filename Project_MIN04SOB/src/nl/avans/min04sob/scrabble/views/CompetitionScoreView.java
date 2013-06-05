package nl.avans.min04sob.scrabble.views;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.mvc.CorePanel;
import nl.avans.min04sob.scrabble.models.CompetitionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CompetitionScoreView extends CorePanel {

	private JScrollPane scrollPane;
	private JScrollPane scrollPane1;
	private JList<CompetitionModel> competitionList;
	private JButton backButton;
	private JLabel competitionLabel;
	private JLabel playersLabel;
	private JTable table;
	private DefaultTableModel tableModel;

	public CompetitionScoreView() {
		setLayout(new MigLayout("", "[250px:n:350px,grow][700px:n]", "[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));

		competitionLabel = new JLabel("Competities");
		add(competitionLabel, "cell 0 0,alignx left");

		playersLabel = new JLabel("Scores van spelers in de competitie");
		add(playersLabel, "cell 1 0,alignx left");

		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 1 3,grow");

		scrollPane1 = new JScrollPane();
		add(scrollPane1, "cell 1 1 1 3,grow");

		competitionList = new JList<CompetitionModel>();
		scrollPane.setViewportView(competitionList);

		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		scrollPane1.setViewportView(table);
		setColumns();

		backButton = new JButton("Terug");
		add(backButton, "cell 0 4,alignx left,aligny top");
	}

	public void setColumns() {
		tableModel.addColumn("Naam");
		tableModel.addColumn("Aantal wedstrijden");
		tableModel.addColumn("Totaal punten");
		tableModel.addColumn("gemiddelde punten");
		tableModel.addColumn("gewonnen/ verloren");
		tableModel.addColumn("Bayesian_gemiddelde");
	}

	public void addRow(Object[] dataRow) {
		tableModel.addRow(dataRow);
	}

	public void addActionListenerAnnuleerButton(ActionListener listener) {
		backButton.addActionListener(listener);
	}

	public void fillAvailableCompetitions(
			CompetitionModel[] availableCompetitions) {
		competitionList.setListData(availableCompetitions);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	public CompetitionModel selectedCompetition() {
		return competitionList.getSelectedValue();
	}

	public void addBackListener(ActionListener listener) {
		backButton.addActionListener(listener);
	}

	public void addCompetitionListListener(MouseAdapter listener) {
		competitionList.addMouseListener(listener);
	}

	public void fillCompetitions(CompetitionModel[] comp) {
		competitionList.setListData(comp);
	}

	public CompetitionModel getSelectedCompetition() {
		return competitionList.getSelectedValue();
	}

	public void emptyTable() {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			tableModel.removeRow(i);
		}
	}
}
