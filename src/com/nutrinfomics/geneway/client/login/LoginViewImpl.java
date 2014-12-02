package com.nutrinfomics.geneway.client.login;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.form.Form;
import com.googlecode.mgwt.ui.client.widget.form.FormEntry;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.DetailsViewImpl;
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.style.Styles;

public class LoginViewImpl extends DetailsViewImpl implements LoginView {
	private MTextBox usernameField;
	private MPasswordTextBox passwordField;

	private Button loginButton;
	private Button registerButton;
	
	public LoginViewImpl(){
//		ScrollPanel scrollPanel = new ScrollPanel();

		GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();

//		Form widgetList = new Form();
//		widgetList.setHeader(new Label(constants.login()));

		WidgetList widgetList = new WidgetList();
	    String sessionID = Cookies.getCookie("sid");
//	    if ( sessionID != null ) checkWithServerIfSessionIdIsStillLegal();
//	    else displayLoginBox();
		
		usernameField = new MTextBox();
		usernameField.setPlaceHolder(constants.username());
		usernameField.setWidth("30%");
		usernameField.getElement().getStyle().setBackgroundColor(Styles.WHITE);

		passwordField = new MPasswordTextBox();
		passwordField.setWidth("30%");
		passwordField.setPlaceHolder(constants.password());
		passwordField.getElement().getStyle().setBackgroundColor(Styles.WHITE);

//		widgetList.add(new FormEntry(constants.username(), usernameField));
//		widgetList.add(new FormEntry(constants.password(), passwordField));
		

		widgetList.add(usernameField);
		widgetList.add(passwordField);

		add(widgetList);

	    loginButton = new Button(constants.login());
	    loginButton.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
	    loginButton.getElement().getStyle().setColor(Styles.BLACK);
	    loginButton.getElement().getStyle().setDisplay(Display.BLOCK);
	    loginButton.getElement().getStyle().setProperty("margin", "auto");
	    loginButton.getElement().getStyle().setBackgroundImage("none");
	    loginButton.getElement().getStyle().setBorderStyle(BorderStyle.NONE);
//	    loginButton.getElement().setAttribute("style",Styles.BACKGROUND_COLOR + "color: black; display: block;" + Styles.HORIZONTAL_CENTER_ALIGN);
	    registerButton = new Button(constants.register());
	    registerButton.setSmall(true);
	    
	    add(new FlexSpacer());
	    add(loginButton);
	    bodyCenterAlign();
	    
		usernameField.setFocus(true);

//	    container.add(registerButton);
		
//		scrollPanel.setScrollingEnabledX(false);
//		scrollPanel.setWidget(container);
//		// workaround for android formfields jumping around when using
//		// -webkit-transform
//		scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());
//		
//		
//		add(scrollPanel);
	}
	@Override
	public HasTapHandlers getRegisterButton() {
		return registerButton;
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
