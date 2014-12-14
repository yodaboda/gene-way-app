package com.nutrinfomics.geneway.client.home;

import com.google.gwt.user.client.ui.IsWidget;
import com.nutrinfomics.geneway.client.DetailsView;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.server.domain.plan.Plan;

public interface HomeView extends DetailsView {

	void start();

	void setSnack(SnackProxy snackProxy);

}
