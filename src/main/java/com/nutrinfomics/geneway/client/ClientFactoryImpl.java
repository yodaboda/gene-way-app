package com.nutrinfomics.geneway.client;

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

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.nutrinfomics.geneway.client.about.AboutView;
import com.nutrinfomics.geneway.client.about.AboutViewImpl;
import com.nutrinfomics.geneway.client.code.CodeView;
import com.nutrinfomics.geneway.client.code.CodeViewImpl;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenPlace;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenView;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenViewImpl;
import com.nutrinfomics.geneway.client.home.HomeView;
import com.nutrinfomics.geneway.client.home.HomeViewImpl;
import com.nutrinfomics.geneway.client.ingredients.IngredientsView;
import com.nutrinfomics.geneway.client.ingredients.IngredientsViewImpl;
import com.nutrinfomics.geneway.client.localization.GeneWayMessages;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.client.login.LoginView;
import com.nutrinfomics.geneway.client.login.LoginViewImpl;
import com.nutrinfomics.geneway.client.nda.NDAView;
import com.nutrinfomics.geneway.client.nda.NDAViewImpl;
import com.nutrinfomics.geneway.client.payment.PaymentActivity;
import com.nutrinfomics.geneway.client.payment.PaymentView;
import com.nutrinfomics.geneway.client.payment.PaymentViewImpl;
import com.nutrinfomics.geneway.client.personalDetails.PersonalDetailsView;
import com.nutrinfomics.geneway.client.personalDetails.PersonalDetailsViewImpl;
import com.nutrinfomics.geneway.client.personalIdentifier.PersonalIdentifierView;
import com.nutrinfomics.geneway.client.personalIdentifier.PersonalIdentifierViewImpl;
import com.nutrinfomics.geneway.client.privacyPolicy.PrivacyPolicyView;
import com.nutrinfomics.geneway.client.privacyPolicy.PrivacyPolicyViewImpl;
import com.nutrinfomics.geneway.client.register.RegisterView;
import com.nutrinfomics.geneway.client.register.RegisterViewImpl;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayRequestFactory;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayRequestTransport;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.DeviceProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.status.StatusView;
import com.nutrinfomics.geneway.client.status.StatusViewImpl;
import com.nutrinfomics.geneway.client.termsOfService.TermsOfServiceView;
import com.nutrinfomics.geneway.client.termsOfService.TermsOfServiceViewImpl;
import com.nutrinfomics.geneway.client.waiting.WaitingView;
import com.nutrinfomics.geneway.client.waiting.WaitingViewImpl;
import com.nutrinfomics.geneway.server.domain.plan.WeeklyCycle;
import com.nutrinfomics.geneway.shared.AccessConstants;
import com.nutrinfomics.geneway.shared.constants.FoodItemTypeConstants;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;
import com.nutrinfomics.geneway.shared.constants.MeasurementsConstants;
import com.nutrinfomics.geneway.shared.constants.MiscConstants;

public class ClientFactoryImpl implements ClientFactory {

	static private SimpleEventBus eventBus;
	static private PlaceController placeController;
	static private PhoneGap phoneGap;
	static private LoginView loginView;
	static private RegisterView registerView;
	static private GeneWayConstants constants;
	static private GeneWayMessages messages;
	static private HomeView homeView;
	static private WaitingView waitingView;
	static private FoodItemTypeConstants foodItemTypeConstants;
	static private MeasurementsConstants measurementsConstants;
	static private MiscConstants miscConstants;
	static private AboutView aboutView;
	static private GeneWayRequestFactory requestFactory;
	static private FirstScreenView firstScreenView;
	static private IngredientsView ingredientsView;
	static private TermsOfServiceView termsOfServiceView;
	static private PrivacyPolicyView privacyPolicyView;
	static private CodeView codeView;
	static private String username;
	static private String password;
	static private PlaceHistoryMapper placeHistoryMapper;
	static private ClientData clientData = new ClientData();
	static private SessionProxy session;
	static private boolean existingCustomer;
	static private PersonalDetailsView personalDetailsView;
	static private StatusView statusView;
	static private PaymentView paymentView;
	static private PersonalIdentifierView personalIdentifierView;
	static private NDAView ndaView;
	
	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public PhoneGap getPhoneGap() {
		return phoneGap;
	}

	@Override
	public LoginView getLoginView() {
		if(loginView == null){
			loginView = new LoginViewImpl();
		}
		return loginView;
	}

	@Override
	public RegisterView getRegisterView() {
		if(registerView == null){
			registerView = new RegisterViewImpl();
		}
		return registerView;
	}

