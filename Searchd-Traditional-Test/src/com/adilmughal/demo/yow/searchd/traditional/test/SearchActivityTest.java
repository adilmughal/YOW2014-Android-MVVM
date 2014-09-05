package com.adilmughal.demo.yow.searchd.traditional.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

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

	private void startActivityForTest() {
		Intent intent = new Intent(getInstrumentation().getTargetContext(), SearchActivity.class);
		startActivity(intent, null, null);
		searchActivity = getActivity();
	}
}
