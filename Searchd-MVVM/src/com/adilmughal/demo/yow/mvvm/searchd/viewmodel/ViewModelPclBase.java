package com.adilmughal.demo.yow.mvvm.searchd.viewmodel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import android.os.Handler;
import android.os.Looper;

/**
 * View Model Base to support Java Beans Property Change Listeners
 * 
 * @author adil
 * 
 */
public class ViewModelPclBase {

	private PropertyChangeSupport propertyChangeSupport;
	
	public ViewModelPclBase() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(propertyName,
				listener);
	}

	protected void notifyPropertyChanged(final String propertyName) {
		notifyPropertyChanged(propertyName, null, null);
	}

	protected void notifyPropertyChanged(final String propertyName,
			final Object previousValue, final Object newValue) {
		if (Looper.myLooper() == Looper.getMainLooper()) {
			propertyChangeSupport.firePropertyChange(propertyName,
					previousValue, newValue);
		} else {
			// Dispatch/post to UI thread
			new Handler(Looper.getMainLooper()).post(new Runnable() {
				@Override
				public void run() {
					propertyChangeSupport.firePropertyChange(propertyName,
							previousValue, newValue);
				}
			});
		}
	}
}
