package com.nutrinfomics.geneway.client.nda;

import com.google.gwt.user.client.ui.CheckBox;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.nutrinfomics.geneway.client.DetailsView;

public interface NDAView extends DetailsView {
	public HasTapHandlers getConfirmButton();

	public CheckBox getCheckBox();
}
