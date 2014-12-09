package com.nutrinfomics.geneway.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.history.MGWTPlaceHistoryHandler;
import com.googlecode.mgwt.ui.client.util.SuperDevModeUtil;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenPlace;
import com.nutrinfomics.geneway.client.login.LoginPlace;

public class GeneWayEntryPoint implements EntryPoint {

	public void onModuleLoad() {
	    SuperDevModeUtil.showDevMode();
		
	    ClientFactoryFactory.getClientFactory().init(new Runnable() {
			@Override
			public void run() {
				start();
			}
		});

	}

	protected void start(){
	    GeneWayPlaceHistoryMapper historyMapper = GWT.create(GeneWayPlaceHistoryMapper.class);

	    AnimationWidget display = new AnimationWidget();
	    GeneWayActivityMapper appActivityMapper = new GeneWayActivityMapper();
	    GeneWayAnimationMapper appAnimationMapper = new GeneWayAnimationMapper();
	    AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, appAnimationMapper, ClientFactoryFactory.getClientFactory().getEventBus());
	    activityManager.setDisplay(display);
	    RootPanel.get().add(display);

	    
	    GeneWayHistoryObserver historyObserver = new GeneWayHistoryObserver();
	    MGWTPlaceHistoryHandler historyHandler = new MGWTPlaceHistoryHandler(historyMapper, historyObserver);
	    historyHandler.register(ClientFactoryFactory.getClientFactory().getPlaceController(), ClientFactoryFactory.getClientFactory().getEventBus(), new FirstScreenPlace());
	    historyHandler.handleCurrentHistory();
	}

}
