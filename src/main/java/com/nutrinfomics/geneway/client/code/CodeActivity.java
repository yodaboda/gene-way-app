package com.nutrinfomics.geneway.client.code;

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

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs.AlertCallback;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.shared.AuthenticationException;
import com.nutrinfomics.geneway.shared.AuthenticationException.AuthenticationExceptionType;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public class CodeActivity extends MGWTAbstractActivity {
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);

		final CodeView codeView = ClientFactoryFactory.getClientFactory().getCodeView();

		final GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();

		
		addHandlerRegistration(codeView.getResendCodeButton().addTapHandler(new TapHandler() {

			@Override
			public void onTap(TapEvent event) {
				// TODO Auto-generated method stub
				
			}
		}));
		
		addHandlerRegistration(codeView.getVerifyButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				AuthenticationRequest authenticationRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest();
				CustomerProxy customerProxy = ClientFactoryFactory.getClientFactory().buildCustomer(authenticationRequest);
				customerProxy.getDevice().setCode(codeView.getEnteredCode());
				Request<Boolean> authenticateCode = authenticationRequest.authenticateCode(customerProxy);
				authenticateCode.fire(new GeneWayReceiver<Boolean>() {
					@Override
					public void onSuccess(Boolean success) {
						if(success){
							ClientFactoryFactory.getClientFactory().setExistingCustomer(true);
							Dialogs.alert(constants.verified(), constants.phoneVerified(), new AlertCallback() {
								@Override
								public void onButtonPressed() {
									ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new LoginPlace());
								}
							});
						}
						else{
							Dialogs.alert(constants.wrongCodeTitle(), constants.wrongCodeMessage(), null);
						}						
					
					}
					@Override
					public void onFailure(ServerFailure error) {
						String errorMessage = error.getMessage();
//						Dialogs.alert("error", errorMessage, null);
						if(error.getExceptionType().equals(AuthenticationException.class.toString()) &&
								errorMessage.equals(AuthenticationExceptionType.EXPIRED.toString()) ){
							Dialogs.alert(constants.codeExpiredTitle(), constants.codeExpiredText(), null);
						}
						else{
							Dialogs.alert(constants.wrongCodeTitle(), constants.wrongCodeMessage(), null);
						}
					}
					
				});
			}
		}));
		
		panel.setWidget(codeView);
	}
}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */