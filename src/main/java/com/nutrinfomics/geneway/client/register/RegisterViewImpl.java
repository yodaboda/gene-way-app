package com.nutrinfomics.geneway.client.register;


import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
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
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.GeneWayWidgetList;
import com.nutrinfomics.geneway.client.privacyPolicy.PrivacyPolicyPlace;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.client.termsOfService.TermsOfServicePlace;
import com.nutrinfomics.geneway.client.util.TextBoxViewImpl;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.FieldsWidgetListAppearance;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.FieldsWidgetListViewImpl;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.ValidationTextBox;


public class RegisterViewImpl extends FieldsWidgetListViewImpl implements RegisterView {
	private MTextBox firstNameField;
	private MTextBox lastNameField;
	private MEmailTextBox emailField;
	private MDateBox birthdateField;
	private MNumberTextBox weightField;
	private MNumberTextBox heightField;
	private MListBox genderField;

	
	private Button registerButton;
	private ValidationTextBox<String> nickNameValidationField;
	private ValidationTextBox phoneNumberValidationField;
	private ValidationTextBox usernameValidationField;
	private ValidationTextBox passwordValidationField;
	private ValidationTextBox repeatPasswordValidationField;
	private Button termsButton;
	private Button privacyButton;
	private CheckBox acceptTermsOfServiceCheckBox;
	public RegisterViewImpl(){
//		ScrollPanel scrollPanel = new ScrollPanel();
		
		nickNameValidationField = new ValidationTextBox<String>(new MTextBox(), constants.nickname(), 
				"nickname");
		addValidationField(nickNameValidationField);
	
		phoneNumberValidationField = new ValidationTextBox(new MPhoneNumberTextBox(), constants.phonenumber(), 
															"phonenumber");
//		toggleBoxAppearance(phoneNumberField, constants.phonenumber());
		addValidationField(phoneNumberValidationField);
		
		
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
		
		usernameValidationField = new ValidationTextBox(new MTextBox(), constants.username(), 
														"username");
//		addValidationField(usernameValidationField);
		
		emailField = new MEmailTextBox();
		toggleBoxAppearance(emailField, constants.email());
//		widgetList.add(emailField);
		

		passwordValidationField = new ValidationTextBox(new MPasswordTextBox(), constants.password(), 
														"password");
		addValidationField(passwordValidationField);
		
		repeatPasswordValidationField = new ValidationTextBox(new MPasswordTextBox(), constants.repeatpassword(), 
																"repeatPassword");
		addValidationField(repeatPasswordValidationField);
		
		add(widgetList);

		termsButton = new Button(constants.termsOfService());
		toggleButtonAppearance(termsButton);
		termsButton.getElement().getStyle().setFontWeight(FontWeight.NORMAL);
		termsButton.getElement().getStyle().setColor(Styles.HYPER_LINK_BLUE);
		privacyButton = new Button(constants.privacyPolicy());
		toggleButtonAppearance(privacyButton);
		privacyButton.getElement().getStyle().setFontWeight(FontWeight.NORMAL);
		privacyButton.getElement().getStyle().setColor(Styles.HYPER_LINK_BLUE);
//		privacyButton.setSmall(true);
		FlexPanel termsPanel = new FlexPanel();
		termsPanel.setOrientation(Orientation.VERTICAL);
		termsPanel.getElement().getStyle().setMarginTop(10, Unit.PX);
		termsPanel.getElement().getStyle().setProperty("WebkitAlignItems", "center");
		termsPanel.getElement().getStyle().setProperty("alignItems", "center");

		FlexPanel firstRowPanel = new FlexPanel();
		firstRowPanel.setOrientation(Orientation.HORIZONTAL);
		firstRowPanel.getElement().getStyle().setProperty("WebkitAlignItems", "center");
		firstRowPanel.getElement().getStyle().setProperty("alignItems", "center");

		acceptTermsOfServiceCheckBox = new CheckBox();
		acceptTermsOfServiceCheckBox.getElement().getStyle().setMarginRight(4, Unit.PX);
		firstRowPanel.add(acceptTermsOfServiceCheckBox);
		firstRowPanel.add(new HTML(" " + constants.acceptTerms() + " "));
		
		FlexPanel secondRowPanel = new FlexPanel();
		secondRowPanel.setOrientation(Orientation.HORIZONTAL);
		secondRowPanel.getElement().getStyle().setProperty("WebkitAlignItems", "center");
		secondRowPanel.getElement().getStyle().setProperty("alignItems", "center");

		secondRowPanel.add(termsButton);
		secondRowPanel.add(new HTML(constants.and() + " "));
		secondRowPanel.add(privacyButton);

		termsPanel.add(firstRowPanel);
		termsPanel.add(secondRowPanel);
		
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
		nickNameValidationField.getValueBox().setFocus(true);

//		phoneNumberValidationField.getValueBox().setFocus(true);
//		add(scrollPanel);
	}

	@Override
	public HasTapHandlers getRegisterButton() {
		return registerButton;
	}

	@Override
	public String getNickName(){
		return nickNameValidationField.getValueBox().getValue();
	}
	
	@Override
	public String getEmail() {
		return emailField.getText();
	}

	@Override
	public String getUsername() {
		return usernameValidationField.getValueBox().getText();
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
		return passwordValidationField.getValueBox().getText();
	}

	@Override
	public String getPhoneNumber() {
		return phoneNumberValidationField.getValueBox().getText();
	}

	@Override
	public String getRepeatPassword() {
		return repeatPasswordValidationField.getValueBox().getText();
	}

	@Override
	public boolean isTermsBoxChecked() {
		return acceptTermsOfServiceCheckBox.getValue();
	}

	@Override
	public HasTapHandlers getPrivacyButton() {
		return privacyButton;
	}

	@Override
	public HasTapHandlers getTermsButton() {
		return termsButton;
	}

}
