package com.nutrinfomics.geneway.client.home.settingsPanel;

import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.input.checkbox.MCheckBox;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.client.util.LanguageUtils;

public class SettingsWidgetList extends WidgetList {
	
	private SnackOrderWidgetList snackOrder;
	public SettingsWidgetList(){
		
		super(new SettingsWidgetListAppearance());
		
		getElement().getStyle().setBackgroundColor(Styles.WHITE);
		GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();
		
		MNumberTextBox hoursBetweenMeals = new SettingsPanelNumberTextBox();
		hoursBetweenMeals.setValue("1.5");
		MTextBox textBox = new SettingTabTextBox();
		textBox.setText(constants.timeBetweenMeals());
		FlexPanel hoursPanel = new SettingsTabPanel(textBox, hoursBetweenMeals);
		
		add(hoursPanel);

		MCheckBox smsCheckBox = new SettingsPanelCheckBox();
		MTextBox textBox2 = new SettingTabTextBox();
		textBox2.setText(constants.smsSnackAlerts());
		FlexPanel smsPanel = new SettingsTabPanel(textBox2, smsCheckBox);
		add(smsPanel);
		
		snackOrder = new SnackOrderWidgetList();
		add(snackOrder);
		
		MListBox languageBox = new MListBox();
		LanguageUtils.initializeLanguageBox(languageBox);
		languageBox.setWidth("50%");
		MTextBox languageTextBox = new SettingTabTextBox();
		languageTextBox.setText(constants.language());
		FlexPanel languagePanel = new SettingsTabPanel(languageTextBox, languageBox);
		add(languagePanel);
	}
	
	@Override
	public void add(Widget w) {
		super.add(w);
		//hacking WidgetListEntry to force it take customized CSS into consideration.
		setSelectAble(getWidgetCount() - 1, true);
	}

}
