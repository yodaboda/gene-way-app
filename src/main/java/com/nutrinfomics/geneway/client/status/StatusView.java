package com.nutrinfomics.geneway.client.status;

import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.nutrinfomics.geneway.client.DetailsView;

public interface StatusView extends DetailsView {

	public Button getNextButton();

	public boolean isConstipated();

}
