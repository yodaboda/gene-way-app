package com.nutrinfomics.geneway.client.home.settingsPanel;

import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.input.checkbox.MCheckBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.home.SnackOrderWidgetList;
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.style.Styles;

public class SettingsWidget extends WidgetList {

	
	private SnackOrderWidgetList snackOrder;
	public SettingsWidget(){
		
		getElement().getStyle().setBackgroundColor(Styles.WHITE);
		setHeight("100%");
		GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();
		
		MNumberTextBox hoursBetweenMeals = new SettingsPanelNumberTextBox();
		hoursBetweenMeals.setValue("1.5");
		FlexPanel panel = new SettingsTabPanel();
		MTextBox textBox = new SettingTabTextBox();
		textBox.setText(constants.timeBetweenMeals());
		panel.add(textBox);
		
		panel.add(hoursBetweenMeals);
		
		
		add(panel);
		
		
		FlexPanel panel2 = new SettingsTabPanel();
		MCheckBox mCheckBox = new MCheckBox();
		
		MTextBox textBox2 = new SettingTabTextBox();
		textBox2.setText(constants.smsSnackAlerts());
		
		panel2.add(textBox2);
		panel2.add(mCheckBox);
		
		
		add(panel2);
		
		snackOrder = new SnackOrderWidgetList();
		
		add(snackOrder);
	}
}
