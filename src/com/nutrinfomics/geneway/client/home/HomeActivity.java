package com.nutrinfomics.geneway.client.home;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;

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
	}
}
