package com.nutrinfomics.geneway.client.code;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.GeneWayWidgetList;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.client.util.TextBoxViewImpl;

public class CodeViewImpl extends TextBoxViewImpl implements CodeView {

	private MTextBox textBox;
	private Button verifyButton;
	private Button resendCodeButton;
	public CodeViewImpl() {
		
		WidgetList widgetList = new GeneWayWidgetList();
		
		textBox = new MTextBox();
		toggleBoxAppearance(textBox, constants.enterVerificationCode());
		widgetList.add(textBox);
		
	    verifyButton = new Button(constants.verify());
	    toggleButtonAppearance(verifyButton);
	    
	    resendCodeButton = new Button(constants.resendCode());
	    toggleButtonAppearance(resendCodeButton);
	    resendCodeButton.setSmall(true);
	    resendCodeButton.getElement().getStyle().setColor(Styles.GREEN_DARK);
	    add(widgetList);
	    
	    add(new FlexSpacer());
	    add(verifyButton);
	    add(new FlexSpacer());
	    add(resendCodeButton);
	    
	    bodyCenterAlign();
	    
	    textBox.setFocus(true);
	}
	
	@Override
	public HasTapHandlers getVerifyButton() {
		return verifyButton;
	}

	@Override
	public String getEnteredCode() {
		return textBox.getValue();
	}

	@Override
	public HasTapHandlers getResendCodeButton() {
		return resendCodeButton;
	}

}
