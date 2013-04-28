package nl.avans.min04sob.scrabble.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class CoreController implements PropertyChangeListener {

	private ArrayList<CoreView> registeredViews;
	private ArrayList<CoreModel> registeredModels;

	public CoreController() {
		registeredViews = new ArrayList<CoreView>();
		registeredModels = new ArrayList<CoreModel>();
	}

	protected void addModel(CoreModel model) {
		registeredModels.add(model);
		model.addPropertyChangeListener(this);
	}

	protected void removeModel(CoreModel model) {
		registeredModels.remove(model);
		model.removePropertyChangeListener(this);
	}

	protected void addView(CoreView view) {
		registeredViews.add(view);
	}

	protected void removeView(CoreView view) {
		registeredViews.remove(view);
	}

	// Use this to observe property changes from registered models
	// and propagate them on to all the views.

	public void propertyChange(PropertyChangeEvent evt) {
		for (CoreView view : registeredViews) {
			view.modelPropertyChange(evt);
		}
	}

	/**
	 * This is a convenience method that subclasses can call upon to fire
	 * property changes back to the models. This method uses reflection to
	 * inspect each of the model classes to determine whether it is the owner of
	 * the property in question. If it isn't, a NoSuchMethodException is thrown,
	 * which the method ignores.
	 * 
	 * @param propertyName
	 *            = The name of the property.
	 * @param newValue
	 *            = An object that represents the new value of the property.
	 */
	protected void setModelProperty(String propertyName, Object newValue) {

		for (CoreModel model : registeredModels) {
			try {

				Method method = model.getClass().getMethod(
						"set" + propertyName,
						new Class[] { newValue.getClass() }

				);
				method.invoke(model, newValue);

			} catch (Exception ex) {
				// Handle exception.
			}
		}
	}

}