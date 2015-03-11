package com.nutrinfomics.geneway.client.requestFactory.proxy.specification;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.specification.FoodItemTypeFoodSpecification;
import com.nutrinfomics.geneway.shared.FoodItemType;

@ProxyFor(value = FoodItemTypeFoodSpecification.class, locator = EntityBaseLocator.class)
public interface FoodItemTypeFoodSpecificationProxy extends AbstractFoodSpecificationProxy{

	public FoodItemType getFoodItemType();

	public void setFoodItemType(FoodItemType foodItemType);
}
