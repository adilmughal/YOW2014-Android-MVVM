package com.adilmughal.demo.yow.searchd.traditional.contract;

import java.util.List;

public interface SearchRepository {

	List<String> getData(String keywords);

}