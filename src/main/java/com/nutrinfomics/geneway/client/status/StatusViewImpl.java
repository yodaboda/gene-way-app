package com.nutrinfomics.geneway.client.status;

import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.radio.MRadioButton;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.FieldsWidgetListViewImpl;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.ValidationTextBox;


public class StatusViewImpl extends FieldsWidgetListViewImpl
		implements StatusView {

	private Button nextButton;
	

	private MRadioButton constipation;
	private MRadioButton diabetes;
	private MRadioButton cholesterol;
	private MRadioButton triglycerides;
	private MRadioButton hairloss;
	private MRadioButton fatigue;
	private MRadioButton arthritis;
		
	public StatusViewImpl(){

		
		constipation = new MRadioButton("constipation");
		constipation.setText(constants.constipation());
		widgetList.add(constipation);
		
		diabetes = new MRadioButton("diabetes");
		diabetes.setText(constants.diabetes());
		widgetList.add(diabetes);
		
		hairloss = new MRadioButton("hairloss");
		hairloss.setText(constants.hairloss());
		widgetList.add(hairloss);
		
		cholesterol = new MRadioButton("cholesterol");
		cholesterol.setText(constants.cholesterol());
		widgetList.add(cholesterol);
		
		fatigue = new MRadioButton("fatigue");
		fatigue.setText(constants.fatigue());
		widgetList.add(fatigue);
		
		triglycerides = new MRadioButton("triglycerides");
		triglycerides.setText(constants.triglycerides());
		widgetList.add(triglycerides);
		
		arthritis = new MRadioButton("arthritis");
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
