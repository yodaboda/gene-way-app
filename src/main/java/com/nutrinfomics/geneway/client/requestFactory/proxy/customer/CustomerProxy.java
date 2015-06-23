package com.nutrinfomics.geneway.client.requestFactory.proxy.customer;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.client.requestFactory.contact.ContactInformationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.DeviceProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.customer.Customer;

@ProxyFor(value = Customer.class, locator = EntityBaseLocator.class)
public interface CustomerProxy extends EntityBaseProxy{

//	public Subscription getSubscription();
//
//	public void setSubscription(Subscription subscription);

	public String getNickName();
	public void setNickName(String nickName);
	
	public SessionProxy getSession();
	public void setSession(SessionProxy session);

	public CredentialsProxy getCredentials();
	public void setCredentials(CredentialsProxy credentials);

	public DeviceProxy getDevice();
	public void setDevice(DeviceProxy device);

	public ContactInformationProxy getContactInformation();
	public void setContactInformation(ContactInformationProxy contactInformation);

	public PersonalDetailsProxy getPersonalDetails();
	public void setPersonalDetails(PersonalDetailsProxy personalDetails);
	
		//	public Status getStatus();

//	public void setStatus(Status status);

}