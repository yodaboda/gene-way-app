package com.nutrinfomics.geneway.client.requestFactory.proxy.device;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.server.domain.customer.Customer;
import com.nutrinfomics.geneway.server.domain.device.Session;

@ProxyFor(Session.class)
public interface SessionProxy extends EntityProxy{

	public CustomerProxy getCustomer();

	public void setCustomer(CustomerProxy customer);

	public String getSid();

	public void setSid(String sid);

}