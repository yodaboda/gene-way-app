package com.nutrinfomics.geneway.client.payment;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.util.GeneWayAbstractActivity;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace;

public class PaymentActivity extends GeneWayAbstractActivity {
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);

		PaymentView paymentView = ClientFactoryFactory.getClientFactory().getPaymentView();

		addHandlerRegistration(paymentView.getDemoButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				demo();
			}
		}));
		
//		loginView.showAboutButton();
//		loginView.hideBackButton();
		
		panel.setWidget(paymentView);
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
}
