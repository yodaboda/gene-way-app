package com.nutrinfomics.geneway.client.requestFactory.request;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.DeviceProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.server.domain.authentication.Authentication;

@Service(Authentication.class)
@ExtraTypes({CustomerProxy.class, SessionProxy.class, DeviceProxy.class})
public interface AuthenticationRequest extends RequestContext {
	Request<SessionProxy> authenticateCustomer(CustomerProxy customerProxy);
	
	Request<SessionProxy> authenticateSession(SessionProxy sessionProxy);

	Request<CustomerProxy> registerCustomer(CustomerProxy customerProxy);
}
