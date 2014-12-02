package com.nutrinfomics.geneway.client.register;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.nutrinfomics.geneway.client.DetailsView;

public interface RegisterView extends DetailsView {
	public HasTapHandlers getRegisterButton();

	public String getEmail();

	public String getUsername();

	public String getFirstName();

	public String getLastName();

	public String getPassword();
}
