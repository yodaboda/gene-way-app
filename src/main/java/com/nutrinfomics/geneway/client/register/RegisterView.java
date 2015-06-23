package com.nutrinfomics.geneway.client.register;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.input.radio.MRadioButton;
import com.nutrinfomics.geneway.client.DetailsView;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.FieldsWidgetListView;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.ValidationTextBox;

public interface RegisterView extends FieldsWidgetListView {
	public HasTapHandlers getRegisterButton();

	public String getEmail();

	public String getUsername();

	public String getFirstName();

	public String getLastName();

	public String getPassword();
	
	public String getRepeatPassword();

	public String getPhoneNumber();

	public boolean isTermsBoxChecked();

	public HasTapHandlers getPrivacyButton();

	public HasTapHandlers getTermsButton();

	public String getNickName();

}
