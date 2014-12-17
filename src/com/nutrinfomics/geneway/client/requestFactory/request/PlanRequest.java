package com.nutrinfomics.geneway.client.requestFactory.request;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.server.domain.plan.Plan;

@Service(Plan.class)
@ExtraTypes({PlanProxy.class, SnackProxy.class})
public interface PlanRequest extends RequestContext{

	Request<PlanProxy> findPlanForSession(SessionProxy sessionProxy);
	
	Request<SnackProxy> getNextSnack(SessionProxy session, String dateString);
}
