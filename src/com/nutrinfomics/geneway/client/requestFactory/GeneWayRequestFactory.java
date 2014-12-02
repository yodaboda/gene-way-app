package com.nutrinfomics.geneway.client.requestFactory;

import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.nutrinfomics.geneway.client.requestFactory.request.CustomerRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.SessionResquent;

public interface GeneWayRequestFactory extends RequestFactory {
	CustomerRequest customerRequest();
	SessionResquent sessionRequest();
//	DeviceRequest deviceRequest();
	AuthenticationRequest authenticationRequest();
	PlanRequest planRequest();
}
