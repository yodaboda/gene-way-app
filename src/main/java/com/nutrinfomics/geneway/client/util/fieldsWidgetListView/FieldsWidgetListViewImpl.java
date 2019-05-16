package com.nutrinfomics.geneway.client.util.fieldsWidgetListView;

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.user.client.Window;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.nutrinfomics.geneway.client.GeneWayWidgetList;
import com.nutrinfomics.geneway.client.util.TextBoxViewImpl;

public class FieldsWidgetListViewImpl extends TextBoxViewImpl implements FieldsWidgetListView{

	protected WidgetList widgetList;
	private List<ValidationTextBox> validationTextBoxes = new ArrayList<ValidationTextBox>();

	public FieldsWidgetListViewImpl(){
		widgetList = new GeneWayWidgetList(new FieldsWidgetListAppearance());
	}
	
	protected void addValidationField(ValidationTextBox validationTextBox) {
		widgetList.add(validationTextBox);
		validationTextBox.setIndex(widgetList.getWidgetCount() - 1);
		validationTextBoxes.add(validationTextBox);
	}

	private ValidationTextBox constraintViolation(ConstraintViolation<?> violation) {
		for(ValidationTextBox validationTextBox : validationTextBoxes){
			if(violation.getMessageTemplate().contains(validationTextBox.getViolationTemplateIdentifier())){
				validationTextBox.setValidationText(violation.getMessage());
				widgetList.setSelectAble(validationTextBox.getIndex(), true);
				return validationTextBox;
			}
		}
		return null;
	}

	@Override
	public void constraintViolations(Set<ConstraintViolation<?>> violations) {
		if(violations == null || violations.isEmpty()) return;
		Set<ValidationTextBox> unaffectedValidationTextBoxes = new HashSet<ValidationTextBox>(validationTextBoxes);
		
		Iterator<ConstraintViolation<?>> iterator = violations.iterator();
			
		while(iterator.hasNext()){
			ConstraintViolation<?> next = iterator.next();
			ValidationTextBox validationTextBox = constraintViolation(next);
			if(validationTextBox != null){
				unaffectedValidationTextBoxes.remove(validationTextBox);
				iterator.remove();
			}
		}
		for(ValidationTextBox validationTextBox : unaffectedValidationTextBoxes){
			validationTextBox.resetValidationText();
			widgetList.setSelectAble(validationTextBox.getIndex(), false);
		}
	}

}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */