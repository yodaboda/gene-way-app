package com.nutrinfomics.geneway.client.requestFactory.proxy.customer;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.DeviceProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.customer.Customer;
import com.nutrinfomics.geneway.server.domain.customer.PersonalDetails;
import com.nutrinfomics.geneway.server.domain.device.Device;
import com.nutrinfomics.geneway.server.domain.device.Session;
import com.nutrinfomics.geneway.server.domain.status.Status;
import com.nutrinfomics.geneway.server.domain.subscription.Subscription;

@ProxyFor(value = Customer.class, locator = EntityBaseLocator.class)
public interface CustomerProxy extends EntityBaseProxy{

//	public Subscription getSubscription();
//
//	public void setSubscription(Subscription subscription);

	public String getUsername();

	public void setUsername(String username);

//	public PersonalDetails getPersonalDetails();
//
//	public void setPersonalDetails(PersonalDetails personalDetails);

	public SessionProxy getSession();

	public void setSession(SessionProxy session);

	public String getPassword();

	public void setPassword(String password);

	public DeviceProxy getDevice();

	public void setDevice(DeviceProxy device);

//	public Status getStatus();

//	public void setStatus(Status status);

}