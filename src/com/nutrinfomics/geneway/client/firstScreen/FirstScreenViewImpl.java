package com.nutrinfomics.geneway.client.firstScreen;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.DetailsViewImpl;
import com.nutrinfomics.geneway.client.GeneWayImageButton;
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.style.Styles;

public class FirstScreenViewImpl extends DetailsViewImpl implements
		FirstScreenView {

	private Button existingAccountButton;
	private Button newAccountButton;
	
	public FirstScreenViewImpl(){
		GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();

		hideHeaderPanel();
		
		VerticalPanel panel = new VerticalPanel();
		
		add(panel);
		
	    GeneWayImageButton geneWayImage = new GeneWayImageButton();
	    Style iconStyle = geneWayImage.image.getStyle();
	    double factor = 1.5;
		String width = iconStyle.getWidth();
		iconStyle.setWidth(Double.parseDouble(width.substring(0, width.length() - 2)) * factor, Style.Unit.PX);
	    String height = iconStyle.getHeight();
		iconStyle.setHeight(Double.parseDouble(height.substring(0, height.length() - 2)) * factor, Style.Unit.PX);
	    
		Style style = geneWayImage.getElement().getStyle();
//		style.setMarginBottom(80, Unit.PX);
		style.setProperty("margin", "0 auto 80px auto");
//		style.setProperty("margin-right", "auto");
		
	    if(MGWT.getOsDetection().isAndroid()){
//			geneWayImage.image.setAttribute("style", "background-size: contain;");

	    	//set background-size to contain
	    }
	    
	    existingAccountButton = new Button(constants.existingAccount());
	    newAccountButton = new Button(constants.newAccount());

	    setButtonStyle(existingAccountButton);
	    setButtonStyle(newAccountButton);
	    
	    panel.add(geneWayImage);
	    panel.add(new FlexSpacer());
	    panel.add(new FixedSpacer());
	    panel.add(new FixedSpacer());
	    panel.add(existingAccountButton);
	    panel.add(newAccountButton);

	    bodyCenterAlign();
	    
	}


	private void setButtonStyle(Button button) {
		button.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
		button.getElement().getStyle().setColor(Styles.WHITE);
	    button.getElement().getStyle().setDisplay(Display.BLOCK);
	    button.getElement().getStyle().setProperty("margin", "auto");
	    button.getElement().getStyle().setBackgroundImage("none");
	    button.getElement().getStyle().setBorderStyle(BorderStyle.NONE);
	}

	
	@Override
	public HasTapHandlers getNewAccountButton() {
		return newAccountButton;
	}

	@Override
	public HasTapHandlers getExistingAccountButton() {
		return existingAccountButton;
	}

}
