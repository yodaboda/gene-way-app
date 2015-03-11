package com.nutrinfomics.geneway.client.requestFactory.proxy.device;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.customer.Customer;
import com.nutrinfomics.geneway.server.domain.device.Session;

@ProxyFor(value = Session.class, locator = EntityBaseLocator.class)
public interface SessionProxy extends EntityBaseProxy{

	public CustomerProxy getCustomer();

	public void setCustomer(CustomerProxy customer);

	public String getSid();

	public void setSid(String sid);

}