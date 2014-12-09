package com.nutrinfomics.geneway.client.firstScreen;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.nutrinfomics.geneway.client.DetailsView;

public interface FirstScreenView extends DetailsView {

	public HasTapHandlers getNewAccountButton();

	public HasTapHandlers getExistingAccountButton();

}
