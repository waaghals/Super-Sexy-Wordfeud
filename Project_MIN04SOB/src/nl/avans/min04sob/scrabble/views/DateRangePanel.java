package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import nl.avans.min04sob.scrabble.core.CorePanel;

@SuppressWarnings("serial")
public class DateRangePanel extends CorePanel {

	private JComboBox<Integer> startYear, startMonth, startDay;
	private JComboBox<Integer> endYear, endMonth, endDay;
	private Calendar startDate = Calendar.getInstance();
	private Calendar endDate = Calendar.getInstance();

	public DateRangePanel() {

		JPanel datesPanel = new JPanel();
		datesPanel.setBorder(BorderFactory
				.createTitledBorder("Begin en einddatum"));

		JLabel startDateLabel = new JLabel("Begin datum:  ",
				SwingConstants.RIGHT);
		startYear = new JComboBox<Integer>();
		buildYearsList(startYear);
		startYear.setSelectedIndex(5);
		startMonth = new JComboBox<Integer>();
		buildMonthsList(startMonth);
		startMonth.setSelectedIndex(startDate.get(Calendar.MONTH));
		startDay = new JComboBox<Integer>();
		buildDaysList(startDate, startDay, startMonth);
		startDay.setSelectedItem(Integer.toString(startDate.get(Calendar.DATE)));
		add(startDateLabel);
		add(startMonth);
		add(startDay);
		add(startYear);
		JLabel endDateLabel = new JLabel("End Date:  ", SwingConstants.RIGHT);
		endYear = new JComboBox<Integer>();
		buildYearsList(endYear);
		endYear.setSelectedIndex(5);
		endMonth = new JComboBox<Integer>();
		buildMonthsList(endMonth);
		endMonth.setSelectedIndex(endDate.get(Calendar.MONTH));
		endDay = new JComboBox<Integer>();
		buildDaysList(endDate, endDay, endMonth);
		endDay.setSelectedItem(Integer.toString(endDate.get(Calendar.DATE)));

		add(endDateLabel);
		add(endMonth);
		add(endDay);
		add(endYear);
	}

	public void addEndDayListener(ActionListener listener) {
		endDay.addActionListener(listener);
	}

	public void addEndMonthListener(ActionListener listener) {
		endMonth.addActionListener(listener);
	}

	public void addEndYearListener(ActionListener listener) {
		endYear.addActionListener(listener);
	}

	public void addStartDayListener(ActionListener listener) {
		startDay.addActionListener(listener);
	}

	public void addStartMonthListener(ActionListener listener) {
		startMonth.addActionListener(listener);
	}

	public void addStartYearListener(ActionListener listener) {
		startYear.addActionListener(listener);
	}

	/**
	 * This method builds the list of years for the start date and end date of
	 * the semester
	 * 
	 * @param dateIn
	 *            The current date, which will be used for the initial date of
	 *            the lists
	 * @param daysList
	 *            The combo box that will contain the days
	 * @param monthsList
	 *            The combo box that will contain the months
	 */
	private void buildDaysList(Calendar dateIn, JComboBox<Integer> daysList,
			JComboBox<Integer> monthsList) {

		daysList.removeAllItems();
		dateIn.set(Calendar.MONTH, monthsList.getSelectedIndex());
		int lastDay = startDate.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int dayCount = 1; dayCount <= lastDay; dayCount++)
			daysList.addItem(dayCount);
	}

	/**
	 * This method builds the list of months for the start date and end date of
	 * the semester
	 * 
	 * @param monthsList
	 *            The combo box containing the months
	 */
	private void buildMonthsList(JComboBox<Integer> monthsList) {

		monthsList.removeAllItems();
		for (int monthCount = 0; monthCount < 12; monthCount++)
			monthsList.addItem(monthCount);
	}

	/**
	 * This method builds the list of years for the start date and end date of
	 * the semester
	 * 
	 * @param yearsList
	 *            The combo box containing the years
	 */
	private void buildYearsList(JComboBox<Integer> yearsList) {

		int currentYear = startDate.get(Calendar.YEAR);

		for (int yearCount = currentYear; yearCount <= currentYear + 5; yearCount++)
			yearsList.addItem(yearCount);
	}

	public Calendar getEndDate() {
		int year = (int) endYear.getSelectedItem();
		int month = (int) endMonth.getSelectedItem();
		int day = (int) endDay.getSelectedItem();
		endDate.set(year, month, day);

		return endDate;
	}

	public Calendar getStartDate() {
		int year = (int) startYear.getSelectedItem();
		int month = (int) startMonth.getSelectedItem();
		int day = (int) startDay.getSelectedItem();
		startDate.set(year, month, day);

		return startDate;
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Automatisch gegenereerde methodestub

	}

}
