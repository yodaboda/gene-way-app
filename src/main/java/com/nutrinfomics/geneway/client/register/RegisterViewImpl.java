package com.nutrinfomics.geneway.client.register;


import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.MDateBox;
import com.googlecode.mgwt.ui.client.widget.input.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MPhoneNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
import com.googlecode.mgwt.ui.client.widget.input.radio.MRadioButton;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.GeneWayWidgetList;
import com.nutrinfomics.geneway.client.TextBoxViewImpl;
import com.nutrinfomics.geneway.client.privacyPolicy.PrivacyPolicyPlace;
import com.nutrinfomics.geneway.client.termsOfService.TermsOfServicePlace;


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
	private MRadioButton acceptTermsOfServicButton;

	public RegisterViewImpl(){
//		ScrollPanel scrollPanel = new ScrollPanel();
		
		WidgetList widgetList = new GeneWayWidgetList();
	
		phoneNumberField = new MPhoneNumberTextBox();
		toggleBoxAppearance(phoneNumberField, constants.phonenumber());
		widgetList.add(phoneNumberField);
		
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

		Anchor termsAnchor = new Anchor(constants.termsOfService(), "#" + ClientFactoryFactory.getClientFactory().getPlaceHistoryMapper().getToken(new TermsOfServicePlace()));

		Anchor privacyAnchor = new Anchor(constants.privacyPolicy(), "#" + ClientFactoryFactory.getClientFactory().getPlaceHistoryMapper().getToken(new PrivacyPolicyPlace()));
		
		FlexPanel termsPanel = new FlexPanel();
		termsPanel.setOrientation(Orientation.HORIZONTAL);
		termsPanel.getElement().getStyle().setPaddingTop(20, Unit.PX);
		termsPanel.add(new HTML("I accept "));
		termsPanel.add(termsAnchor);
		termsPanel.add(new HTML(constants.and()));
		termsPanel.add(privacyAnchor);
		
		acceptTermsOfServicButton = new MRadioButton("terms");
		acceptTermsOfServicButton.addTapHandler(new TapHandler() {
			private boolean previousState = false;
			@Override
			public void onTap(TapEvent event) {
				if(previousState) acceptTermsOfServicButton.setValue(false);
				previousState = acceptTermsOfServicButton.getValue();
			}
		});
//		acceptTermsOfServicButton.setText(constants.ßacceptTerms());
		termsPanel.add(acceptTermsOfServicButton);
		
		add(new FlexSpacer());
		add(new FixedSpacer());
		add(termsPanel);
//		add(acceptTermsOfServicButton);
		
	    registerButton = new Button(constants.start());
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
//		usernameField.setFocus(true);
		phoneNumberField.setFocus(true);
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

	@Override
	public String getPhoneNumber() {
		return phoneNumberField.getValue();
	}

	@Override
	public String getRepeatPassword() {
		return repeatPasswordField.getText();
	}

	@Override
	public boolean isTermsBoxChecked() {
		return acceptTermsOfServicButton.getValue();
	}
}
