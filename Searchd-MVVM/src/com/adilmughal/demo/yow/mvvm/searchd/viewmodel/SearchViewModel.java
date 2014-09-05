package com.adilmughal.demo.yow.mvvm.searchd.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.widget.compoundbutton.CheckedChangeEvent;
import org.robobinding.widget.view.ClickEvent;

public class SearchViewModel extends ViewModelBase {

	// Data
	private String keywords;
	private String location;
	private boolean locationIncluded;
	private List<String> resultList;

	// State
	private boolean searchInProgress;

	// Validation
	private boolean inValid;
	private String validationMessage;

	public String getKeywords() {
		return keywords;
	}

	public String getLocation() {
		return location;
	}

	@ItemPresentationModel(value = StringItemPresentationModel.class)
	public List<String> getResultList() {
		return resultList;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public boolean isInValid() {
		return inValid;
	}

	public boolean isLocationIncluded() {
		return locationIncluded;
	}

	public boolean isSearchInProgress() {
		return searchInProgress;
	}

	public void onLocationIncluded(CheckedChangeEvent event) {
		this.setLocationIncluded(event.isChecked());
	}

	public void onSubmit(ClickEvent event) {
		if (this.validateSearchParams()) {
			retrieveData();
		}
	}

	public void setInValid(boolean inValid) {
		this.inValid = inValid;
		super.notifyPropertyChanged("inValid");
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
		super.firePropertyChange("keywords");
	}

	public void setLocation(String location) {
		this.location = location;
		super.notifyPropertyChanged("location");
	}

	public void setLocationIncluded(boolean locationIncluded) {
		this.locationIncluded = locationIncluded;
		super.firePropertyChange("locationIncluded");
	}

	public void setResultList(List<String> resultList) {
		this.resultList = resultList;
		super.notifyPropertyChanged("resultList");
	}

	public void setSearchInProgress(boolean searchInProgress) {
		this.searchInProgress = searchInProgress;
		super.notifyPropertyChanged("searchInProgress");
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
		super.notifyPropertyChanged("validationMessage");
	}
	
	private void retrieveData() {
		this.setSearchInProgress(true);
		final ArrayList<String> data = new ArrayList<String>();
		data.add("iPad 8GB");
		data.add("Nexus 7");
		data.add("Nexus 10");
		data.add("iPad Mini");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				setSearchInProgress(false);
				setResultList(data);
			}
		}).start();

	}

	private boolean validateSearchParams() {
		boolean isValid = true;
		StringBuilder errorMessageBuilder = new StringBuilder();

		if (this.getKeywords().length() <= 2) {
			isValid = false;
			errorMessageBuilder
					.append("- Try entering more characters as keywords!");
		}

		if (this.isLocationIncluded()) {
			if (this.getLocation().length() != 4) {
				isValid = false;

				if (errorMessageBuilder.length() > 0)
					errorMessageBuilder.append("\n");

				errorMessageBuilder
						.append("- Invalid location, enter 4 digit postcode!");
			}
		}

		this.setInValid(!isValid);
		this.setValidationMessage(errorMessageBuilder.toString());

		return isValid;
	}

}
