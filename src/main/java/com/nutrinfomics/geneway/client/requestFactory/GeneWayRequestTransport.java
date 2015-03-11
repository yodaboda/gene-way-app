package com.nutrinfomics.geneway.client.requestFactory;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.web.bindery.requestfactory.gwt.client.DefaultRequestTransport;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.shared.AccessConstants;

public class GeneWayRequestTransport extends DefaultRequestTransport {
    @Override
    protected RequestCallback createRequestCallback(final TransportReceiver receiver){
        final RequestCallback superCallback = super.createRequestCallback(receiver);

        return new RequestCallback(){
            public void onResponseReceived(Request request, Response response){
                if (Response.SC_UNAUTHORIZED == response.getStatusCode()){
                	ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new LoginPlace());
                }
                else{
                    superCallback.onResponseReceived(request, response);
                }
            }

            public void onError(Request request, Throwable exception){
                superCallback.onError(request, exception);
            }

        };
    }
    @Override
    protected void configureRequestBuilder(RequestBuilder builder){
    	String sid = ClientFactoryFactory.getClientFactory().getSID();
    	if(sid != null) builder.setHeader(AccessConstants.SID.toString(), sid);
    	builder.setHeader(AccessConstants.UUID.toString(), ClientFactoryFactory.getClientFactory().getUUID());
    	super.configureRequestBuilder(builder);
    }
}
