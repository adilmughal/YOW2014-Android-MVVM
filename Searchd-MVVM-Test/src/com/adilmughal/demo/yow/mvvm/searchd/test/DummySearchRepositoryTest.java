package com.adilmughal.demo.yow.mvvm.searchd.test;

import java.util.List;

import org.junit.Test;

import com.adilmughal.demo.yow.mvvm.searchd.repository.DummySearchRepository;

import junit.framework.TestCase;

public class DummySearchRepositoryTest extends TestCase {

	@Test
	public void testGetDataShouldReturnNullWithNullKeywords()
	{
		DummySearchRepository repositoryUnderTest = new DummySearchRepository();
		String keywords = null;
		List<String> expected = repositoryUnderTest.getData(keywords);
		assertNull(expected);
		
	}
}
