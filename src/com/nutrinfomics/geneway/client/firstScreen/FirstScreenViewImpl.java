package com.nutrinfomics.geneway.client.firstScreen;


import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton;
import com.googlecode.mgwt.ui.client.widget.carousel.Carousel;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.Panel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.nutrinfomics.geneway.client.AbstractImageButton;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.DetailsViewImpl;
import com.nutrinfomics.geneway.client.GeneWayImageButton;
import com.nutrinfomics.geneway.client.GeneWayImageButtonAppearance;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder;
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
		
//		WidgetList panel = new WidgetList();
		
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
	    
	    
	    ImageButton before = new AbstractImageButton(new GeneWayImageButtonAppearance(), LocalImageHolder.get().reemBefore(), "", 
				Styles.WHITE, Styles.WHITE, Styles.GREEN) {
		};

//	    final ImageButton after = new AbstractImageButton(new GeneWayImageButtonAppearance(), LocalImageHolder.get().reemAfter(), "", 
//				Styles.WHITE, Styles.WHITE, Styles.GREEN) {
//		};
		
		final Image beforeImage = new Image(LocalImageHolder.get().reemBefore());
		beforeImage.setPixelSize(beforeImage.getWidth() / 2, beforeImage.getHeight() / 2);
		
		final Image afterImage = new Image(LocalImageHolder.get().reemAfter());
		afterImage.setPixelSize(afterImage.getWidth() / 2, afterImage.getHeight() / 2);
		
		
	    existingAccountButton = new Button(constants.existingAccount());
	    newAccountButton = new Button(constants.newAccount());

	    setButtonStyle(existingAccountButton);
	    setButtonStyle(newAccountButton);

	    final Panel imagePanel = new Panel();
	    imagePanel.add(beforeImage);
	    
	    
	    Timer timer = new Timer() {
	    	private double opacity = 1.;
	    	private boolean fading = true;
			@Override
			public void run() {
				if(fading){
					opacity -= .01;
					if(opacity < 0){
						imagePanel.remove(beforeImage);
						opacity = 0;
						fading = false;
						afterImage.getElement().getStyle().setOpacity(opacity);
						imagePanel.add(afterImage);
					}
					else{
						beforeImage.getElement().getStyle().setOpacity(opacity);
					}
				}
				else{
					opacity += .01;
					if(opacity > 1){
						cancel();
					}
					else{
						afterImage.getElement().getStyle().setOpacity(opacity);
					}
				}
			}
		};

		timer.scheduleRepeating(100);
		
//	    panel.getElement().getStyle().setBackgroundImage(beforeImage.getUrl());
	    panel.add(geneWayImage);
	    panel.add(new FlexSpacer());
	    panel.add(new FixedSpacer());
	    panel.add(imagePanel);
	    
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
