package com.adilmughal.demo.yow.mvvm.searchd.viewmodel;

import java.util.List;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.widget.compoundbutton.CheckedChangeEvent;
import org.robobinding.widget.view.ClickEvent;

import com.adilmughal.demo.yow.mvvm.searchd.contract.SearchRepository;
import com.adilmughal.demo.yow.mvvm.searchd.repository.DummySearchRepository;

public class SearchPclViewModel extends ViewModelPclBase {

	public static final String PROPERTY_IN_VALID = "inValid";
	public static final String PROPERTY_KEYWORDS = "keywords";
	public static final String PROPERTY_LOCATION = "location";
	public static final String PROPERTY_LOCATION_INCLUDED = "locationIncluded";
	public static final String PROPERTY_RESULT_LIST = "resultList";
	public static final String PROPERTY_SEARCH_IN_PROGRESS = "searchInProgress";
	public static final String PROPERTY_VALIDATION_MESSAGE = "validationMessage";

	// Validation
	private boolean invalid;
	private String validationMessage;

	// Data
	private String keywords;
	private String location;
	private boolean locationIncluded;

	private List<String> resultList;

	// State
	private boolean searchInProgress;

	public SearchPclViewModel() {
		this.keywords = "";
		this.location = "";
		this.locationIncluded = false;
	}

	// Getters and Setters
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
		return invalid;
	}

	public boolean isLocationIncluded() {
		return locationIncluded;
	}

	public boolean isSearchInProgress() {
		return searchInProgress;
	}

	public void setInValid(boolean inValid) {
		this.invalid = inValid;
		super.notifyPropertyChanged(PROPERTY_IN_VALID);
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
		super.notifyPropertyChanged(PROPERTY_KEYWORDS);
	}

	public void setLocation(String location) {
		this.location = location;
		super.notifyPropertyChanged(PROPERTY_LOCATION);
	}

	public void setLocationIncluded(boolean locationIncluded) {
		this.locationIncluded = locationIncluded;
		super.notifyPropertyChanged(PROPERTY_LOCATION_INCLUDED);
	}

	public void setResultList(List<String> resultList) {
		this.resultList = resultList;
		super.notifyPropertyChanged(PROPERTY_RESULT_LIST);
	}

	public void setSearchInProgress(boolean searchInProgress) {
		this.searchInProgress = searchInProgress;
		super.notifyPropertyChanged(PROPERTY_SEARCH_IN_PROGRESS);
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
		super.notifyPropertyChanged(PROPERTY_VALIDATION_MESSAGE);
	}

	// Commands/Methods

	public void onLocationIncluded(CheckedChangeEvent event) {
		this.setLocationIncluded(event.isChecked());
	}

	public void onSubmit(ClickEvent event) {
		if (this.validateSearchParams()) {
			retrieveData();
		}
	}

	private void retrieveData() {
		this.setSearchInProgress(true);
		new Thread(new Runnable() {

			@Override
			public void run() {
				SearchRepository repository = new DummySearchRepository();
				List<String> data = repository.getData(keywords);
				setResultList(data);
				setSearchInProgress(false);
			}
		}).start();

	}

	private boolean validateSearchParams() {
		boolean isValid = true;
		StringBuilder errorMessageBuilder = new StringBuilder();

		if (this.getKeywords().length() <= 2) {
			isValid = false;
			errorMessageBuilder.append("- Try entering more characters as keywords!");
		}

		if (this.isLocationIncluded()) {
			if (this.getLocation().length() != 4) {
				isValid = false;

				if (errorMessageBuilder.length() > 0)
					errorMessageBuilder.append("\n");

				errorMessageBuilder.append("- Invalid location, enter 4 digit postcode!");
			}
		}

		this.setInValid(!isValid);
		this.setValidationMessage(errorMessageBuilder.toString());

		return isValid;
	}

}
