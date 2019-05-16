package com.nutrinfomics.geneway.client.personalDetails;

/*
 * Copyright (C) 2019 Firas Swidan†
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

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
	private MDateBox birthdayDateBox;
	
	public PersonalDetailsViewImpl(){


//		birthdayValidationField = new ValidationTextBox<Date>(new MDateBox(), constants.birthdate(), "birthdate");
//		addValidationField(birthdayValidationField);

		birthdayDateBox = new MDateBox();
		widgetList.add(birthdayDateBox);
		
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
//	    add(new FlexSpacer());
//	    add(demoButton);
	    
		bodyCenterAlign();
		
		birthdayDateBox.setFocus(true);
//		birthdayValidationField.getValueBox().setFocus(true);
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
		return birthdayDateBox.getValue();
//		return birthdayValidationField.getValueBox().getValue();
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

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */