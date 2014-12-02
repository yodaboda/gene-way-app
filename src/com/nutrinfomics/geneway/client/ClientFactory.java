package com.nutrinfomics.geneway.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.nutrinfomics.geneway.client.about.AboutView;
import com.nutrinfomics.geneway.client.constants.FoodItemTypeConstants;
import com.nutrinfomics.geneway.client.home.HomeView;
import com.nutrinfomics.geneway.client.home.WeeklyCycle;
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.login.LoginView;
import com.nutrinfomics.geneway.client.register.RegisterView;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayRequestFactory;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.waiting.WaitingView;
import com.nutrinfomics.geneway.server.domain.plan.Plan;

public interface ClientFactory {
	
	public void init(Runnable runnable);
	
	public EventBus getEventBus();

	public PlaceController getPlaceController();

	public PhoneGap getPhoneGap();

	public LoginView getLoginView();

	public RegisterView getRegisterView();
	
	public GeneWayConstants getConstants();
	
	public String getModuleURL();

	public HomeView getHomeView();

	public SessionProxy getSession();
	
	public void setSession(SessionProxy session);

	public FoodItemTypeConstants getFoodItemTypeConstants();

	public AboutView getAboutView();

	public WaitingView getWaitingView();

	public void setPlan(PlanProxy plan);
	
	public PlanProxy getPlan();

	public GeneWayRequestFactory getRequestFactory();

	public WeeklyCycle getWeeklyCycle();
}
