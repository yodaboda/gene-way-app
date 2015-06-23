package com.nutrinfomics.geneway.client.privacyPolicy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.util.GeneWayAbstractActivity;

public class PrivacyPolicyActivity extends GeneWayAbstractActivity {
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		PrivacyPolicyView privacyPolicyView = ClientFactoryFactory.getClientFactory().getPrivacyPolicyView();
		
		panel.setWidget(privacyPolicyView);
	}
}
