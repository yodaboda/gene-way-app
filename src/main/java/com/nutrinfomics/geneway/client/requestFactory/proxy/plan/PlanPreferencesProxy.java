package com.nutrinfomics.geneway.client.requestFactory.proxy.plan;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.plan.PlanPreferences;

@ProxyFor(value = PlanPreferences.class, locator = EntityBaseLocator.class)
public interface PlanPreferencesProxy extends EntityBaseProxy {

	public SnackTimesProxy getSnackTimes();

	public void setSnackTimes(SnackTimesProxy snackTimes);

	public boolean isSmsAlerts();

	public void setSmsAlerts(boolean smsAlerts);

	public boolean isEmailAlerts();

	public void setEmailAlerts(boolean emailAlerts);
}
