package com.nutrinfomics.geneway.client.home.settingsPanel;

/*
 * Copyright (C) 2019 Firas Swidan†
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Request;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.input.checkbox.MCheckBox;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.nutrinfomics.geneway.client.ClientData.PlanPreferencesListener;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanPreferencesProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackTimesProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.EntityBaseRequest;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.client.util.LanguageUtils;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public class SettingsWidgetList extends WidgetList {
	
	private SnackOrderWidgetList snackOrder;
	public SettingsWidgetList(){
		
		super(new SettingsWidgetListAppearance());
		
		getElement().getStyle().setBackgroundColor(Styles.WHITE);
		GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();
		
		final MNumberTextBox hoursBetweenMeals = new SettingsPanelNumberTextBox();
		hoursBetweenMeals.setValue("" + ClientFactoryFactory.getClientFactory().getClientData().getPlanPreferences().getSnackTimes().getTimeBetweenSnacks());
		hoursBetweenMeals.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				hoursBetweenSnacksChanged(event);
			}
		});

		MTextBox textBox = new SettingTabTextBox();
		textBox.setText(constants.timeBetweenMeals());
		FlexPanel hoursPanel = new SettingsTabPanel(textBox, hoursBetweenMeals);
		
		add(hoursPanel);

		MCheckBox smsCheckBox = new SettingsPanelCheckBox();
		smsCheckBox.setValue(ClientFactoryFactory.getClientFactory().getClientData().getPlanPreferences().isSmsAlerts());
		smsCheckBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				smsAlertChanged(event);
			}
		});
		MTextBox textBox2 = new SettingTabTextBox();
		textBox2.setText(constants.smsSnackAlerts());
		FlexPanel smsPanel = new SettingsTabPanel(textBox2, smsCheckBox);
		add(smsPanel);
		

		MCheckBox emailCheckBox = new SettingsPanelCheckBox();
		emailCheckBox.setValue(ClientFactoryFactory.getClientFactory().getClientData().getPlanPreferences().isEmailAlerts());
		emailCheckBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				emailAlertChanged(event);
			}
		});
		MTextBox emailBox = new SettingTabTextBox();
		emailBox.setText(constants.emailSnackAlerts());
		FlexPanel emailPanel = new SettingsTabPanel(emailBox, emailCheckBox);
		add(emailPanel);

		
		
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
	
	protected void emailAlertChanged(ValueChangeEvent<Boolean> event) {
		PlanPreferencesProxy planPreferences = ClientFactoryFactory.getClientFactory().getClientData().getPlanPreferences();
		EntityBaseRequest entityBaseRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().entityBaseRequest();
		
		final PlanPreferencesProxy planPreferencesEdit = entityBaseRequest.edit(planPreferences);
		planPreferencesEdit.setEmailAlerts(event.getValue());

		entityBaseRequest.merge(planPreferencesEdit).fire(new GeneWayReceiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				//update client copy
				ClientFactoryFactory.getClientFactory().getClientData().findPlanPreferences(planPreferencesEdit, new PlanPreferencesListener(){
					public void planPreferences(PlanPreferencesProxy newPlanPreferences) {
					}
				});
			}
		});
	}

	protected void smsAlertChanged(ValueChangeEvent<Boolean> event) {
		PlanPreferencesProxy planPreferences = ClientFactoryFactory.getClientFactory().getClientData().getPlanPreferences();
		EntityBaseRequest entityBaseRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().entityBaseRequest();
		
		final PlanPreferencesProxy planPreferencesEdit = entityBaseRequest.edit(planPreferences);
		planPreferencesEdit.setSmsAlerts(event.getValue());

		entityBaseRequest.merge(planPreferencesEdit).fire(new GeneWayReceiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				//update client copy
				ClientFactoryFactory.getClientFactory().getClientData().findPlanPreferences(planPreferencesEdit, new PlanPreferencesListener(){
					public void planPreferences(PlanPreferencesProxy newPlanPreferences) {
					}
				});
			}
		});
		
	}

	@Override
	public void add(Widget w) {
		super.add(w);
		//hacking WidgetListEntry to force it take customized CSS into consideration.
		setSelectAble(getWidgetCount() - 1, true);
	}

	private void hoursBetweenSnacksChanged(ValueChangeEvent<String> event) {
		PlanPreferencesProxy planPreferences = ClientFactoryFactory.getClientFactory().getClientData().getPlanPreferences();
		EntityBaseRequest entityBaseRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().entityBaseRequest();
		
		final PlanPreferencesProxy planPreferencesEdit = entityBaseRequest.edit(planPreferences);
		double newValue = Double.parseDouble(event.getValue());
		planPreferencesEdit.getSnackTimes().setTimeBetweenSnacks(newValue);

		entityBaseRequest.merge(planPreferencesEdit).fire(new GeneWayReceiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				//update client copy
				ClientFactoryFactory.getClientFactory().getClientData().findPlanPreferences(planPreferencesEdit, new PlanPreferencesListener(){
					public void planPreferences(PlanPreferencesProxy newPlanPreferences) {
					}
				});
			}
		});
	}

}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */