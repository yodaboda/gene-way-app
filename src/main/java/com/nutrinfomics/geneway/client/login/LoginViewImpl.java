package com.nutrinfomics.geneway.client.login;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.FieldsWidgetListViewImpl;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.ValidationTextBox;

public class LoginViewImpl extends FieldsWidgetListViewImpl implements LoginView {
	private MTextBox usernameField;
	private ValidationTextBox passwordValidationField;

	private Button loginButton;
	public LoginViewImpl(){
		
//		usernameField = new MTextBox();
//		toggleBoxAppearance(usernameField, constants.username());


		passwordValidationField = new ValidationTextBox(new MPasswordTextBox(), constants.password(), 
			"password");

//		widgetList.add(usernameField);
		addValidationField(passwordValidationField);

		add(widgetList);
		
	    loginButton = new Button(constants.login());
	    toggleButtonAppearance(loginButton);
	    
	    add(new FlexSpacer());
	    add(loginButton);
	    bodyCenterAlign();
	    
		passwordValidationField.getValueBox().setFocus(true);
	}
	@Override
	public HasTapHandlers getLoginButton() {
		return loginButton;
	}
	@Override
	public String getUsername() {
		return usernameField.getText();
	}
	@Override
	public String getPassword() {
		return passwordValidationField.getValueBox().getText();
	}
}
