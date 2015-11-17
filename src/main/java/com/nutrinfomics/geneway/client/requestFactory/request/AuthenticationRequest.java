package com.nutrinfomics.geneway.client.requestFactory.request;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nutrinfomics.geneway.client.requestFactory.contact.ContactInformationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CredentialsProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.PersonalDetailsProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.SimpleDateProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.DeviceProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.identifier.IdentifierProxy;
import com.nutrinfomics.geneway.server.requestfactory.GeneWayServiceLocator;
import com.nutrinfomics.geneway.server.requestfactory.request.AuthenticationService;

@Service(value = AuthenticationService.class, locator = GeneWayServiceLocator.class)
@ExtraTypes({CustomerProxy.class, SessionProxy.class, DeviceProxy.class, CredentialsProxy.class,
			ContactInformationProxy.class, PersonalDetailsProxy.class, SimpleDateProxy.class,
			IdentifierProxy.class})
public interface AuthenticationRequest extends RequestContext {
	
	Request<SessionProxy> authenticateCustomer(CustomerProxy customerProxy);
	Request<SessionProxy> authenticateSession(SessionProxy sessionProxy);
	
	Request<Void> register(CustomerProxy customerProxy);
	Request<Boolean> authenticateCode(CustomerProxy customerProxy);
	
	Request<Boolean> unlock(IdentifierProxy identifierProxy);
	Request<String> confirmValuationTermsOfService(String uuid);
}