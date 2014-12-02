package com.nutrinfomics.geneway.client.requestFactory.proxy.plan;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.server.domain.plan.SnackMenu;

@ProxyFor(SnackMenu.class)
public interface SnackMenuProxy extends EntityProxy{

	public List<SnackProxy> getSnacks();

}