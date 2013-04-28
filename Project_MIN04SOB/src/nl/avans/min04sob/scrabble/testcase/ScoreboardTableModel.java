package nl.avans.min04sob.scrabble.testcase;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import nl.avans.min04sob.scrabble.testcase.ScoreboardModel.Boardline;

public class ScoreboardTableModel extends AbstractTableModel {

    private List<Boardline> boardlines;

    public ScoreboardTableModel(List<Boardline> boardlines) {
        this.boardlines = boardlines;
    }

    public int getColumnCount() {
    	return 2;
    }

    public int getRowCount() {
        return boardlines.size();
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0: return "Name";
        case 1: return "Score";
        }
		return null;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	Boardline boardline = boardlines.get(rowIndex);

        switch (columnIndex) {
        case 0: return boardline.getName();
        case 1: return boardline.getScore();
        }
		return boardline;
    }

}
