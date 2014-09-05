package com.adilmughal.demo.yow.mvvm.searchd.viewmodel;

import org.robobinding.presentationmodel.AbstractPresentationModel;

import android.os.Handler;
import android.os.Looper;

public class ViewModelBase extends AbstractPresentationModel {

	protected void notifyPropertyChanged(final String propertyName) {
		if (Looper.myLooper() == Looper.getMainLooper()) {
			super.firePropertyChange(propertyName);
		} else {
			// Dispatch/post to UI thread
			new Handler(Looper.getMainLooper()).post(new Runnable() {
				@Override
				public void run() {
					firePropertyChange(propertyName);
				}
			});
		}
	}
}
