package com.nutrinfomics.geneway.client.waiting;

import java.util.List;
import java.util.Set;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs.AlertCallback;
import com.nutrinfomics.geneway.client.ClientData;
import com.nutrinfomics.geneway.client.ClientData.IngredientsListener;
import com.nutrinfomics.geneway.client.ClientData.NextSnackListener;
import com.nutrinfomics.geneway.client.ClientData.MenuSummaryListener;
import com.nutrinfomics.geneway.client.ClientData.PlanPreferencesListener;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.home.HomePlace;
import com.nutrinfomics.geneway.client.login.AuthenticationException;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.client.login.AuthenticationException.LoginExceptionType;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanPreferencesProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.shared.AccessConstants;
import com.nutrinfomics.geneway.shared.FoodItemType;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public class WaitingActivity extends MGWTAbstractActivity {

	private WaitingView waitingView;
	private int count = 0;
	
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

		Request<SessionProxy> authenticateCustomer = authenticationRequest.authenticateCustomer(sessionProxy.getCustomer());
		authenticateCustomer.with("customer");
		authenticateCustomer.fire(new GeneWayReceiver<SessionProxy>() {
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
		Request<SessionProxy> authenticateSession = authenticationRequest.authenticateSession(sessionProxy);
		authenticateSession.with("customer");
		authenticateSession.fire(new GeneWayReceiver<SessionProxy>() {
			@Override
			public void onSuccess(SessionProxy session){
				success(session);
			}
			@Override
			public void onFailure(ServerFailure error) {
				ClientFactoryFactory.getClientFactory().setSID(null);
//				Cookies.removeCookie(AccessConstants.SID.toString());
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new LoginPlace());
			}
		});

	}
	public void success(SessionProxy session) {

		ClientFactoryFactory.getClientFactory().setSID(session.getSid());
		ClientFactoryFactory.getClientFactory().setSession(session);
		
		getIngredients();
		getMenuSummary();
		getNextSnack();
		getSnackTimes();
		
		Timer timer = new Timer(){
			  @Override
			     public void run() {
					increaseCount();
			  }
		};
		
		timer.schedule(2000);
	}
	
	private void getSnackTimes(){
		ClientFactoryFactory.getClientFactory().getClientData().requestPlanPreferences(new PlanPreferencesListener() {
			@Override
			public void planPreferences(PlanPreferencesProxy planPreferencesProxy) {
				increaseCount();
			}
		});
		
	}
	
	private void getNextSnack() {
		ClientData clientData = ClientFactoryFactory.getClientFactory().getClientData();
		
		clientData.persistCurrentSnack(null, null, new NextSnackListener() {
			@Override
			public void nextSnack(SnackProxy snackProxy, boolean snackForTommorow) {
				increaseCount();
			}
		});
		
	}
	private void getMenuSummary() {
		ClientFactoryFactory.getClientFactory().getClientData().requestMenuSummary(new MenuSummaryListener() {
			@Override
			public void menuSummary(List<String> menuSummary) {
				increaseCount();
			}
		});
	}

	private void getIngredients() {
		ClientFactoryFactory.getClientFactory().getClientData().requestIngredients(new IngredientsListener() {
			@Override
			public void ingredinets(Set<FoodItemType> foodTypes) {
				increaseCount();
			}
		});
	}

	private synchronized void increaseCount(){
		count++;
		if(count == 5){
			  ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new HomePlace());
		}
	}
}
