package com.nutrinfomics.geneway.client;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Display;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public class TextBoxViewImpl extends DetailsViewImpl {

	protected GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();

	protected void toggleBoxAppearance(MTextBox textBox, String placeHolder) {
		textBox.setPlaceHolder(placeHolder);
		textBox.setWidth("30%");
		textBox.getElement().getStyle().setBackgroundColor(Styles.WHITE);
	}

	protected void toggleButtonAppearance(Button button) {
		button.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
	    button.getElement().getStyle().setColor(Styles.BLACK);
	    button.getElement().getStyle().setDisplay(Display.BLOCK);
	    button.getElement().getStyle().setProperty("margin", "auto");
	    button.getElement().getStyle().setBackgroundImage("none");
	    button.getElement().getStyle().setBorderStyle(BorderStyle.NONE);
	}

}
