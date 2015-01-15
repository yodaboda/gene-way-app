package com.nutrinfomics.geneway.client.login;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.home.HomePlace;
import com.nutrinfomics.geneway.client.login.AuthenticationException.LoginExceptionType;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace;
import com.nutrinfomics.geneway.shared.AccessConstants;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public class LoginActivity extends MGWTAbstractActivity {

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
		
		ClientFactoryFactory.getClientFactory().setUsername(loginView.getUsername());
		ClientFactoryFactory.getClientFactory().setPassword(loginView.getPassword());
		
		ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new WaitingPlace());				  
	}

}
