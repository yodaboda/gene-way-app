package com.nutrinfomics.geneway.client.requestFactory.proxy.customer;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.customer.PersonalDetails;
import com.nutrinfomics.geneway.server.domain.customer.SimpleDate;
import com.nutrinfomics.geneway.shared.Gender;

@ProxyFor(value = PersonalDetails.class, locator = EntityBaseLocator.class)
public interface PersonalDetailsProxy extends EntityBaseProxy {

	public CustomerProxy getCustomer();
	public void setCustomer(CustomerProxy customer);

	public SimpleDateProxy getBirthday();
	public void setBirthday(SimpleDateProxy birthday);
	
	public Gender getGender();
	public void setGender(Gender gender);

}
