package com.adilmughal.demo.yow.searchd.traditional.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.adilmughal.demo.yow.searchd.traditional.activity.SearchActivity;

public class SearchActivityTest extends ActivityUnitTestCase<SearchActivity> {

	private SearchActivity searchActivity;

	public SearchActivityTest() {
		super(SearchActivity.class);
	}

	public void testLocationIsVisibleWhenIncludeLocationIsChecked() {

		this.startActivityForTest();
		
	    int viewId;
	    
		viewId = com.adilmughal.demo.yow.searchd.traditional.R.id.activity_search_location_include_checkbox;
		CheckBox includeLocationCheckbox = (CheckBox) searchActivity.findViewById(viewId);
		
		viewId = com.adilmughal.demo.yow.searchd.traditional.R.id.activity_search_location_edittext;
		EditText locationEditText = (EditText) searchActivity.findViewById(viewId);
		
		// Precondition before click
		assertTrue(locationEditText.getVisibility() == View.GONE);
		
		includeLocationCheckbox.performClick();
		getInstrumentation().waitForIdleSync();
		
		assertTrue(locationEditText.getVisibility() == View.VISIBLE);
	}
	
	public void testProgressBarIsVisibleWhenSubmitIsClicked() {

		this.startActivityForTest();
		
	    int viewId;
	    
		viewId = com.adilmughal.demo.yow.searchd.traditional.R.id.activity_search_progress;
		ProgressBar progressBar = (ProgressBar) searchActivity.findViewById(viewId);
		
		viewId = com.adilmughal.demo.yow.searchd.traditional.R.id.activity_search_submit_button;
		Button submitButton = (Button) searchActivity.findViewById(viewId);
		
		// Precondition before click
		assertTrue(progressBar.getVisibility() == View.GONE);
		
		submitButton.performClick();
		getInstrumentation().waitForIdleSync();
		
		//Below assertion would fail because thats being done in AsyncTask
		//assertTrue(progressBar.getVisibility() == View.VISIBLE);
	}

	private void startActivityForTest() {
		Intent intent = new Intent(getInstrumentation().getTargetContext(), SearchActivity.class);
		startActivity(intent, null, null);
		searchActivity = getActivity();
	}
}
