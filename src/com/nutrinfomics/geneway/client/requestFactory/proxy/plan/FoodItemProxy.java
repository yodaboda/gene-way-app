package com.nutrinfomics.geneway.client.requestFactory.proxy.plan;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.plan.FoodItem;
import com.nutrinfomics.geneway.shared.FoodItemType;
import com.nutrinfomics.geneway.shared.MeasurementUnit;

@ProxyFor(value = FoodItem.class, locator = EntityBaseLocator.class)
public interface FoodItemProxy extends EntityBaseProxy{

	public double getAmount();

	public void setAmount(double amount);

	public MeasurementUnit getMeasurementUnit();

	public void setMeasurementUnit(MeasurementUnit measurementUnit);

	public FoodItemType getFoodType();

	public void setFoodType(FoodItemType foodType);

}