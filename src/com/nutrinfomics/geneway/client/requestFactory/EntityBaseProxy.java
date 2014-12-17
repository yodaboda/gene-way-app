package com.nutrinfomics.geneway.client.requestFactory;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.server.domain.EntityBase;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;

@ProxyFor(value = EntityBase.class, locator = EntityBaseLocator.class)
public interface EntityBaseProxy extends EntityProxy {

}
