package com.adilmughal.demo.yow.mvvm.searchd.contract;

import java.util.List;

public interface SearchRepository {

	List<String> getData(String keywords);

}