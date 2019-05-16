package com.nutrinfomics.geneway.client;

/*
 * Copyright (C) 2019 Firas Swidan†
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

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
import com.nutrinfomics.geneway.client.personalIdentifier.PersonalIdentifierPlace;
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
	    if(! ClientFactoryFactory.getClientFactory().isIdentified()) place = new PersonalIdentifierPlace();
	    else if(ClientFactoryFactory.getClientFactory().isLoggedIn()) place = new WaitingPlace();
	    else place = new FirstScreenPlace();
	    historyHandler.register(ClientFactoryFactory.getClientFactory().getPlaceController(), ClientFactoryFactory.getClientFactory().getEventBus(), place);
	    historyHandler.handleCurrentHistory();
	}

}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */