package com.nutrinfomics.geneway.client.login;

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
import com.nutrinfomics.geneway.client.ClientFactoryImpl;
import com.nutrinfomics.geneway.client.CookieConstants;
import com.nutrinfomics.geneway.client.home.HomePlace;
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.login.AuthenticationException.LoginExceptionType;
import com.nutrinfomics.geneway.client.register.RegisterPlace;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace;
import com.nutrinfomics.geneway.server.domain.customer.Customer;
import com.nutrinfomics.geneway.server.domain.device.Device;
import com.nutrinfomics.geneway.server.domain.device.Session;

public class LoginActivity extends MGWTAbstractActivity {

	private static final String SID = CookieConstants.SID.toString();
	private LoginView loginView;
//	private LoginServiceAsync loginService = GWT.create(LoginService.class);
	private CustomerProxy customer;
	private AcceptsOneWidget panel;
	
	public LoginActivity(){
		super();
//		PhonegapUtil.prepareService((ServiceDefTarget) loginService, ClientFactoryFactory.getClientFactory().getModuleURL(), "login");
	}
	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		
		this.panel = panel;
		String sid = Cookies.getCookie(SID);
		
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
		
		addHandlerRegistration(loginView.getRegisterButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new RegisterPlace());
			}
		}));
		
		panel.setWidget(loginView);
	}

	private void loginSID(String sid) {

//		AsyncCallback<Customer> callback = new AsyncCallback<Customer>() {
//			public void onFailure(Throwable caught) {
//				Cookies.removeCookie(SID);
//				showLoginView(panel);
//			}
//
//			public void onSuccess(Customer customer) {
//				LoginActivity.this.customer = customer;
//				success();
//			}
//		};

//		loginService.login(session, callback);
//		ClientFactoryFactory.getClientFactory().getRequestFactory().getRequestTransport();
		
//		ClientFactoryFactory.getClientFactory().getRequestFactory().customerRequest().create(CustomerProxy.class);
		
		String uuid = ClientFactoryFactory.getClientFactory().getPhoneGap().getDevice().getUuid();
		ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest().authenticateSession(sid, uuid).fire(new GeneWayReceiver<CustomerProxy>() {
			@Override
			public void onSuccess(CustomerProxy customer){
				LoginActivity.this.customer = customer;
				success();
			}
			@Override
			public void onFailure(ServerFailure error) {
				Cookies.removeCookie(SID);
				showLoginView(panel);
			}

		});
		
	}

	
	protected void login() {

//		AsyncCallback<Customer> callback = new AsyncCallback<Customer>() {
//			public void onFailure(Throwable caught) {
//			}
//
//			public void onSuccess(Customer customer) {
//			}
//		};
		String uuid = ClientFactoryFactory.getClientFactory().getPhoneGap().getDevice().getUuid();
		ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest().authenticateCustomer(loginView.getUsername(), loginView.getPassword(), uuid).fire(new GeneWayReceiver<CustomerProxy>() {
			@Override
			public void onSuccess(CustomerProxy customer){
//				final long DURATION = 1000 * 60 * 60 * 24 * 14; //duration remembering login. 2 weeks in this example.
				Date expires = new Date(System.currentTimeMillis() + Long.MAX_VALUE); //Indefinite duration
//				Cookies.setCookie(SID, customer.getSession().getSid(), expires, null, "/", false);
				LoginActivity.this.customer = customer;
				success();
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

		//		loginService.login(customer, callback);
	}

	private void success(){
		ClientFactoryFactory.getClientFactory().setSession(customer.getSession());
		ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new WaitingPlace());
	}
		
}
