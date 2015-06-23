package com.nutrinfomics.geneway.client.home;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.nutrinfomics.geneway.client.BlinkImageButton;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.client.register.RegisterPlace;
import com.nutrinfomics.geneway.client.util.GeneWayAbstractActivity;

public class HomeActivity extends GeneWayAbstractActivity {
	private HomeView homeView;
	
	public HomeActivity(){
		super();
	}
	
	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		homeView = ClientFactoryFactory.getClientFactory().getHomeView();
		addHandlerRegistration(homeView.getStatusButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				if(homeView.getStatusButton() instanceof BlinkImageButton){
					Dialogs.alert(constants.imbalanceTitle(), constants.imbalanceText(), null);					
				}
				else{
					Dialogs.alert(constants.goodWorkTitle(), constants.goodWorkText(), null);
				}
			}
		}));
		panel.setWidget(homeView);
	}
}
