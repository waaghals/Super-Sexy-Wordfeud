package nl.avans.min04sob.scrabble.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class CoreController implements PropertyChangeListener, Runnable {

	private ArrayList<CoreView> registeredViews;
	private ArrayList<CoreModel> registeredModels;
	private int updateInterval;

	public CoreController() {
		registeredViews = new ArrayList<CoreView>();
		registeredModels = new ArrayList<CoreModel>();
		updateInterval = 10;
		startTimer();
	}

	private void startTimer() {
		ScheduledExecutorService exec = Executors
				.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(this, 0, updateInterval, TimeUnit.SECONDS);
	}

	//Update all the registered models
	public void run() {
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

	public void setUpdateInterval(int newUpdateInterval) {
		updateInterval = newUpdateInterval;
	}

}