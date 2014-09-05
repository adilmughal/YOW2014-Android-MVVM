package com.adilmughal.demo.yow.searchd.traditional.repository;

import java.util.ArrayList;
import java.util.List;

import com.adilmughal.demo.yow.searchd.traditional.contract.SearchRepository;

public class DummySearchRepository implements SearchRepository {

	@Override
	public List<String> getData(String keywords) {
		
		if (keywords == null)
			return null;
		
		ArrayList<String> data = new ArrayList<String>();
		data.add("iPad 8GB");
		data.add("Nexus 7");
		data.add("Nexus 10");
		data.add("iPad Mini");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return data;
	}
}
