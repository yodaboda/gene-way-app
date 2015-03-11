package com.nutrinfomics.geneway.client.termsOfService;

import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.nutrinfomics.geneway.client.ScrollingDetailsViewImplementation;

public class TermsOfServiceViewImpl extends ScrollingDetailsViewImplementation implements
		TermsOfServiceView {
	public TermsOfServiceViewImpl(){		

		FlexPanel flexPanel = new FlexPanel();
		HTML title = new HTML(constants.termsOfServiceTitle());
		HTML content = new HTML(constants.termsOfServiceText());
		
		flexPanel.add(title);
		flexPanel.add(content);

		setScrollWidget(flexPanel);

	}
}
