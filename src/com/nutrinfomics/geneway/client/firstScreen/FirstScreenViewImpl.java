package com.nutrinfomics.geneway.client.firstScreen;


import java.util.Date;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
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
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
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
		style.setProperty("margin", "0 auto 40px auto");
//		style.setProperty("margin-right", "auto");
		
	    if(MGWT.getOsDetection().isAndroid()){
//			geneWayImage.image.setAttribute("style", "background-size: contain;");

	    	//set background-size to contain
	    }
	    
	    
//	    ImageButton before = new AbstractImageButton(new GeneWayImageButtonAppearance(), LocalImageHolder.get().reemBefore(), "", 
//				Styles.WHITE, Styles.WHITE, Styles.GREEN) {
//		};

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

	    
	    final MListBox languageBox = new MListBox();
	    languageBox.getElement().getStyle().setColor(Styles.BLACK);
	    
	    if(LocaleInfo.getCurrentLocale().isRTL()){
		    languageBox.getElement().getStyle().setPaddingRight(48, Unit.PX);
	    }
	    else{
		    languageBox.getElement().getStyle().setPaddingLeft(35, Unit.PX);
	    }
	    languageBox.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
//	    languageBox.getElement().getStyle().setProperty("margin", "auto");
	    panel.add(languageBox);
	    initializeLanguageBox(languageBox);
	    
	    bodyCenterAlign();
	    
	}

	private void initializeLanguageBox(final MListBox languageBox) {
		final String cookieName=LocaleInfo.getLocaleCookieName();
	    final String queryParam=LocaleInfo.getLocaleQueryParam();
	    if (cookieName == null && queryParam == null) {
//	      localeSelectionCell.getStyle().setDisplay(Display.NONE);
	      return;
	    }
	    String currentLocale=LocaleInfo.getCurrentLocale().getLocaleName();
	    if (currentLocale.equals("default")) {
	    	currentLocale="en";
	    }
	    String[] localeNames=LocaleInfo.getAvailableLocaleNames();
	    for (  String localeName : localeNames) {
	    	if(!localeName.equals("default")){
	    		String nativeName=LocaleInfo.getLocaleNativeDisplayName(localeName);
	    		languageBox.addItem(nativeName,localeName);
	    		if (localeName.equals(currentLocale)) {
	    			languageBox.setSelectedIndex(languageBox.getItemCount() - 1);
	    		}
	    	}
	    }
	    languageBox.addChangeHandler(new ChangeHandler() {

	    	@Override
	    	public void onChange(ChangeEvent event) {
	    		String localeName=languageBox.getValue(languageBox.getSelectedIndex());
	    		if (cookieName != null) {
	    			Date expires=new Date();
	    			expires.setYear(expires.getYear() + 1);
	    			Cookies.setCookie(cookieName,localeName,expires);
	    		}
//	    		if (queryParam != null) {
//	    			UrlBuilder builder=Location.createUrlBuilder().setParameter(queryParam,localeName);
//	    			Window.Location.replace(builder.buildString());
//	    		}
//	    		else {
	    			Window.Location.reload();
//	    		}
	    	}			
	    });
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
