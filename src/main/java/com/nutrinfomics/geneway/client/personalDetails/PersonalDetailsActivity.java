package com.nutrinfomics.geneway.client.personalDetails;

import java.util.Date;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.PersonalDetailsProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.SimpleDateProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.EntityBaseRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.status.StatusPlace;
import com.nutrinfomics.geneway.client.util.DateUtils;
import com.nutrinfomics.geneway.client.util.GeneWayAbstractActivity;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace;

public class PersonalDetailsActivity extends GeneWayAbstractActivity {
	private PersonalDetailsView personalDetailsView;

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);

		personalDetailsView = ClientFactoryFactory.getClientFactory().getPersonalDetailsView();

//		loginView.showAboutButton();
//		loginView.hideBackButton();
		
		addHandlerRegistration(personalDetailsView.getNextButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				next();
			}
		}));

		addHandlerRegistration(personalDetailsView.getDemoButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				demo();
			}
		}));

		panel.setWidget(personalDetailsView);
	}

	protected void demo() {
		PlanRequest planRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest();

		SessionProxy session = ClientFactoryFactory.getClientFactory().createNewSession(planRequest);
		planRequest.setDemo(session).fire(new GeneWayReceiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new WaitingPlace());
			}
		});
		
	}

	protected void next() {
		EntityBaseRequest entityRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().entityBaseRequest();
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().buildSession(entityRequest);
		PersonalDetailsProxy personalDetailsProxy = entityRequest.create(PersonalDetailsProxy.class);
		personalDetailsProxy.setGender(personalDetailsView.getGender());

		SimpleDateProxy birthday = entityRequest.create(SimpleDateProxy.class);
		Date birthdayDate = personalDetailsView.getBirthdayDate();
		birthday.setDay(Integer.parseInt(DateUtils.getDayAsString(birthdayDate)));
		birthday.setMonth(Integer.parseInt(DateUtils.getMonthAsString(birthdayDate)));
		birthday.setYear(Integer.parseInt(DateUtils.getYearAsString(birthdayDate)));
		personalDetailsProxy.setBirthday(birthday);
		
		personalDetailsProxy.setCustomer(sessionProxy.getCustomer());
		sessionProxy.getCustomer().setPersonalDetails(personalDetailsProxy);
		
		entityRequest.mergePersonalDetails(sessionProxy, personalDetailsProxy).fire(new GeneWayReceiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new StatusPlace());				
			}
		});
		
	}
}
