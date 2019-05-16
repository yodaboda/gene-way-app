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

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.nutrinfomics.geneway.client.about.AboutView;
import com.nutrinfomics.geneway.client.code.CodeView;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenView;
import com.nutrinfomics.geneway.client.home.HomeView;
import com.nutrinfomics.geneway.client.ingredients.IngredientsView;
import com.nutrinfomics.geneway.client.localization.GeneWayMessages;
import com.nutrinfomics.geneway.client.login.LoginView;
import com.nutrinfomics.geneway.client.nda.NDAView;
import com.nutrinfomics.geneway.client.payment.PaymentView;
import com.nutrinfomics.geneway.client.personalDetails.PersonalDetailsView;
import com.nutrinfomics.geneway.client.personalIdentifier.PersonalIdentifierView;
import com.nutrinfomics.geneway.client.privacyPolicy.PrivacyPolicyView;
import com.nutrinfomics.geneway.client.register.RegisterView;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayRequestFactory;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.status.StatusView;
import com.nutrinfomics.geneway.client.termsOfService.TermsOfServiceView;
import com.nutrinfomics.geneway.client.waiting.WaitingView;
import com.nutrinfomics.geneway.server.domain.plan.Plan;
import com.nutrinfomics.geneway.server.domain.plan.WeeklyCycle;
import com.nutrinfomics.geneway.shared.constants.FoodItemTypeConstants;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;
import com.nutrinfomics.geneway.shared.constants.MeasurementsConstants;
import com.nutrinfomics.geneway.shared.constants.MiscConstants;

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
	
	public MiscConstants getMiscConstants();
	
	public AboutView getAboutView();

	public WaitingView getWaitingView();

	public GeneWayRequestFactory getRequestFactory();

	public FirstScreenView getFirstScreenView();

	public SessionProxy buildSession(RequestContext requestContext);

	public SessionProxy createNewSession(RequestContext requestContext);

	public String getUUID();

	public void setUsername(String username);

	public void setPassword(String password);
	
	public String getUsername();
	
	public String getPassword();

	public PlaceHistoryMapper getPlaceHistoryMapper();

	public ClientData getClientData();

	public SessionProxy getSession();

	public void setSession(SessionProxy session);

	public IngredientsView getIngredientsView();

	public CodeView getCodeView();

	public void setPhonenumber(String phoneNumber);
	
	public String getPhonenumber();

	public void logout();

	public boolean isLoggedIn();

	public boolean isExistingCustomer();

	public void loggedin(SessionProxy sessionProxy);

	public TermsOfServiceView getTermsOfServiceView();

	public PrivacyPolicyView getPrivacyPolicyView();

	public CustomerProxy buildCustomer(RequestContext requestContext);

	public void setExistingCustomer(boolean existingCustomer);

	public PersonalDetailsView getPersonalDetailsView();

	public StatusView getStatusView();

	public PaymentView getPaymentView();

	public PersonalIdentifierView getPersonalIdentifierView();

	public void setIdentified(boolean bool);
	
	public boolean isIdentified();

	public NDAView getNDAView();

}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */