package com.nutrinfomics.geneway.client.requestFactory.request;

import java.util.List;
import java.util.Set;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanPreferencesProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackHistoryProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.AbstractFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.AcceptAllSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.AnimalFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.FoodItemTypeFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.SnackOrderSpecificationProxy;
import com.nutrinfomics.geneway.server.requestfactory.GeneWayServiceLocator;
import com.nutrinfomics.geneway.server.requestfactory.request.PlanService;
import com.nutrinfomics.geneway.shared.FoodItemType;

@Service(value = PlanService.class, locator = GeneWayServiceLocator.class)
@ExtraTypes({PlanProxy.class, SnackProxy.class, SnackHistoryProxy.class, SnackOrderSpecificationProxy.class,
			FoodItemTypeFoodSpecificationProxy.class, AnimalFoodSpecificationProxy.class, AbstractFoodSpecificationProxy.class,
			AcceptAllSpecificationProxy.class})
public interface PlanRequest extends RequestContext{
	
	Request<Void> setDemo(SessionProxy session);

	
	Request<SnackProxy> getNextSnack(SessionProxy session, String dateString);

	Request<Set<FoodItemType>> getIngredients(SessionProxy sessionProxy, String dateString);

	Request<List<String>> getMenuSummary(SessionProxy sessionProxy, String dateString);
	
	Request<PlanPreferencesProxy> getPlanPreferences(SessionProxy session);
	
	Request<Void> markCurrentSnack(SessionProxy session, SnackProxy snack, SnackHistoryProxy snackHistory);
	
	Request<SnackOrderSpecificationProxy> getSnackOrderSpecification(SessionProxy session);

	Request<Void> updateSpecifications(SnackOrderSpecificationProxy snackOrderSpecificationEdit,
																AbstractFoodSpecificationProxy oldFoodSpecification);
}