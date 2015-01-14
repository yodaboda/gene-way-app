package com.nutrinfomics.geneway.client.requestFactory.proxy.plan;

import java.util.Date;
import java.util.List;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.plan.SnackTimes;

@ProxyFor(value = SnackTimes.class, locator = EntityBaseLocator.class)
public interface SnackTimesProxy extends EntityBaseProxy {

	public double getTimeBetweenSnacks();

	public void setTimeBetweenSnacks(double timeBetweenSnacks);

	public List<Date> getSnackTimes();

	public void setSnackTimes(List<Date> snackTimes);

}
