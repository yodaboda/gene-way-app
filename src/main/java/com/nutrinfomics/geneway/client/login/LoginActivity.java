package com.nutrinfomics.geneway.client.login;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.util.GeneWayAbstractActivity;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace;

public class LoginActivity extends GeneWayAbstractActivity {

	private LoginView loginView;
	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);

		loginView = ClientFactoryFactory.getClientFactory().getLoginView();

		loginView.showAboutButton();
		loginView.hideBackButton();
		
		addHandlerRegistration(loginView.getLoginButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				login();
			}
		}));
		panel.setWidget(loginView);
	}

	protected void login() {
		
		ClientFactoryFactory.getClientFactory().setPassword(loginView.getPassword());
		
		ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new WaitingPlace());				  
	}

}
