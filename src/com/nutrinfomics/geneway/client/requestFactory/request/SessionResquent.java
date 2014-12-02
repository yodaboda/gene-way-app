package com.nutrinfomics.geneway.client.requestFactory.request;

import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nutrinfomics.geneway.server.domain.device.Session;

@Service(Session.class)
public interface SessionResquent extends RequestContext {

}
