package com.nutrinfomics.geneway.client.requestFactory.proxy.identifier;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.identifier.Identifier;

@ProxyFor(value = Identifier.class, locator = EntityBaseLocator.class)
public interface IdentifierProxy extends EntityBaseProxy {

	public String getUuid();
	
	public void setUuid(String uuid);

	public String getIdentifierCode();

	public void setIdentifierCode(String identifierCode);
}
