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
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.login.AuthenticationException.LoginExceptionType;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace;
import com.nutrinfomics.geneway.shared.AccessConstants;

public class LoginActivity extends MGWTAbstractActivity {

	private LoginView loginView;
	private AcceptsOneWidget panel;
	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		
		this.panel = panel;
		String sid = ClientFactoryFactory.getClientFactory().getSID();
		
		if(sid != null){
			loginSID(sid);
		}
		else{
			showLoginView(this.panel);
		}
	}


	private void showLoginView(AcceptsOneWidget panel) {
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

	private void loginSID(String sid) {

		AuthenticationRequest authenticationRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest();
		
		final SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().buildSession(authenticationRequest);
		sessionProxy.setSid(sid);
		authenticationRequest.authenticateSession(sessionProxy).fire(new GeneWayReceiver<SessionProxy>() {
			@Override
			public void onSuccess(SessionProxy session){
				success(session);
			}
			@Override
			public void onFailure(ServerFailure error) {
				Cookies.removeCookie(AccessConstants.SID.toString());
				showLoginView(panel);
			}
		});
	}

	
	protected void login() {
		AuthenticationRequest authenticationRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest();
		
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().buildSession(authenticationRequest);
		sessionProxy.getCustomer().setUsername(loginView.getUsername());
		sessionProxy.getCustomer().setPassword(loginView.getPassword());

		authenticationRequest.authenticateCustomer(sessionProxy.getCustomer()).fire(new GeneWayReceiver<SessionProxy>() {
			@Override
			public void onSuccess(SessionProxy session){
				success(session);
			}
			@Override
			public void onFailure(ServerFailure error) {
				String errorMessage = error.getMessage();
				if(error.getExceptionType().equals(AuthenticationException.class.toString())){
//					AuthenticationException loginException = (AuthenticationException)caught;
					GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();
					if(errorMessage.equals(LoginExceptionType.INVALID_USERNAME.toString()) || 
							errorMessage.equals(LoginExceptionType.INVALID_PASSWORD.toString())){
						Window.alert(constants.invalidLogin());
					}
					else if(errorMessage.equals(LoginExceptionType.UNAUTHORIZED_DEVICE.toString())){
						Window.alert(constants.unauthorizedDevice());						
					}
				}
				else{
					Window.alert(errorMessage);
				}
			}
		});
	}

	private void success(SessionProxy session){
		ClientFactoryFactory.getClientFactory().setSID(session.getSid());
		ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new WaitingPlace());
	}
}
