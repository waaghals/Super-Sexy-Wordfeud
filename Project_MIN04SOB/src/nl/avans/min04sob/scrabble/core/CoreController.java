package nl.avans.min04sob.scrabble.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class CoreController implements PropertyChangeListener {

	private ArrayList<CoreView> registeredViews;
	private ArrayList<CoreModel> registeredModels;
	private int updateInterval;

	public CoreController() {
		registeredViews = new ArrayList<CoreView>();
		registeredModels = new ArrayList<CoreModel>();
		
		startTimer();
	}

	private void startTimer() {
		ScheduledExecutorService exec = Executors
				.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				updateModels();
			}

		}, 0, updateInterval, TimeUnit.SECONDS);
	}
	
	private void updateModels(){
		for (CoreModel model : registeredModels) {
			model.update();
		}
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
	 *            =update The name of the property.
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
	
	public void setUpdateInterval(int newUpdateInterval){
		updateInterval = newUpdateInterval;
	}

}