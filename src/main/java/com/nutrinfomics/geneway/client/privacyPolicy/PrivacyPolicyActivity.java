package com.nutrinfomics.geneway.client.privacyPolicy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;

public class PrivacyPolicyActivity extends MGWTAbstractActivity {
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		PrivacyPolicyView privacyPolicyView = ClientFactoryFactory.getClientFactory().getPrivacyPolicyView();
		
		panel.setWidget(privacyPolicyView);
	}
}
