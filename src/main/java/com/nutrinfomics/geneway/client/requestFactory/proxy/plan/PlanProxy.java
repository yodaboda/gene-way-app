package com.nutrinfomics.geneway.client.requestFactory.proxy.plan;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.plan.Plan;
import com.nutrinfomics.geneway.shared.ActivitiesType;
import com.nutrinfomics.geneway.shared.SupplementType;

@ProxyFor(value = Plan.class, locator = EntityBaseLocator.class)
public interface PlanProxy extends EntityBaseProxy{

	public SnackMenuProxy getSnackMenu();

	public void setSnackMenu(SnackMenuProxy snackMenu);

	public List<ActivitiesType> getActivities();

	public void setActivities(List<ActivitiesType> activities);

	public List<SupplementType> getSupplements();

	public void setSupplements(List<SupplementType> supplements);

}