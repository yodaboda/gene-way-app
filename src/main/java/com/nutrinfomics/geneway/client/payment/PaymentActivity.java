package com.nutrinfomics.geneway.client.payment;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.util.GeneWayAbstractActivity;

public class PaymentActivity extends GeneWayAbstractActivity {
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);

		PaymentView paymentView = ClientFactoryFactory.getClientFactory().getPaymentView();

//		loginView.showAboutButton();
//		loginView.hideBackButton();
		
		panel.setWidget(paymentView);
	}
}
