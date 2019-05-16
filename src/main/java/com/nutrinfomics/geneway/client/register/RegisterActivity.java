package com.nutrinfomics.geneway.client.register;

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

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.code.CodePlace;
import com.nutrinfomics.geneway.client.privacyPolicy.PrivacyPolicyPlace;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.contact.ContactInformationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CredentialsProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.DeviceProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.client.termsOfService.TermsOfServicePlace;
import com.nutrinfomics.geneway.client.util.GeneWayAbstractActivity;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public class RegisterActivity extends GeneWayAbstractActivity {
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

		addHandlerRegistration(registerView.getPrivacyButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new PrivacyPolicyPlace());				
			}
		}));

		addHandlerRegistration(registerView.getTermsButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new TermsOfServicePlace());				
			}
		}));

		panel.setWidget(registerView);
	}
	
	protected void register(){
		if(!registerView.isTermsBoxChecked()){
			Dialogs.alert(constants.acceptTermsTitle(), constants.acceptTermsText(), null);
			return;
		}
		final String phoneNumber = registerView.getPhoneNumber();
		AuthenticationRequest authenticationRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest();

		CustomerProxy customerProxy = ClientFactoryFactory.getClientFactory().buildCustomer(authenticationRequest);
		CredentialsProxy credentials = authenticationRequest.create(CredentialsProxy.class);
		ContactInformationProxy contactInformation = authenticationRequest.create(ContactInformationProxy.class);
		
		customerProxy.setNickName(registerView.getNickName());
		customerProxy.setCredentials(credentials);
		credentials.setPassword(registerView.getPassword());
	
		customerProxy.setContactInformation(contactInformation);
		contactInformation.setRegisteredPhoneNumber(phoneNumber);
		
		authenticationRequest.register(customerProxy).fire(new GeneWayReceiver<Void>() {
			@Override
			public void onFailure(ServerFailure error){
				registerView.hideLoader();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> violations){
				registerView.hideLoader();
				registerView.constraintViolations(violations);
				super.onConstraintViolation(violations);
			}
			@Override
			public void onSuccess(Void vd) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new CodePlace());				
			}
		});
		registerView.displayLoader();
	}
//	protected void register() {
//		EntityBaseRequest authenticationRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().entityBaseRequest();
//
//		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().buildSession(authenticationRequest);
//		sessionProxy.getCustomer().setUsername(registerView.getUsername());
//		sessionProxy.getCustomer().setPassword(registerView.getPassword());
//		
//		authenticationRequest.persist(sessionProxy.getCustomer()).fire(new GeneWayReceiver<Void>() {
//			private GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();
//
//			public void onFailure(ServerFailure error) {
//				if(error.getExceptionType().equals(RegisterException.class.toString())){
//					if(error.getMessage().equals(RegisterExceptionType.USERNAME_EXISTS.toString())){
//						Window.alert(constants.usernameExists());
//					}
//					//TODO: deal with other cases
//				}
//				else{
//					Window.alert(constants.error());
//				}
//			}
//
//			@Override
//			public void onSuccess(Void response) {
//				Window.alert(constants.registrationSuccessful());
//				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new LoginPlace());
//			}
//		});;
//	}

}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */