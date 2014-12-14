package com.nutrinfomics.geneway.client.home;

import java.util.Date;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.shared.SnackStatus;

public class HomeActivity extends MGWTAbstractActivity {
	private HomeView homeView;
	
	public HomeActivity(){
		super();
	}
	
	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		homeView = ClientFactoryFactory.getClientFactory().getHomeView();
		panel.setWidget(homeView);	
//		homeView.start();
		PlanRequest planRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest();
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().getNewSession(planRequest);
		Date date = new Date();
		SnackProxy snackProxy = planRequest.create(SnackProxy.class);
		@SuppressWarnings("deprecation")
		int timezoneOffset = date.getTimezoneOffset();
		
		
		planRequest.getNextSnack(snackProxy, SnackStatus.UNKNOWN, sessionProxy, date, timezoneOffset).fire(new GeneWayReceiver<SnackProxy>() {
			@Override
			public void onFailure(ServerFailure error) {
				Window.alert(error.getMessage());
			}
			@Override
			public void onSuccess(SnackProxy snackProxy) {
				homeView.setSnack(snackProxy);
			}
		});
	}
}
