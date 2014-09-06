package com.adilmughal.demo.yow.mvvm.searchd.test;

import org.robobinding.widget.compoundbutton.CheckedChangeEvent;

import junit.framework.TestCase;

import com.adilmughal.demo.yow.mvvm.searchd.viewmodel.SearchViewModel;

public class SearchViewModelTest extends TestCase {
	public void testLocationIsVisibleWhenIncludeLocationIsChecked() {
		
		SearchViewModel viewModel = new SearchViewModel();
		assertFalse(viewModel.isLocationIncluded());
		CheckedChangeEvent event = new CheckedChangeEvent(null, true);
		viewModel.onLocationIncluded(event);
		
		assertTrue(viewModel.isLocationIncluded());
	}
	
	public void testProgressIsDisplayedOnSubmitWhenValidated() {
		
		SearchViewModel viewModel = new SearchViewModel();
		assertFalse(viewModel.isSearchInProgress());
		
		viewModel.setKeywords("tablet");
		viewModel.onSubmit(null);
		
		assertTrue(viewModel.isSearchInProgress());
	}
}