	@Override
	public FirstScreenView getFirstScreenView(){
		if(firstScreenView == null){
			firstScreenView = new FirstScreenViewImpl();
		}
		return firstScreenView;
	}
	
	@Override
	public GeneWayConstants getConstants() {
		if(constants == null){
			constants = GWT.create(GeneWayConstants.class);
		}
		return constants;
	}

	@Override
	public GeneWayMessages getMessages(){
		if( messages == null){
			messages = GWT.create(GeneWayMessages.class);
		}
		return messages;
	}
	
	@Override
	public String getModuleURL() {
//		return "http://127.0.0.1:8080/gene-way/gene_way/";

//		return "http://192.168.14.115:8080/gene-way/gene_way/";
		return "https://84.108.13.21:8443/gene-way/gene_way/";
	}

	@Override
	public HomeView getHomeView() {
		if(homeView == null){
			homeView = new HomeViewImpl();
		}
		return homeView;
	}

	@Override
	public void init(final Runnable runnable) {
		MGWT.applySettings(MGWTSettings.getAppSetting());

//		return "http://127.0.0.1:8080/gene-way/gene_way/";
//		return "http://192.168.14.115:8080/gene-way/gene_way/";
//		return "https://84.108.13.21:8443/gene-way/gene_way/";
		
		eventBus = new SimpleEventBus();
		requestFactory = GWT.create(GeneWayRequestFactory.class);
//		DefaultRequestTransport requestTransport = new DefaultRequestTransport();
//		requestTransport.setRequestUrl("http://127.0.0.1:8080/gene-way/gene_way/");
		requestFactory.initialize(eventBus, new GeneWayRequestTransport());
		
		placeController = new PlaceController(eventBus);
	    
		phoneGap = GWT.create(PhoneGap.class);
	    phoneGap.addHandler(new PhoneGapAvailableHandler() {
	        @Override
	        public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
	          runnable.run();
	        }
	      });
	    phoneGap.addHandler(new PhoneGapTimeoutHandler() {
	    	@Override
	        public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
	          Window.alert("can not load phonegap");
	        }
	      });
	    phoneGap.initializePhoneGap();
	}

	@Override
	public GeneWayRequestFactory getRequestFactory(){
		return requestFactory;
	}

	@Override 
	public CustomerProxy buildCustomer(RequestContext requestContext){
		CustomerProxy customerProxy = requestContext.create(CustomerProxy.class);
		DeviceProxy deviceProxy = requestContext.create(DeviceProxy.class);
			
		deviceProxy.setUuid(getUUID());

		deviceProxy.setCustomer(customerProxy);
		customerProxy.setDevice(deviceProxy);
		
		customerProxy.setNickName("niiiiiicckkkk");

		return customerProxy;
	}
	
	@Override
	public SessionProxy buildSession(RequestContext requestContext) {
		SessionProxy sessionProxy = createNewSession(requestContext);
		
		CustomerProxy customerProxy = buildCustomer(requestContext);
			
		sessionProxy.setCustomer(customerProxy);
		customerProxy.setSession(sessionProxy);
		
		return sessionProxy;
	}

	@Override
	public String getSID() {
		return Cookies.getCookie(AccessConstants.SID.toString());
	}

	@Override
	public void setSID(String sid) {
		if(sid == null){
			Cookies.removeCookie(AccessConstants.SID.toString());
			setSession(null);
		}
		else{
			Date expires = new Date(System.currentTimeMillis() + Long.MAX_VALUE); //Indefinite duration
			Cookies.setCookie(AccessConstants.SID.toString(), sid, expires, null, "/", false);
		}
	}
	
	@Override
	public String getPhonenumber(){
		return Cookies.getCookie(AccessConstants.PHONENUMBER.toString());
	}
	
	@Override
	public void setPhonenumber(String phonenumber){
		if(phonenumber == null){
			Cookies.removeCookie(AccessConstants.PHONENUMBER.toString());
		}
		else{
			Date expires = new Date(System.currentTimeMillis() + Long.MAX_VALUE); //Indefinite duration
			Cookies.setCookie(AccessConstants.PHONENUMBER.toString(), phonenumber, expires, null, "/", false);
		}		
	}

	@Override
	public FoodItemTypeConstants getFoodItemTypeConstants() {
		if(foodItemTypeConstants == null){
			foodItemTypeConstants = GWT.create(FoodItemTypeConstants.class);
		}
		return foodItemTypeConstants;
	}

	@Override
	public MeasurementsConstants getMeasurementsConstants(){
		if(measurementsConstants == null){
			measurementsConstants = GWT.create(MeasurementsConstants.class);
		}
		return measurementsConstants;
	}
	
	@Override
	public AboutView getAboutView() {
		if(aboutView == null){
			aboutView = new AboutViewImpl();
		}
		return aboutView;
	}

	@Override
	public WaitingView getWaitingView() {
		if(waitingView == null){
			waitingView = new WaitingViewImpl();
		}
		return waitingView;
	}

	@Override
	public SessionProxy createNewSession(RequestContext requestContext) {
		SessionProxy sessionProxy = requestContext.create(SessionProxy.class);
		String sid = getSID();
		if(sid != null) sessionProxy.setSid(sid);
		return sessionProxy;
	}

	@Override
	public String getUUID() {
		return getPhoneGap().getDevice().getUuid();
	}

	@Override
	public void setUsername(String username) {
		ClientFactoryImpl.username = username;
		
	}

	@Override
	public void setPassword(String password) {
		ClientFactoryImpl.password = password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public PlaceHistoryMapper getPlaceHistoryMapper() {
		if(placeHistoryMapper == null){
			placeHistoryMapper = GWT.create(GeneWayPlaceHistoryMapper.class);
		}
		return placeHistoryMapper;
	}

	@Override
	public ClientData getClientData() {
		return clientData;
	}

	@Override
	public MiscConstants getMiscConstants() {
		if(miscConstants == null){
			miscConstants = GWT.create(MiscConstants.class);
		}
		return miscConstants;
	}

	@Override
	public SessionProxy getSession() {
		return session;
	}
	@Override
	public void setSession(SessionProxy session){
		ClientFactoryImpl.session = session;
	}

	@Override
	public IngredientsView getIngredientsView() {
		if(ingredientsView == null){
			ingredientsView = new IngredientsViewImpl();
		}
		return ingredientsView;
	}

	@Override
	public CodeView getCodeView() {
		if(codeView == null){
			codeView = new CodeViewImpl();
		}
		return codeView;
	}

	@Override
	public void logout() {
		setSID(null);
		ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new LoginPlace());
	}

	@Override
	public boolean isLoggedIn() {
		return getSID() != null;
	}

	@Override
	public boolean isExistingCustomer() {
		String exCustomer = Cookies.getCookie(AccessConstants.EXISTING_USER.toString());
		if(exCustomer == null) return false;
		return Boolean.parseBoolean(exCustomer);
	}
	
	@Override
	public void setExistingCustomer(boolean existingCustomer){
		Date expires = new Date(System.currentTimeMillis() + Long.MAX_VALUE); //Indefinite duration
		Cookies.setCookie(AccessConstants.EXISTING_USER.toString(), existingCustomer + "", expires, null, "/", false);
	}

	@Override
	public void loggedin(SessionProxy sessionProxy) {
		setSession(sessionProxy);
		setSID(sessionProxy.getSid());
	}

	@Override
	public TermsOfServiceView getTermsOfServiceView() {
		if(termsOfServiceView == null){
			termsOfServiceView = new TermsOfServiceViewImpl();
		}
		return termsOfServiceView;
	}

	@Override
	public PrivacyPolicyView getPrivacyPolicyView() {
		if(privacyPolicyView == null){
			privacyPolicyView = new PrivacyPolicyViewImpl();
		}
		return privacyPolicyView;
	}
	
	@Override
	public PersonalDetailsView getPersonalDetailsView(){
		if(personalDetailsView == null){
			personalDetailsView = new PersonalDetailsViewImpl();
		}
		return personalDetailsView;		
	}

	@Override
	public StatusView getStatusView() {
		if(statusView == null){
			statusView = new StatusViewImpl();
		}
		return statusView;
	}

	@Override
	public PaymentView getPaymentView() {
		if(paymentView == null){
			paymentView = new PaymentViewImpl();
		}
		return paymentView;
	}

	@Override
	public PersonalIdentifierView getPersonalIdentifierView() {
		if(personalIdentifierView == null){
			personalIdentifierView = new PersonalIdentifierViewImpl();
		}
		return personalIdentifierView;
	}

	@Override
	public void setIdentified(boolean bool) {
		Date expires = new Date(System.currentTimeMillis() + Long.MAX_VALUE); //Indefinite duration
		Cookies.setCookie(AccessConstants.IDENTIFIED.toString(), bool + "", expires, null, "/", false);
		
	}

	@Override
	public boolean isIdentified() {
		String identified = Cookies.getCookie(AccessConstants.IDENTIFIED.toString());
		if(identified == null) return false;
		return Boolean.parseBoolean(identified);
	}

	@Override
	public NDAView getNDAView() {
		if(ndaView == null){
			ndaView = new NDAViewImpl();
		}
		return ndaView;
	}
}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */