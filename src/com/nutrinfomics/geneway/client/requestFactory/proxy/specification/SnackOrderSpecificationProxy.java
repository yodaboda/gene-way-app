package com.nutrinfomics.geneway.client.requestFactory.proxy.specification;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.specification.SnackOrderSpecification;

@ProxyFor(value = SnackOrderSpecification.class, locator = EntityBaseLocator.class)
public interface SnackOrderSpecificationProxy extends EntityBaseProxy{

	public List<AbstractFoodSpecificationProxy> getFoodOrderSpecification();

	public void setFoodOrderSpecification(List<AbstractFoodSpecificationProxy> foodOrderSpecification);

}
