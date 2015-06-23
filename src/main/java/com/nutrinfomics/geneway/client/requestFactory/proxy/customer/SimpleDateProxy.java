package com.nutrinfomics.geneway.client.requestFactory.proxy.customer;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.customer.SimpleDate;

@ProxyFor(value = SimpleDate.class, locator = EntityBaseLocator.class)
public interface SimpleDateProxy extends EntityBaseProxy {

	public int getDay();
	public void setDay(int day);

	public int getMonth();
	public void setMonth(int month);

	public int getYear();
	public void setYear(int year);
}
