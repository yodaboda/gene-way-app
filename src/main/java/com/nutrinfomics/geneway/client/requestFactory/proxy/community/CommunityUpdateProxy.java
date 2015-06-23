package com.nutrinfomics.geneway.client.requestFactory.proxy.community;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.community.CommunityUpdate;

@ProxyFor(value = CommunityUpdate.class, locator = EntityBaseLocator.class)
public interface CommunityUpdateProxy extends EntityBaseProxy {

}
