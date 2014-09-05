package com.adilmughal.demo.yow.mvvm.searchd.viewmodel;

import org.robobinding.itempresentationmodel.ItemPresentationModel;
import org.robobinding.presentationmodel.AbstractPresentationModel;

public class StringItemPresentationModel extends AbstractPresentationModel implements ItemPresentationModel<String>
{
	private String value;

	@Override
	public void updateData(int index, String bean)
	{
		value = bean;
	}

	public String getValue()
	{
		return value;
	}
}