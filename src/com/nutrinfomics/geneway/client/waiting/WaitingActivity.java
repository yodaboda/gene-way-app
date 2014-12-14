package com.nutrinfomics.geneway.client.waiting;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.home.HomePlace;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.shared.SnackStatus;

public class WaitingActivity extends MGWTAbstractActivity {

	private WaitingView waitingView;

	
	public WaitingActivity(){
	}
	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		waitingView = ClientFactoryFactory.getClientFactory().getWaitingView();
		panel.setWidget(waitingView);
		
		Timer timer = new Timer(){
			  @Override
			     public void run() {
				  ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new HomePlace());				  
			  }
		};
		
		timer.schedule(2000);

	}	
	
}
