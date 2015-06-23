package com.nutrinfomics.geneway.client.personalIdentifier;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.FieldsWidgetListViewImpl;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.ValidationTextBox;

public class PersonalIdentifierViewImpl extends FieldsWidgetListViewImpl
		implements PersonalIdentifierView {
	private ValidationTextBox<String> identifierValidationField;
	private Button unlockButton;
	
	public PersonalIdentifierViewImpl(){
		identifierValidationField = new ValidationTextBox<String>(new MTextBox(), constants.identifier(), 
				"identifier");
		addValidationField(identifierValidationField);
		
		add(widgetList);
		
	    unlockButton = new Button(constants.unlock());
	    toggleButtonAppearance(unlockButton);
	    
	    add(new FlexSpacer());
	    add(unlockButton);
	    bodyCenterAlign();

	    identifierValidationField.getValueBox().setFocus(true);
	}
	
	@Override
	public String getIdentifier(){
		return identifierValidationField.getValueBox().getText();
	}
	
	@Override
	public HasTapHandlers getUnlockButton() {
		return unlockButton;
	}

}
