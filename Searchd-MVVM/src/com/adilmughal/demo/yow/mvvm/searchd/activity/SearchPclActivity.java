package com.adilmughal.demo.yow.mvvm.searchd.activity;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.robobinding.widget.compoundbutton.CheckedChangeEvent;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adilmughal.demo.yow.mvvm.searchd.R;
import com.adilmughal.demo.yow.mvvm.searchd.viewmodel.SearchPclViewModel;

public class SearchPclActivity extends Activity {
	private EditText locationEditText;
	private TextView validationMessageTextView;
	private ListView searchListView;
	private ProgressBar progressBar;

	private SearchPclViewModel viewModel;
	private EditText keywordsEditText;
	private Button submitButton;
	private CheckBox includeLocationCheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewModel = new SearchPclViewModel();
		setContentView(R.layout.activity_search_pcl);
		this.initializeViewFields();
		this.setupViewListeners();
		this.setupViewModelListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	private void initializeViewFields() {
		this.keywordsEditText = (EditText) this.findViewById(R.id.activity_search_keywords_edittext);
		this.submitButton = (Button) this.findViewById(R.id.activity_search_submit_button);
		this.includeLocationCheckBox  = (CheckBox) this.findViewById(R.id.activity_search_location_include_checkbox);
		this.locationEditText = (EditText) this.findViewById(R.id.activity_search_location_edittext);
		this.validationMessageTextView = (TextView) this.findViewById(R.id.activity_search_validation_message_view);
		this.progressBar = (ProgressBar) this.findViewById(R.id.activity_search_progress);
		this.searchListView = (ListView) this.findViewById(R.id.activity_search_result_list_view);
	}

	private void setupViewModelListeners() {
		viewModel.addPropertyChangeListener(SearchPclViewModel.PROPERTY_LOCATION_INCLUDED, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg) {
				locationEditText.setVisibility(viewModel.isLocationIncluded() ? View.VISIBLE : View.GONE);

			}
		});

		viewModel.addPropertyChangeListener(SearchPclViewModel.PROPERTY_IN_VALID, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg) {
				validationMessageTextView.setVisibility(viewModel.isInValid() ? View.VISIBLE : View.GONE);
			}
		});
		
		viewModel.addPropertyChangeListener(SearchPclViewModel.PROPERTY_VALIDATION_MESSAGE, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg) {
				validationMessageTextView.setText(viewModel.getValidationMessage());
			}
		});

		viewModel.addPropertyChangeListener(SearchPclViewModel.PROPERTY_SEARCH_IN_PROGRESS, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg) {
				progressBar.setVisibility(viewModel.isSearchInProgress() ? View.VISIBLE : View.GONE);
			}
		});

		viewModel.addPropertyChangeListener(SearchPclViewModel.PROPERTY_RESULT_LIST, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg) {
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchPclActivity.this,
						android.R.layout.simple_list_item_1, viewModel.getResultList());
				searchListView.setAdapter(adapter);
			}
		});

	}
	
	private void setupViewListeners() {
		
		this.keywordsEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				viewModel.setKeywords(keywordsEditText.getText().toString());
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
	this.submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				viewModel.onSubmit(null);
			}	
		});
		
		this.includeLocationCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				viewModel.onLocationIncluded(new CheckedChangeEvent(buttonView, isChecked));
			}
		});
		
	}

}
