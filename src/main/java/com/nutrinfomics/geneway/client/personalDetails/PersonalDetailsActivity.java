package com.nutrinfomics.geneway.client.personalDetails;

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

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */