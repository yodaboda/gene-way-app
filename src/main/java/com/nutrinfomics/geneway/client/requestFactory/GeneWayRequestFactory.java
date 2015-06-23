package com.nutrinfomics.geneway.client.requestFactory;

import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.CommunityRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.EntityBaseRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;

public interface GeneWayRequestFactory extends RequestFactory {
//	DeviceRequest deviceRequest();
	AuthenticationRequest authenticationRequest();
	PlanRequest planRequest();
	EntityBaseRequest entityBaseRequest();
	CommunityRequest communityRequest();
}
