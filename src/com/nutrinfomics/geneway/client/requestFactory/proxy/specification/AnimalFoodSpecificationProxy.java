package com.nutrinfomics.geneway.client.requestFactory.proxy.specification;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.specification.AnimalFoodSpecification;

@ProxyFor(value = AnimalFoodSpecification.class, locator = EntityBaseLocator.class)
public interface AnimalFoodSpecificationProxy extends AbstractFoodSpecificationProxy{

}
