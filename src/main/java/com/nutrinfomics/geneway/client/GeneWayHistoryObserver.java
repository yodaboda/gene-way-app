package com.nutrinfomics.geneway.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.mvp.client.history.HistoryHandler;
import com.googlecode.mgwt.mvp.client.history.HistoryObserver;
import com.googlecode.mgwt.ui.client.MGWT;
import com.nutrinfomics.geneway.client.event.ActionEvent;

public class GeneWayHistoryObserver implements HistoryObserver {

	@Override
	public void onPlaceChange(Place place, HistoryHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHistoryChanged(Place place, HistoryHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAppStarted(Place place, HistoryHandler historyHandler) {
		// TODO Auto-generated method stub

	}

	@Override
	public HandlerRegistration bind(EventBus eventBus, HistoryHandler historyHandler) {
		HandlerRegistration register = ActionEvent.register(eventBus, ActionEvent.ActionNames.BACK.toString(), new ActionEvent.Handler() {
			@Override
			public void onAction(ActionEvent event) {
				History.back();
			}
		});

		HandlerRegistrationCollection col = new HandlerRegistrationCollection();
		col.addHandlerRegistration(register);
		return col;
	}

}
