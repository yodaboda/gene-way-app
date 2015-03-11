package com.nutrinfomics.geneway.client.code;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.nutrinfomics.geneway.client.DetailsView;

public interface CodeView extends DetailsView {
	public HasTapHandlers getVerifyButton();

	public String getEnteredCode();

	public HasTapHandlers getResendCodeButton();
}
