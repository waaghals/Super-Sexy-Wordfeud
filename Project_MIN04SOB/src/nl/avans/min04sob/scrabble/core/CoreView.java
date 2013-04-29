package nl.avans.min04sob.scrabble.core;

import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public interface CoreView {
	public abstract void modelPropertyChange(PropertyChangeEvent evt);
}
