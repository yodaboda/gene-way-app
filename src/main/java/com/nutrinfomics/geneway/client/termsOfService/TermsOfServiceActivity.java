package com.nutrinfomics.geneway.client.termsOfService;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public class TermsOfServiceActivity extends MGWTAbstractActivity {
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		TermsOfServiceView termsOfServiceView = ClientFactoryFactory.getClientFactory().getTermsOfServiceView();		
		panel.setWidget(termsOfServiceView);
	}
}
