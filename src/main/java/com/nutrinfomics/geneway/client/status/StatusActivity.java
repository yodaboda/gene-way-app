package com.nutrinfomics.geneway.client.status;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.payment.PaymentPlace;
import com.nutrinfomics.geneway.client.util.GeneWayAbstractActivity;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace;

public class StatusActivity extends GeneWayAbstractActivity {
	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);

		StatusView statusView = ClientFactoryFactory.getClientFactory().getStatusView();
		panel.setWidget(statusView);
		
		
		addHandlerRegistration(statusView.getNextButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new PaymentPlace());
			}
		}));
	}	

}
