package com.nutrinfomics.geneway.client.login;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.nutrinfomics.geneway.client.DetailsView;

public interface LoginView extends DetailsView{
	public HasTapHandlers getLoginButton();
	public String getUsername();
	public String getPassword();
}
