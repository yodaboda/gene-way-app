package com.nutrinfomics.geneway.client.requestFactory.contact;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.contact.ContactInformation;

@ProxyFor(value = ContactInformation.class, locator = EntityBaseLocator.class)
public interface ContactInformationProxy extends EntityBaseProxy {

	public String getRegisteredPhoneNumber();
	
	public void setRegisteredPhoneNumber(String registeredPhoneNumber);
}
