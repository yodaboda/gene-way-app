package com.nutrinfomics.geneway.client.personalIdentifier;

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
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.code.CodePlace;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenPlace;
import com.nutrinfomics.geneway.client.nda.NDAPlace;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.contact.ContactInformationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CredentialsProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.identifier.IdentifierProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.client.util.GeneWayAbstractActivity;

public class PersonalIdentifierActivity extends GeneWayAbstractActivity {
	
	private PersonalIdentifierView personalIdentifierView;
	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		personalIdentifierView = ClientFactoryFactory.getClientFactory().getPersonalIdentifierView();
		
		personalIdentifierView.hideBackButton();
		personalIdentifierView.showAboutButton();
		
		addHandlerRegistration(personalIdentifierView.getUnlockButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				unlock();					
			}
		}));

		panel.setWidget(personalIdentifierView);
	}

	protected void unlock() {
		String identifier = personalIdentifierView.getIdentifier();
		AuthenticationRequest authenticationRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest();

		IdentifierProxy identifierProxy = authenticationRequest.create(IdentifierProxy.class);
		
		identifierProxy.setIdentifierCode(identifier);
		identifierProxy.setUuid(ClientFactoryFactory.getClientFactory().getUUID());
		
		authenticationRequest.unlock(identifierProxy).fire(new GeneWayReceiver<Boolean>() {
			@Override
			public void onFailure(ServerFailure error){
				personalIdentifierView.hideLoader();
				Dialogs.alert(constants.unlockingFailedTitle(), constants.unlockingFailedText(), null);				
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> violations){
				personalIdentifierView.hideLoader();
				personalIdentifierView.constraintViolations(violations);
				super.onConstraintViolation(violations);
			}
			@Override
			public void onSuccess(Boolean bool) {
				personalIdentifierView.hideLoader();
				if(bool){
//					ClientFactoryFactory.getClientFactory().setIdentified(true);
					ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new NDAPlace());									
				}
				else{
					Dialogs.alert(constants.unlockingFailedTitle(), constants.unlockingFailedText(), null);
				}
			}
		});
		personalIdentifierView.displayLoader();
	}
}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */