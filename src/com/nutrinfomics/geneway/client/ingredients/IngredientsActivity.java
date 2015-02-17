package com.nutrinfomics.geneway.client.ingredients;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;

public class IngredientsActivity extends MGWTAbstractActivity {


	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		IngredientsView ingredientsView = ClientFactoryFactory.getClientFactory().getIngredientsView();
		panel.setWidget(ingredientsView);
	}

}
