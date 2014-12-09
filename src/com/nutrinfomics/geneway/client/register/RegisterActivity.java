package com.nutrinfomics.geneway.client.register;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.gwtphonegap.client.util.PhonegapUtil;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.client.register.RegisterException.RegisterExceptionType;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.server.domain.customer.Customer;
import com.nutrinfomics.geneway.server.domain.customer.PersonalDetails;
import com.nutrinfomics.geneway.server.domain.device.Device;
import com.nutrinfomics.geneway.server.domain.device.Session;

public class RegisterActivity extends MGWTAbstractActivity {
//	private RegisterServiceAsync registerService = GWT.create(RegisterService.class);
	private RegisterView registerView;
	
	public RegisterActivity(){
//		PhonegapUtil.prepareService((ServiceDefTarget) registerService, ClientFactoryFactory.getClientFactory().getModuleURL(), "register");
	}
	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		registerView = ClientFactoryFactory.getClientFactory().getRegisterView();
		
		registerView.hideBackButton();
		registerView.showAboutButton();
		
		addHandlerRegistration(registerView.getRegisterButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				register();
			}
		}));
		
		panel.setWidget(registerView);
	}

	protected void register() {
		AuthenticationRequest authenticationRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest();

		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().buildSession(authenticationRequest);
		sessionProxy.getCustomer().setUsername(registerView.getUsername());
		sessionProxy.getCustomer().setPassword(registerView.getPassword());
		
		authenticationRequest.registerCustomer(sessionProxy.getCustomer()).fire(new GeneWayReceiver<CustomerProxy>() {
			private GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();

			public void onFailure(ServerFailure error) {
				if(error.getExceptionType().equals(RegisterException.class.toString())){
					if(error.getMessage().equals(RegisterExceptionType.USERNAME_EXISTS.toString())){
						Window.alert(constants.usernameExists());
					}
					//TODO: deal with other cases
				}
				else{
					Window.alert(constants.error());
				}
			}

			@Override
			public void onSuccess(CustomerProxy response) {
				Window.alert(constants.registrationSuccessful());
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new LoginPlace());
			}
		});;
	}

}