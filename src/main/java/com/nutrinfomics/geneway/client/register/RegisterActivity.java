package com.nutrinfomics.geneway.client.register;

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
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CredentialsProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

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
			GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();
			@Override
			public void onTap(TapEvent event) {
				register();					

//				if(registerView.getRepeatPassword().equals(registerView.getPassword())){
//				}
			}
		}));
		
		panel.setWidget(registerView);
	}
	
	protected void register(){
		String phoneNumber = registerView.getPhoneNumber();
		AuthenticationRequest authenticationRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest();
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().buildSession(authenticationRequest);
		CredentialsProxy credentials = authenticationRequest.create(CredentialsProxy.class);
		sessionProxy.getCustomer().setCredentials(credentials);
		credentials.setPassword(registerView.getPassword());
		credentials.setUsername(registerView.getUsername());
		sessionProxy.getCustomer().getDevice().setPhonenumber(phoneNumber);
		
		authenticationRequest.register(sessionProxy).fire(new GeneWayReceiver<SessionProxy>() {
			@Override
			public void onFailure(ServerFailure error){
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> violations){
				Window.alert("constraint violation");
				if(! violations.isEmpty() ){
					Iterator<ConstraintViolation<?>> iterator = violations.iterator();
					String message = "";
					
					while(iterator.hasNext()){
						ConstraintViolation<?> next = iterator.next();
						message += next.getMessageTemplate() + " " + next.getMessage() + "\n";
					}
					
					Window.alert(message);
				}
			}
			@Override
			public void onSuccess(SessionProxy response) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new CodePlace());				
			}
		});
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