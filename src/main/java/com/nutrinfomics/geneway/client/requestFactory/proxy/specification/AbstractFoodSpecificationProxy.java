package com.nutrinfomics.geneway.client.requestFactory.proxy.specification;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.specification.AbstractFoodSpecification;

@ProxyFor(value = AbstractFoodSpecification.class, locator = EntityBaseLocator.class)
public interface AbstractFoodSpecificationProxy extends EntityBaseProxy{

}
