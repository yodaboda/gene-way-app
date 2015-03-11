package com.nutrinfomics.geneway.client.requestFactory.proxy.customer;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.customer.Credentials;

@ProxyFor(value = Credentials.class, locator = EntityBaseLocator.class)
public interface CredentialsProxy extends EntityBaseProxy {
	public String getUsername();
	public void setUsername(String username);

	public String getPassword();
	public void setPassword(String password);
}
