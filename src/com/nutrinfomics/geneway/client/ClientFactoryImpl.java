package com.nutrinfomics.geneway.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.gwt.client.DefaultRequestTransport;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.nutrinfomics.geneway.client.about.AboutView;
import com.nutrinfomics.geneway.client.about.AboutViewImpl;
import com.nutrinfomics.geneway.client.constants.FoodItemTypeConstants;
import com.nutrinfomics.geneway.client.home.HomeView;
import com.nutrinfomics.geneway.client.home.HomeViewImpl;
import com.nutrinfomics.geneway.client.home.WeeklyCycle;
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.login.LoginView;
import com.nutrinfomics.geneway.client.login.LoginViewImpl;
import com.nutrinfomics.geneway.client.register.RegisterView;
import com.nutrinfomics.geneway.client.register.RegisterViewImpl;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayRequestFactory;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.DeviceProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.waiting.WaitingView;
import com.nutrinfomics.geneway.client.waiting.WaitingViewImpl;

public class ClientFactoryImpl implements ClientFactory {

	static private SimpleEventBus eventBus;
	static private PlaceController placeController;
	static private PhoneGap phoneGap;
	static private LoginView loginView;
	static private RegisterView registerView;
	static private GeneWayConstants constants;
	static private HomeView homeView;
	static private WaitingView waitingView;
	static private SessionProxy session;
	private FoodItemTypeConstants foodItemTypeConstants;
	static private AboutView aboutView;
	static private PlanProxy plan;
	static private GeneWayRequestFactory requestFactory;
	static private WeeklyCycle weeklyCycle;
	
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
	public GeneWayConstants getConstants() {
		if(constants == null){
			constants = GWT.create(GeneWayConstants.class);
		}
		return constants;
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
		requestFactory.initialize(eventBus);
		
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
	public SessionProxy getSession() {
		return session;
	}

	@Override
	public void setSession(SessionProxy session) {
		ClientFactoryImpl.session = session;
	}

	@Override
	public FoodItemTypeConstants getFoodItemTypeConstants() {
		if(foodItemTypeConstants == null){
			foodItemTypeConstants = GWT.create(FoodItemTypeConstants.class);
		}
		return foodItemTypeConstants;
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
	public void setPlan(PlanProxy plan) {
		ClientFactoryImpl.plan = plan;
	}

	@Override
	public PlanProxy getPlan() {
		return plan;
	}

	@Override
	public WeeklyCycle getWeeklyCycle() {
		if(weeklyCycle == null) weeklyCycle = new WeeklyCycle();
		return weeklyCycle;
	}
	
}
