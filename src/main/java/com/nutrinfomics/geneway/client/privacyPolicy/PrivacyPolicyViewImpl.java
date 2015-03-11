package com.nutrinfomics.geneway.client.privacyPolicy;

import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.nutrinfomics.geneway.client.ScrollingDetailsViewImplementation;

public class PrivacyPolicyViewImpl extends ScrollingDetailsViewImplementation
		implements PrivacyPolicyView {
	public PrivacyPolicyViewImpl(){
		FlexPanel flexPanel = new FlexPanel();
		HTML title = new HTML(constants.privacyPolicyTitle());
		HTML content = new HTML(constants.privacyPolicyText());
		
		flexPanel.add(title);
		flexPanel.add(content);

		setScrollWidget(flexPanel);

	}
}
