package com.nutrinfomics.geneway.client.requestFactory.request;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.server.domain.device.Session;
import com.nutrinfomics.geneway.server.domain.plan.Plan;
import com.nutrinfomics.geneway.server.domain.plan.Snack;
import com.nutrinfomics.geneway.shared.SnackStatus;

@Service(Plan.class)
@ExtraTypes(PlanProxy.class)
public interface PlanRequest extends RequestContext{

	Request<SnackProxy> getNextSnack(SnackProxy currentSnack, SnackStatus snackStatus, SessionProxy session, Date timestamp, int timeZoneOffset);
}
