package com.nutrinfomics.geneway.client.requestFactory.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.server.domain.customer.Customer;

@Service(Customer.class)
public interface CustomerRequest extends RequestContext {

//	InstanceRequest<CustomerProxy, Void> persist();
//
//	InstanceRequest<CustomerProxy, Void> remove();
}
