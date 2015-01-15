package com.nutrinfomics.geneway.client.register;

import com.google.gwt.i18n.shared.FirstStrongDirectionEstimator;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.form.Form;
import com.googlecode.mgwt.ui.client.widget.form.FormEntry;
import com.googlecode.mgwt.ui.client.widget.input.MDateBox;
import com.googlecode.mgwt.ui.client.widget.input.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MPhoneNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.DetailsViewImpl;
import com.nutrinfomics.geneway.client.GeneWayWidgetList;
import com.nutrinfomics.geneway.client.TextBoxViewImpl;
import com.nutrinfomics.geneway.client.constants.GeneWayConstants;

public class RegisterViewImpl extends TextBoxViewImpl implements RegisterView {
	private MTextBox firstNameField;
	private MTextBox lastNameField;
	private MEmailTextBox emailField;
	private MPhoneNumberTextBox phoneNumberField;
	private MDateBox birthdateField;
	private MNumberTextBox weightField;
	private MNumberTextBox heightField;
	private MListBox genderField;
	private MTextBox usernameField;
	private MPasswordTextBox passwordField;
	private MPasswordTextBox repeatPasswordField;
	
	private Button registerButton;

	public RegisterViewImpl(){
		ScrollPanel scrollPanel = new ScrollPanel();
		
		WidgetList widgetList = new GeneWayWidgetList();
	
		firstNameField = new MTextBox();
		toggleBoxAppearance(firstNameField, constants.privatename());
//		widgetList.add(firstNameField);
		
		lastNameField = new MTextBox();
		toggleBoxAppearance(lastNameField, constants.familyname());
//		widgetList.add(lastNameField);
		
		
//		phoneNumberField = new MPhoneNumberTextBox();
//		widgetList.add(new FormEntry(constants.phonenumber(), phoneNumberField));
//		birthdateField = new MDateBox();
//		widgetList.add(new FormEntry(constants.birthdate(), birthdateField));
//		weightField = new MNumberTextBox();
//		widgetList.add(new FormEntry(constants.weight(), weightField));
//		heightField = new MNumberTextBox();
//		widgetList.add(new FormEntry(constants.height(), heightField));
//		genderField = new MListBox();
//		genderField.addItem(constants.female());
//		genderField.addItem(constants.male());
//		widgetList.add(new FormEntry(constants.gender(), genderField));
		
		usernameField = new MTextBox();
		toggleBoxAppearance(usernameField, constants.username());
		widgetList.add(usernameField);
		
		emailField = new MEmailTextBox();
		toggleBoxAppearance(emailField, constants.email());
//		widgetList.add(emailField);
		
		passwordField = new MPasswordTextBox();
		toggleBoxAppearance(passwordField, constants.password());
		widgetList.add(passwordField);
		
		repeatPasswordField = new MPasswordTextBox();
		toggleBoxAppearance(repeatPasswordField, constants.repeatpassword());
		widgetList.add(repeatPasswordField);
		
		add(widgetList);

	    registerButton = new Button(constants.register());
	    toggleButtonAppearance(registerButton);
	    
	    add(new FlexSpacer());
	    add(registerButton);
	    bodyCenterAlign();
	    
//		scrollPanel.setScrollingEnabledX(false);
//		scrollPanel.setWidget(container);
//		// workaround for android formfields jumping around when using
//		// -webkit-transform
//		scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());
		
//		privateNameField.setFocus(true);
		usernameField.setFocus(true);
		
//		add(scrollPanel);
	}

	@Override
	public HasTapHandlers getRegisterButton() {
		return registerButton;
	}

	@Override
	public String getEmail() {
		return emailField.getText();
	}

	@Override
	public String getUsername() {
		return usernameField.getText();
	}

	@Override
	public String getFirstName() {
		return firstNameField.getText();
	}

	@Override
	public String getLastName() {
		return lastNameField.getText();
	}

	@Override
	public String getPassword() {
		return passwordField.getText();
	}
}
