package com.nutrinfomics.geneway.client.requestFactory.proxy.plan;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.plan.SnackHistory;
import com.nutrinfomics.geneway.shared.SnackStatus;

@ProxyFor(value = SnackHistory.class, locator = EntityBaseLocator.class)
public interface SnackHistoryProxy extends EntityBaseProxy{

	public void setEatenSnack(SnackProxy snack);

	public SnackProxy getEatenSnack();

	public void setPlannedSnack(SnackProxy snack);
	
	public SnackProxy getPlannedSnack();
	
	public CustomerProxy getCustomer();
	
	public void setCustomer(CustomerProxy customer);
	
	public String getDayString() ;

	public void setDayString(String dayString);

	public Date getTimestamp();

	public void setTimestamp(Date timestamp);
	
	public int getTimeZoneDiff();

	public void setTimeZoneDiff(int timeZoneDiff);

	public SnackStatus getStatus();

	public void setStatus(SnackStatus status);
}