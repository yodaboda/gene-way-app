package com.nutrinfomics.geneway.client.code;

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
				SessionProxy session = ClientFactoryFactory.getClientFactory().buildSession(authenticationRequest);
				session.getCustomer().getDevice().setCode(codeView.getEnteredCode());
				Request<SessionProxy> authenticateCode = authenticationRequest.authenticateCode(session);
				authenticateCode.with("customer", "customer.device");
				authenticateCode.fire(new GeneWayReceiver<SessionProxy>() {
					@Override
					public void onSuccess(SessionProxy sessionProxy) {
						if(sessionProxy != null){
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
