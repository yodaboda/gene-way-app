package com.nutrinfomics.geneway.client.personalIdentifier;

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
