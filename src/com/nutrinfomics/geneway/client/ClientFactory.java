package com.nutrinfomics.geneway.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.nutrinfomics.geneway.client.about.AboutView;
import com.nutrinfomics.geneway.client.constants.FoodItemTypeConstants;
import com.nutrinfomics.geneway.client.constants.MeasurementsConstants;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenView;
import com.nutrinfomics.geneway.client.home.HomeView;
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.localization.GeneWayMessages;
import com.nutrinfomics.geneway.client.login.LoginView;
import com.nutrinfomics.geneway.client.register.RegisterView;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayRequestFactory;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.waiting.WaitingView;
import com.nutrinfomics.geneway.server.domain.plan.Plan;
import com.nutrinfomics.geneway.server.domain.plan.WeeklyCycle;

public interface ClientFactory {
	
	public void init(Runnable runnable);
	
	public EventBus getEventBus();

	public PlaceController getPlaceController();

	public PhoneGap getPhoneGap();

	public LoginView getLoginView();

	public RegisterView getRegisterView();
	
	public GeneWayConstants getConstants();
	
	public GeneWayMessages getMessages();
	
	public String getModuleURL();

	public HomeView getHomeView();

	public String getSID();
	
	public void setSID(String sid);

	public FoodItemTypeConstants getFoodItemTypeConstants();

	public MeasurementsConstants getMeasurementsConstants();
	
	public AboutView getAboutView();

	public WaitingView getWaitingView();

	public GeneWayRequestFactory getRequestFactory();

	public FirstScreenView getFirstScreenView();

	public SessionProxy buildSession(RequestContext requestContext);

	public SessionProxy getNewSession(RequestContext requestContext);

	public String getUUID();

	public void setUsername(String username);

	public void setPassword(String password);
	
	public String getUsername();
	
	public String getPassword();

	public PlaceHistoryMapper getPlaceHistoryMapper();
}
