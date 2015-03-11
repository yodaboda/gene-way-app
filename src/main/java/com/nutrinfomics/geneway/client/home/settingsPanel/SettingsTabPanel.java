package com.nutrinfomics.geneway.client.home.settingsPanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.nutrinfomics.geneway.client.style.Styles;

public class SettingsTabPanel extends FlexPanel {

	public SettingsTabPanel(IsWidget widget1, IsWidget widget2){
		getElement().getStyle().setBackgroundColor(Styles.WHITE);
		setOrientation(Orientation.HORIZONTAL);
		setWidth("100%");
		getElement().getStyle().setPadding(0, Unit.PX);
		getElement().getStyle().setBorderWidth(0, Unit.PX);
		if(LocaleInfo.getCurrentLocale().isRTL()){
			add(widget2);
			add(widget1);
		}
		else{
			add(widget1);
			add(widget2);
		}
	}
}
