package com.nutrinfomics.geneway.client;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.gwt.client.DefaultRequestTransport;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
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
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenView;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenViewImpl;
import com.nutrinfomics.geneway.client.home.HomeView;
import com.nutrinfomics.geneway.client.home.HomeViewImpl;
import com.nutrinfomics.geneway.client.ingredients.IngredientsView;
import com.nutrinfomics.geneway.client.ingredients.IngredientsViewImpl;
import com.nutrinfomics.geneway.client.localization.GeneWayMessages;
import com.nutrinfomics.geneway.client.login.LoginView;
import com.nutrinfomics.geneway.client.login.LoginViewImpl;
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
	static private String username;
	static private String password;
	static private PlaceHistoryMapper placeHistoryMapper;
	static private ClientData clientData = new ClientData();
	static private SessionProxy session;
	
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
	public SessionProxy buildSession(RequestContext requestContext) {
		SessionProxy sessionProxy = createNewSession(requestContext);
		
		CustomerProxy customerProxy = requestContext.create(CustomerProxy.class);
		DeviceProxy deviceProxy = requestContext.create(DeviceProxy.class);
			
		deviceProxy.setUuid(getUUID());
		deviceProxy.setCustomer(customerProxy);
		customerProxy.setDevice(deviceProxy);
			
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
}
