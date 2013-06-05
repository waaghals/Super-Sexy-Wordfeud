package nl.avans.min04sob.scrabble.core.mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class CoreModel {

	protected PropertyChangeSupport propertyChangeSupport;

	public CoreModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,
				newValue);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	abstract public void update();

}
