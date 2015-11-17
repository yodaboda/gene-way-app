package com.nutrinfomics.geneway.client.requestFactory.request;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.PersonalDetailsProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.SimpleDateProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.DeviceProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanPreferencesProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackHistoryProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackTimesProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.AbstractFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.AnimalFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.FoodItemTypeFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.SnackOrderSpecificationProxy;
import com.nutrinfomics.geneway.server.domain.EntityBase;
import com.nutrinfomics.geneway.server.domain.plan.SnackHistory;
import com.nutrinfomics.geneway.server.requestfactory.GeneWayServiceLocator;
import com.nutrinfomics.geneway.server.requestfactory.request.EntityBaseService;

@Service(value = EntityBaseService.class, locator = GeneWayServiceLocator.class)
@ExtraTypes({SnackHistoryProxy.class, SnackProxy.class, SessionProxy.class, CustomerProxy.class,
			PlanPreferencesProxy.class, SnackTimesProxy.class, SnackOrderSpecificationProxy.class,
			FoodItemTypeFoodSpecificationProxy.class, AnimalFoodSpecificationProxy.class, 
			AbstractFoodSpecificationProxy.class, PersonalDetailsProxy.class, DeviceProxy.class,
			SimpleDateProxy.class})
public interface EntityBaseRequest extends RequestContext {

	Request<Void> persist(EntityBaseProxy entityBase);
	Request<Void> merge(EntityBaseProxy entityBase);
	Request<Void> mergePersonalDetails(SessionProxy sessionProxy,
										PersonalDetailsProxy personalDetailsProxy);
}