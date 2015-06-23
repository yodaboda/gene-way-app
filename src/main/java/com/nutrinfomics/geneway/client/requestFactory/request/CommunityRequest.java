package com.nutrinfomics.geneway.client.requestFactory.request;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nutrinfomics.geneway.client.requestFactory.proxy.community.CommunityUpdateProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.server.requestfactory.GeneWayServiceLocator;
import com.nutrinfomics.geneway.server.requestfactory.request.CommunityService;

@Service(value = CommunityService.class, locator = GeneWayServiceLocator.class)
@ExtraTypes({})
public interface CommunityRequest extends RequestContext {
	Request<List<CommunityUpdateProxy>> communityUpdates(SessionProxy session);
}
