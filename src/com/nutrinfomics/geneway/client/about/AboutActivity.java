
package com.nutrinfomics.geneway.client.about;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;

public class AboutActivity extends MGWTAbstractActivity {


	public AboutActivity() {
	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);

		AboutView aboutView = ClientFactoryFactory.getClientFactory().getAboutView();
		aboutView.hideAboutButton();
		aboutView.showBackButton();
//		addHandlerRegistration(aboutView.getBackbutton().addTapHandler(new TapHandler() {
//
//			@Override
//			public void onTap(TapEvent event) {
//				ActionEvent.fire(eventBus, ActionNames.BACK);
//
//			}
//		}));

		panel.setWidget(aboutView);

	}

}
