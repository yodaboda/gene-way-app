package com.nutrinfomics.geneway.client.home.settingsPanel;

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
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanPreferencesProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackTimesProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.EntityBaseRequest;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.client.util.LanguageUtils;

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
				PlanPreferencesProxy planPreferences = ClientFactoryFactory.getClientFactory().getClientData().getPlanPreferences();
				EntityBaseRequest entityBaseRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().entityBaseRequest();
				
				final PlanPreferencesProxy planPreferencesEdit = entityBaseRequest.edit(planPreferences);
				double newValue = Double.parseDouble(event.getValue());
				planPreferencesEdit.getSnackTimes().setTimeBetweenSnacks(newValue);

				Window.alert("edit: new time between snacks value: " + planPreferencesEdit.getSnackTimes().getTimeBetweenSnacks());
				
				entityBaseRequest.merge(planPreferencesEdit).fire(new GeneWayReceiver<Void>() {
					@Override
					public void onSuccess(Void response) {
						//update client copy
						ClientFactoryFactory.getClientFactory().getClientData().findPlanPreferences(planPreferencesEdit, new PlanPreferencesListener(){
							public void planPreferences(PlanPreferencesProxy newPlanPreferences) {
								Window.alert("find: new time between snacks value: " + newPlanPreferences.getSnackTimes().getTimeBetweenSnacks());
							}
						});
//						Request<PlanPreferencesProxy> findRequest = (Request<PlanPreferencesProxy>) ClientFactoryFactory.getClientFactory().getRequestFactory().entityBaseRequest().find(planPreferences.stableId());
//						findRequest.with("snackTimes");
//						findRequest.fire(new GeneWayReceiver<PlanPreferencesProxy>() {
//							@Override
//							public void onSuccess(PlanPreferencesProxy planPreferencesProxy) {
//								Window.alert("find: new time between snacks value: " + planPreferencesProxy.getSnackTimes().getTimeBetweenSnacks());
//								
//							}
//						});
//						ClientFactoryFactory.getClientFactory().getClientData().requestPlanPreferences(new PlanPreferencesListener() {
//							@Override
//							public void planPreferences(PlanPreferencesProxy newPlanPreferences) {
//								Window.alert("request: new time between snacks value: " + newPlanPreferences.getSnackTimes().getTimeBetweenSnacks());
//							}
//						});						
					}
				});
			}
		});

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
