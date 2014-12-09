package com.nutrinfomics.geneway.client.login;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.GeneWayWidgetList;
import com.nutrinfomics.geneway.client.TextBoxViewImpl;
import com.nutrinfomics.geneway.client.style.Styles;

public class LoginViewImpl extends TextBoxViewImpl implements LoginView {
	private MTextBox usernameField;
	private MPasswordTextBox passwordField;

	private Button loginButton;
	public LoginViewImpl(){

		WidgetList widgetList = new GeneWayWidgetList();
		
		usernameField = new MTextBox();
		toggleBoxAppearance(usernameField, constants.username());

		passwordField = new MPasswordTextBox();
		toggleBoxAppearance(passwordField, constants.password());

		widgetList.add(usernameField);
		widgetList.add(passwordField);

		add(widgetList);

	    loginButton = new Button(constants.login());
	    toggleButtonAppearance(loginButton);
	    
	    add(new FlexSpacer());
	    add(loginButton);
	    bodyCenterAlign();
	    
		usernameField.setFocus(true);
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
		return passwordField.getText();
	}
}
