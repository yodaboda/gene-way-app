package com.nutrinfomics.geneway.client.requestFactory.proxy.plan;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.plan.SnackMenu;

@ProxyFor(value = SnackMenu.class, locator = EntityBaseLocator.class)
public interface SnackMenuProxy extends EntityProxy{

	public List<SnackProxy> getSnacks();

}