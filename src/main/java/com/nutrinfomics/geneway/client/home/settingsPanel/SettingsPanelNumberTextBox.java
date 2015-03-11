package com.nutrinfomics.geneway.client.home.settingsPanel;

import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.nutrinfomics.geneway.client.style.Styles;

public class SettingsPanelNumberTextBox extends MNumberTextBox {
	public SettingsPanelNumberTextBox(){
		setMaxLength(3);
		if(LocaleInfo.getCurrentLocale().isRTL()){
			setAlignment(TextAlignment.LEFT);
		}
		else{
			setAlignment(TextAlignment.RIGHT);
		}
		setWidth("30%");
		box.getElement().getStyle().setColor(Styles.BLACK);
	}
}
