package nl.avans.min04sob.scrabble.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class CoreController implements PropertyChangeListener, Runnable {

	private ArrayList<CoreView> registeredViews;
	private ArrayList<CoreModel> registeredModels;
	private final static int INTERVAL = 1;//Second

	public CoreController() {
		registeredViews = new ArrayList<CoreView>();
		registeredModels = new ArrayList<CoreModel>();
		startTimer();
	}

	abstract public void addListeners(); //Add the listeners for the buttons etc..

	public void addModel(CoreModel model) {
		if(model == null){
			return;
		}
		registeredModels.add(model);
		model.addPropertyChangeListener(this);
	}

	public void addView(CoreView view) {
		if(view == null){
			return;
		}
		registeredViews.add(view);
	}

	//Cleanlyness methods
	abstract public void initialize(); 	//Init all variables

	// Use this to observe property changes from registered models
	// and propagate them on to all the views.
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		for (CoreView view : registeredViews) {
			//System.out.println("Update from " + evt.getSource() + " to " + view.getClass().getName() + " via " + this.toString() + ". Message: " + evt.getPropertyName());
			view.modelPropertyChange(evt);
		}
	}

	public void removeModel(CoreModel model) {
		if(model == null){
			return;
		}
		registeredModels.remove(model);
		model.removePropertyChangeListener(this);
	}

	public void removeView(CoreView view) {
		if(view == null){
			return;
		}
		registeredViews.remove(view);
	}
	
	//Update all the registered models
	@Override
	public void run() {
		for (CoreModel model : registeredModels) {
			model.update();
		}
	}
	
	private void startTimer() {
		ScheduledExecutorService exec = Executors
				.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(this, 0, INTERVAL, TimeUnit.SECONDS);
	}
	@Override
	public String toString(){
		return this.getClass().getName();
	}

}