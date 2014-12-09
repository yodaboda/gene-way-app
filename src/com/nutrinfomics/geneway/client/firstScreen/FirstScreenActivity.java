package com.nutrinfomics.geneway.client.firstScreen;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.client.register.RegisterPlace;

public class FirstScreenActivity extends MGWTAbstractActivity {

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);

		FirstScreenView firstScreenView = ClientFactoryFactory.getClientFactory().getFirstScreenView();
		
		addHandlerRegistration(firstScreenView.getNewAccountButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new RegisterPlace());
			}
		}));
		
		addHandlerRegistration(firstScreenView.getExistingAccountButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new LoginPlace());
			}
		}));

		
		panel.setWidget(firstScreenView);

	}
}
