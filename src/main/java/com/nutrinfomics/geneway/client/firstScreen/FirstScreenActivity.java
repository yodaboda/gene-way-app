package com.nutrinfomics.geneway.client.firstScreen;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.client.register.RegisterPlace;
import com.nutrinfomics.geneway.client.util.GeneWayAbstractActivity;

public class FirstScreenActivity extends GeneWayAbstractActivity {

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);

		final FirstScreenView firstScreenView = ClientFactoryFactory.getClientFactory().getFirstScreenView();
		
		addHandlerRegistration(firstScreenView.getNewAccountButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
//				if(firstScreenView.getPassword().equals("sfae")){
//					ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new RegisterPlace());
//				}

				if(ClientFactoryFactory.getClientFactory().isExistingCustomer()){
					ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new LoginPlace());
				}
				else{
					ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new RegisterPlace());					
				}
			}
		}));
		
		addHandlerRegistration(firstScreenView.getAccountAnchorButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new RegisterPlace());
			}
		}));

//		addHandlerRegistration(firstScreenView.getNewAccountAnchor().addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new RegisterPlace());
//			    event.preventDefault();
//			}
//		}));
		
		panel.setWidget(firstScreenView);

	}
}
