package com.adilmughal.demo.yow.mvvm.searchd.activity;

import org.robobinding.binder.Binders;

import com.adilmughal.demo.yow.mvvm.searchd.R;
import com.adilmughal.demo.yow.mvvm.searchd.viewmodel.SearchViewModel;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SearchViewModel viewModel = new SearchViewModel();
		View view = Binders.inflateAndBind(this, R.layout.activity_search, viewModel);
		setContentView(view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
