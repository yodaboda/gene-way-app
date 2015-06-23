package com.nutrinfomics.geneway.client.firstScreen;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.nutrinfomics.geneway.client.DetailsView;

public interface FirstScreenView extends DetailsView {

	public HasTapHandlers getNewAccountButton();

	public HasTapHandlers getExistingAccountButton();

	public HasClickHandlers getNewAccountAnchor();

	public String getPassword();

	public HasTapHandlers getAccountAnchorButton();

}
