package com.nutrinfomics.geneway.client.util.fieldsWidgetListView;

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
