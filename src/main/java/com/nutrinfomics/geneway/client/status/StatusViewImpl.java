package com.nutrinfomics.geneway.client.status;

import com.google.gwt.user.client.ui.CheckBox;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.checkbox.MCheckBox;
import com.googlecode.mgwt.ui.client.widget.input.radio.MRadioButton;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.FieldsWidgetListViewImpl;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.ValidationTextBox;


public class StatusViewImpl extends FieldsWidgetListViewImpl
		implements StatusView {

	private Button nextButton;
	

	private CheckBox constipation;
	private CheckBox diabetes;
	private CheckBox cholesterol;
	private CheckBox triglycerides;
	private CheckBox hairloss;
	private CheckBox fatigue;
	private CheckBox arthritis;
		
	public StatusViewImpl(){

		
		constipation = new CheckBox("constipation");
		constipation.setText(constants.constipation());
		widgetList.add(constipation);
		
		diabetes = new CheckBox("diabetes");
		diabetes.setText(constants.diabetes());
		widgetList.add(diabetes);
		
		hairloss = new CheckBox("hairloss");
		hairloss.setText(constants.hairloss());
		widgetList.add(hairloss);
		
		cholesterol = new CheckBox("cholesterol");
		cholesterol.setText(constants.cholesterol());
		widgetList.add(cholesterol);
		
		fatigue = new CheckBox("fatigue");
		fatigue.setText(constants.fatigue());
		widgetList.add(fatigue);
		
		triglycerides = new CheckBox("triglycerides");
		triglycerides.setText(constants.triglycerides());
		widgetList.add(triglycerides);
		
		arthritis = new CheckBox("arthritis");
		arthritis.setText(constants.arthritis());
		widgetList.add(arthritis);
		
		add(widgetList);
			
		nextButton = new Button(constants.next());
		toggleButtonAppearance(nextButton);
		    	    
	    add(new FlexSpacer());
	    add(nextButton);
	    
		bodyCenterAlign();
		    
//		constipation.getsetFocus(true);
	}

	@Override
	public Button getNextButton(){
		return nextButton;
	}
	@Override
	public boolean isConstipated(){
		return constipation.getValue();
	}
}
