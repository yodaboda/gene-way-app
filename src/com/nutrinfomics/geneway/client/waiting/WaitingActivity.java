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
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;

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
				  ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest().findPlan(ClientFactoryFactory.getClientFactory().getSession()).fire(new GeneWayReceiver<PlanProxy>() {
					  @Override
					  public void onFailure(ServerFailure error) {
						  Window.alert(error.getMessage());
					  }
					  @Override
					  public void onSuccess(PlanProxy plan) {
						  ClientFactoryFactory.getClientFactory().setPlan(plan);
						  ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new HomePlace());
					  }
				  });
			  }
		};
		
		timer.schedule(2000);

	}	
	
}
