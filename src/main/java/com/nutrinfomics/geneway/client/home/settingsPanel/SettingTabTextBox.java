package com.nutrinfomics.geneway.client.home.settingsPanel;

import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.nutrinfomics.geneway.client.style.Styles;

public class SettingTabTextBox extends MTextBox {
	public SettingTabTextBox(){
		this.box.getElement().getStyle().setColor(Styles.BLACK);
		setWidth("50%");
	}
}
