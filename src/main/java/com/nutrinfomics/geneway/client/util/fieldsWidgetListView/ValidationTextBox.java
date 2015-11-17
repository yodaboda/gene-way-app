package com.nutrinfomics.geneway.client.util.fieldsWidgetListView;

import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.base.MTextBoxBase;
import com.googlecode.mgwt.ui.client.widget.base.MValueBoxBase;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.nutrinfomics.geneway.client.style.Styles;

public class ValidationTextBox<T> extends FlexPanel{
	private MValueBoxBase<T> textBox;
	private HTML validationText;
	private int index;
	private String violationTemplateIdentifier;
	
	public ValidationTextBox(MValueBoxBase<T> textBox, String placeHolder, String violationTemplate){
		setOrientation(Orientation.VERTICAL);
		setWidth("100%");
		this.textBox = textBox;
		toggleBoxAppearance(placeHolder);
		
		validationText = new HTML();
		validationText.getElement().getStyle().setProperty("WebkitAlignSelf", "center"); // for safari 7+
		validationText.getElement().getStyle().setProperty("alignSelf", "center");
		validationText.getElement().getStyle().setProperty("fontSize", "70%");
		resetValidationText();
		add(textBox);
		add(validationText);

		violationTemplateIdentifier = violationTemplate;
	}
	
	public int getIndex(){
		return index;
	}

	public void setIndex(int index){
		this.index = index;
	}
	
	public String getViolationTemplateIdentifier(){
		return violationTemplateIdentifier;
	}
	
	protected void toggleBoxAppearance(String placeHolder) {
		textBox.setPlaceHolder(placeHolder);
//		textBox.setWidth("30%");
		textBox.getElement().getStyle().setBackgroundColor(Styles.WHITE);
		textBox.getElement().getStyle().setColor(Styles.BLACK);
		
		//TODO: on android, the color of the text of the input field is white
		// and hence it is not possible to see it. As a workaround, set the 
		// background color to lightblue
		if(MGWT.getOsDetection().isAndroid()){
			textBox.getElement().getStyle().setBackgroundColor(Styles.LIGHTBLUE);
		}
	}

	public void resetValidationText(){
		validationText.getElement().getStyle().setColor(Styles.WHITE);
		validationText.setText("|");		
	}
	
	public void setValidationText(String text){
		validationText.getElement().getStyle().setColor(Styles.RED);
		validationText.setText(text);
	}
	public MValueBoxBase<T> getValueBox(){
		return textBox;
	}
}
