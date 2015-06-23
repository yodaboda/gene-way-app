package com.nutrinfomics.geneway.client.personalDetails;

import java.util.Date;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.MDateBox;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.SimpleDateProxy;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.FieldsWidgetListViewImpl;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.ValidationTextBox;
import com.nutrinfomics.geneway.shared.Gender;

public class PersonalDetailsViewImpl extends FieldsWidgetListViewImpl implements
		PersonalDetailsView {

	private Button nextButton;
	private Button demoButton;
	
	private ValidationTextBox<String> privateNameValidationField;
	private ValidationTextBox<String> familyNameValidationField;

	private ValidationTextBox<Date> birthdayValidationField;
//	private ValidationTextBox genderValidationField;
	private MListBox genderBox;
	private ValidationTextBox<String> heightValidationField;
	private ValidationTextBox<String> weightValidationField;
	
	public PersonalDetailsViewImpl(){


		birthdayValidationField = new ValidationTextBox<Date>(new MDateBox(), constants.birthdate(), "birthdate");
		addValidationField(birthdayValidationField);
		
		genderBox = new MListBox();
		genderBox.addItem(Gender.FEMALE.toString(), constants.female());
		genderBox.addItem(Gender.MALE.toString(), constants.male());
		
		widgetList.add(genderBox);
		
		heightValidationField = new ValidationTextBox<String>(new MNumberTextBox(), constants.height(), 
				"height");
		addValidationField(heightValidationField);

		weightValidationField = new ValidationTextBox<String>(new MNumberTextBox(), constants.weight(), 
				"weight");
		addValidationField(weightValidationField);
		
		add(widgetList);
			
		nextButton = new Button(constants.next());
		toggleButtonAppearance(nextButton);
		    
		demoButton = new Button(constants.demo());
	    toggleButtonAppearance(demoButton);
	    demoButton.setSmall(true);
	    demoButton.getElement().getStyle().setColor(Styles.GREEN_DARK);
	    
	    add(new FlexSpacer());
	    add(nextButton);
	    add(new FlexSpacer());
	    add(demoButton);
	    
		bodyCenterAlign();
		    
		birthdayValidationField.getValueBox().setFocus(true);
	}
	
	@Override
	public HasTapHandlers getNextButton() {
		return nextButton;
	}

	@Override
	public HasTapHandlers getDemoButton() {
		return demoButton;
	}
	
	@Override
	public Date getBirthdayDate(){
		return birthdayValidationField.getValueBox().getValue();
	}
	
	@Override
	public Gender getGender(){
		return Gender.valueOf(genderBox.getSelectedItemText());
	}
	@Override
	public float getHeight(){
		return Float.parseFloat(heightValidationField.getValueBox().getText());
	}
	@Override
	public float getWeight(){
		return Float.parseFloat(weightValidationField.getValueBox().getText());		
	}

}
