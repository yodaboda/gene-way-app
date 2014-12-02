package com.nutrinfomics.geneway.client.requestFactory.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.server.domain.authentication.Authentication;

@Service(Authentication.class)
public interface AuthenticationRequest extends RequestContext {
	Request<CustomerProxy> authenticateCustomer(String userName, String password, String uuid);
	
	Request<CustomerProxy> authenticateSession(String sid, String uuid);

	Request<CustomerProxy> registerCustomer(String username, String password, String uuid);
}
