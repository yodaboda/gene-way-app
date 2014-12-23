package com.nutrinfomics.geneway.client.waiting;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs.AlertCallback;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.home.HomePlace;
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.login.AuthenticationException;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.client.login.AuthenticationException.LoginExceptionType;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.shared.AccessConstants;
import com.nutrinfomics.geneway.shared.SnackStatus;

public class WaitingActivity extends MGWTAbstractActivity {

	private WaitingView waitingView;

	
	public WaitingActivity(){
	}
	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);

		waitingView = ClientFactoryFactory.getClientFactory().getWaitingView();
		panel.setWidget(waitingView);
		
		if(ClientFactoryFactory.getClientFactory().getSID() != null) authenticateSID();
		else authenticateUsernamePassword();
	}	
	
	
	protected void authenticateUsernamePassword() {
		AuthenticationRequest authenticationRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest();
		
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().buildSession(authenticationRequest);
		final String username = ClientFactoryFactory.getClientFactory().getUsername();
		sessionProxy.getCustomer().setUsername(username);
		final String password = ClientFactoryFactory.getClientFactory().getPassword();
		sessionProxy.getCustomer().setPassword(password);

		authenticationRequest.authenticateCustomer(sessionProxy.getCustomer()).fire(new GeneWayReceiver<SessionProxy>() {
			@Override
			public void onSuccess(SessionProxy session){
				ClientFactoryFactory.getClientFactory().setPassword(null);
				ClientFactoryFactory.getClientFactory().setUsername(null);
				success(session);
			}
			@Override
			public void onFailure(ServerFailure error) {
				ClientFactoryFactory.getClientFactory().setPassword(null);
				ClientFactoryFactory.getClientFactory().setUsername(null);
				
				String errorMessage = error.getMessage();

				GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();

				AlertCallback callBack = new AlertCallback() {
					
					@Override
					public void onButtonPressed() {
						ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new LoginPlace());
					}
				};
				
				if(error.getExceptionType().equals(AuthenticationException.class.toString())){
//					AuthenticationException loginException = (AuthenticationException)caught;
					if(errorMessage.equals(LoginExceptionType.INVALID_USERNAME.toString()) || 
							errorMessage.equals(LoginExceptionType.INVALID_PASSWORD.toString())){
						Dialogs.alert(constants.invalidLoginTitle(), constants.invalidLogin(), callBack);
					}
					else if(errorMessage.equals(LoginExceptionType.UNAUTHORIZED_DEVICE.toString())){
						Dialogs.alert(constants.unauthorizedDeviceTitle(), constants.unauthorizedDevice(), callBack);
					}
				}
				else{
					Dialogs.alert(constants.error(), errorMessage, callBack);
				}

			}
		});
	}

	
	private void authenticateSID(){
		AuthenticationRequest authenticationRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest();
		
		final SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().buildSession(authenticationRequest);
		authenticationRequest.authenticateSession(sessionProxy).fire(new GeneWayReceiver<SessionProxy>() {
			@Override
			public void onSuccess(SessionProxy session){
				success(session);
			}
			@Override
			public void onFailure(ServerFailure error) {
				Cookies.removeCookie(AccessConstants.SID.toString());
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new LoginPlace());
			}
		});

	}
	public void success(SessionProxy session) {
		ClientFactoryFactory.getClientFactory().setSID(session.getSid());
		Timer timer = new Timer(){
			  @Override
			     public void run() {
				  ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new HomePlace());				  
			  }
		};
		
		timer.schedule(2000);
	}

}
