package com.adilmughal.demo.yow.searchd.traditional.activity;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
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

import com.adilmughal.demo.yow.searchd.traditional.R;
import com.adilmughal.demo.yow.searchd.traditional.contract.SearchRepository;
import com.adilmughal.demo.yow.searchd.traditional.repository.DummySearchRepository;

public class SearchActivity extends Activity {

	private EditText keywordsEditText;
	private Button submitButton;
	private CheckBox includeLocationCheckBox;
	private EditText locationEditText;
	private TextView validationMessageTextView;
	private ListView searchListView;

	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		this.initializeViewFields();
		this.setupListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	private void initializeViewFields() {
		this.keywordsEditText = (EditText) this.findViewById(R.id.activity_search_keywords_edittext);
		this.submitButton = (Button) this.findViewById(R.id.activity_search_submit_button);
		this.includeLocationCheckBox = (CheckBox) this.findViewById(R.id.activity_search_location_include_checkbox);
		this.locationEditText = (EditText) this.findViewById(R.id.activity_search_location_edittext);
		this.validationMessageTextView = (TextView) this.findViewById(R.id.activity_search_validation_message_view);
		this.progressBar = (ProgressBar) this.findViewById(R.id.activity_search_progress);
		this.searchListView = (ListView) this.findViewById(R.id.activity_search_result_list_view);
	}

	private void setupListeners() {
		this.submitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (validateSearchParams()) {
					new PerformSearchAsyncTask().execute(keywordsEditText.getText().toString());
				}
			}
		});

		this.includeLocationCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
					locationEditText.setVisibility(View.VISIBLE);
				else
					locationEditText.setVisibility(View.INVISIBLE);

			}
		});

	}

	private void showValidationErrorMessage(String errorMessage) {
		this.validationMessageTextView.setText(errorMessage);
		this.validationMessageTextView.setVisibility(View.VISIBLE);
		this.validationMessageTextView.bringToFront();
		this.validationMessageTextView.postDelayed(new Runnable() {

			@Override
			public void run() {
				validationMessageTextView.setVisibility(View.GONE);

			}
		}, 3000);

	}

	private boolean validateSearchParams() {
		boolean isValid = true;
		StringBuilder errorMessageBuilder = new StringBuilder();

		if (keywordsEditText.getText().length() <= 2) {
			isValid = false;
			errorMessageBuilder.append("- Try entering more characters as keywords!");
		}

		if (this.includeLocationCheckBox.isChecked()) {
			if (this.locationEditText.getText().length() != 4) {
				isValid = false;

				if (errorMessageBuilder.length() > 0)
					errorMessageBuilder.append("\n");

				errorMessageBuilder.append("- Invalid location, enter 4 digit postcode!");
			}
		}

		if (!isValid)
			this.showValidationErrorMessage(errorMessageBuilder.toString());

		return isValid;
	}

	private void bindDataWithResult(List<String> result) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);
		this.searchListView.setAdapter(adapter);
	}

	private class PerformSearchAsyncTask extends AsyncTask<String, Void, List<String>> {

		@Override
		protected List<String> doInBackground(String... keywords) {
			SearchRepository repo = new DummySearchRepository();
			return repo.getData(keywords[0]);
		}

		@Override
		protected void onPostExecute(List<String> result) {
			if (result != null && result.size() > 0) {
				progressBar.setVisibility(View.INVISIBLE);
				bindDataWithResult(result);
				searchListView.setVisibility(View.VISIBLE);
			}

		}

		@Override
		protected void onPreExecute() {
			progressBar.setVisibility(View.VISIBLE);
			searchListView.setVisibility(View.GONE);
		}

	}
}
