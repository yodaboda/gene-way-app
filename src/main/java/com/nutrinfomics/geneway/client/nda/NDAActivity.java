package com.nutrinfomics.geneway.client.nda;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs.AlertCallback;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenPlace;
import com.nutrinfomics.geneway.client.privacyPolicy.PrivacyPolicyView;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.identifier.IdentifierProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.AuthenticationRequest;
import com.nutrinfomics.geneway.client.util.GeneWayAbstractActivity;

public class NDAActivity extends GeneWayAbstractActivity {
	private NDAView ndaView;
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		ndaView = ClientFactoryFactory.getClientFactory().getNDAView();
		
		addHandlerRegistration(ndaView.getConfirmButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				if(! ndaView.getCheckBox().getValue()) return;
				confirm();					
			}
		}));
		
		panel.setWidget(ndaView);
	}
	private void confirm(){
		AuthenticationRequest authenticationRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().authenticationRequest();
		
		authenticationRequest.confirmValuationTermsOfService(ClientFactoryFactory.getClientFactory().getUUID()).fire(new GeneWayReceiver<String>() {
			@Override
			public void onFailure(ServerFailure error){
				ndaView.hideLoader();
				Dialogs.alert("Server failure", "this should not happen", null);				
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> violations){
				ndaView.hideLoader();
				Dialogs.alert("Constraints violation", "this should not happen ", null);				
			}
			@Override
			public void onSuccess(String ip) {
				ndaView.hideLoader();
				Dialogs.alert("User confirmed", "IP: " + ip + "\nUUID: " + ClientFactoryFactory.getClientFactory().getUUID()
												+ "\nTime: " + DateTimeFormat.getFormat(PredefinedFormat.DATE_LONG).format(new Date()), 
							  new AlertCallback() {
								
								@Override
								public void onButtonPressed() {
									ClientFactoryFactory.getClientFactory().setIdentified(true);				
									ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new FirstScreenPlace());									
								}
							});


			}
		});
		ndaView.displayLoader();

	}
}
