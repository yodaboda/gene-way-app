package com.nutrinfomics.geneway.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.history.MGWTPlaceHistoryHandler;
import com.googlecode.mgwt.ui.client.util.SuperDevModeUtil;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenPlace;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace;

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
	    PlaceHistoryMapper historyMapper = ClientFactoryFactory.getClientFactory().getPlaceHistoryMapper();

	    AnimationWidget display = new AnimationWidget();
	    GeneWayActivityMapper appActivityMapper = new GeneWayActivityMapper();
	    GeneWayAnimationMapper appAnimationMapper = new GeneWayAnimationMapper();
	    AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, appAnimationMapper, ClientFactoryFactory.getClientFactory().getEventBus());
	    activityManager.setDisplay(display);
	    RootPanel.get().add(display);

	    
	    GeneWayHistoryObserver historyObserver = new GeneWayHistoryObserver();
	    MGWTPlaceHistoryHandler historyHandler = new MGWTPlaceHistoryHandler(historyMapper, historyObserver);
	    Place place;
	    if(ClientFactoryFactory.getClientFactory().getSID() != null) place = new WaitingPlace();
	    else place = new FirstScreenPlace();
	    historyHandler.register(ClientFactoryFactory.getClientFactory().getPlaceController(), ClientFactoryFactory.getClientFactory().getEventBus(), place);
	    historyHandler.handleCurrentHistory();
	}

}
