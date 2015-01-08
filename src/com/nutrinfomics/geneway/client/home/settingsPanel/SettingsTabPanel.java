package com.nutrinfomics.geneway.client.home.settingsPanel;

import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.nutrinfomics.geneway.client.style.Styles;

public class SettingsTabPanel extends FlexPanel {

	public SettingsTabPanel(){
		getElement().getStyle().setBackgroundColor(Styles.WHITE);
		setOrientation(Orientation.HORIZONTAL);
		setWidth("100%");
	}
}
