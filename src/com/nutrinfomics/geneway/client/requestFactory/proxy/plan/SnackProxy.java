package com.nutrinfomics.geneway.client.requestFactory.proxy.plan;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.plan.Snack;
import com.nutrinfomics.geneway.shared.SnackProperty;

@ProxyFor(value = Snack.class, locator = EntityBaseLocator.class)
public interface SnackProxy extends EntityProxy{

	public List<FoodItemProxy> getFoodItems();

	public SnackProperty getSnackProperty();

	public void setSnackProperty(SnackProperty snackProperty);


}