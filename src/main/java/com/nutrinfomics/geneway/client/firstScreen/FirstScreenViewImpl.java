package com.nutrinfomics.geneway.client.firstScreen;


import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.impl.HyperlinkImpl;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton;
import com.googlecode.mgwt.ui.client.widget.carousel.Carousel;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
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
import com.nutrinfomics.geneway.client.register.RegisterPlace;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.client.util.LanguageUtils;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public class FirstScreenViewImpl extends DetailsViewImpl implements
		FirstScreenView {

	private Button existingAccountButton;
	private Button newAccountButton;
	private Anchor registerAnchor;
	
	public FirstScreenViewImpl(){
		GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();

		hideHeaderPanel();
		
		VerticalPanel panel = new VerticalPanel();
		
//		WidgetList panel = new WidgetList();
		
		add(panel);
		
	    GeneWayImageButton geneWayImage = new GeneWayImageButton();
	    Style iconStyle = geneWayImage.image.getStyle();
	    double factor = 1.3;
		String width = iconStyle.getWidth();
		iconStyle.setWidth(Double.parseDouble(width.substring(0, width.length() - 2)) * factor, Style.Unit.PX);
	    String height = iconStyle.getHeight();
		iconStyle.setHeight(Double.parseDouble(height.substring(0, height.length() - 2)) * factor, Style.Unit.PX);
	    
		Style style = geneWayImage.getElement().getStyle();
//		style.setMarginBottom(80, Unit.PX);
		style.setProperty("margin", "0 auto 20px auto");
//		style.setProperty("margin-right", "auto");
		
	    if(MGWT.getOsDetection().isAndroid()){
//			geneWayImage.image.setAttribute("style", "background-size: contain;");

	    	//set background-size to contain
	    }
	    
		final Image beforeImage = new Image(LocalImageHolder.get().reemBefore());

	    int imageHeight = beforeImage.getHeight() / 2;
	    int imageWidth = beforeImage.getWidth() / 2;

		beforeImage.setPixelSize(imageWidth, imageHeight);
		
		final Image afterImage = new Image(LocalImageHolder.get().reemAfter());
		afterImage.setPixelSize(imageWidth, imageHeight);
		
//	    existingAccountButton = new Button(constants.startHere());
	    newAccountButton = new Button(constants.startHere());
	    
//	    setButtonStyle(existingAccountButton);
	    setButtonStyle(newAccountButton);

	    registerAnchor = new Anchor(constants.startHere(), "#" + ClientFactoryFactory.getClientFactory().getPlaceHistoryMapper().getToken(new RegisterPlace()));
	    registerAnchor.setStylePrimaryName("sp-circle-link");
	    
//	    registerAnchor.get;
	    panel.add(geneWayImage);
	    
	    HTML animatedText = new HTML("<div class=\"sp-container\" dir=\"ltr\">"+
	    								"<div class=\"sp-content\">" +
//	    									"<div class=\"sp-globe\"></div>" +
	    										"<h2 class=\"frame-1\">" + constants.reem() + "</h2>" +

        										"<h2 class=\"frame-2\">" + constants.lost35kg() + "</h2>" +

        										"<h2 class=\"frame-3\">" + constants.in7Months() + "</h2>" +

        										"<h2 class=\"frame-4\">" + constants.youQM() + "</h2>" +

        										"<h2 class=\"frame-5\"><span>" + constants.timeInTimeForChange() + "</span> <span>" + constants.forInTimeForChange() + "</span> <span>" +  constants.changeInTimeForChange() + "</span></h2>"+
//        										registerAnchor.getHTML() + 
        										//	    										"<a class=\"sp-circle-link\" href=\"#" + ClientFactoryFactory.getClientFactory().getPlaceHistoryMapper().getToken(new RegisterPlace()) + "\">Join Now!</a>" +
        								"</div>" +
	    							"</div>");
		animatedText.setWidth("100%");

		
		HTML animatedImages = new HTML("<div id=\"cf\">" +
		  "<img class=\"bottom\" src=\" "+ afterImage.getUrl() + "\" style = \"width:82px; height:210px; \" />" +
		  "<img class=\"top\" src=\" " + beforeImage.getUrl() + "\" style = \"width:82px; height:210px; \" />" +
		"</div>");
		animatedImages.setPixelSize(imageWidth, imageHeight);
		animatedImages.getElement().getStyle().setProperty("margin", "auto");
		animatedImages.getElement().getStyle().setMarginTop(50, Unit.PX);

		Panel registerPanel = new Panel();
		registerPanel.setStylePrimaryName("sp-content");
		registerPanel.getElement().getStyle().setProperty("margin", "auto");
		registerPanel.getElement().getStyle().setMarginTop(-80, Unit.PX);
		registerPanel.setWidth("100%");
//		registerAnchor.getElement().getStyle().setWidth(afterImage.getWidth(), Unit.PX);;
		registerPanel.add(registerAnchor);
		
//		Panel animatedPanel = new Panel();
////		animatedPanel.setStylePrimaryName("crossfade");
//		animatedPanel.setStylePrimaryName("cf");
//		animatedPanel.setPixelSize(imageWidth, imageHeight);
//		afterImage.setStylePrimaryName("bottom");
//		animatedPanel.add(afterImage);
//		beforeImage.setStylePrimaryName("top");
//		animatedPanel.add(beforeImage);
//		animatedPanel.getElement().getStyle().setMarginTop(50, Unit.PX);
		
		panel.add(animatedText);
	    panel.add(new FlexSpacer());
	    panel.add(new FixedSpacer());
	    
//	    panel.add(animationWidget);
//	    panel.add(imagePanel);
	    panel.add(animatedImages);
	    panel.add(registerPanel);
//	    panel.add(animatedPanel);
	    
	    panel.add(new FixedSpacer());
//	    panel.add(existingAccountButton);
	    panel.add(newAccountButton);
	    
	    final MListBox languageBox = new MListBox();
	    languageBox.getElement().getStyle().setColor(Styles.BLACK);
	    
//	    Window.alert("locale cookie: " + Cookies.getCookie(LocaleInfo.getLocaleCookieName()));
	    	    
//	    if(LocaleInfo.getCurrentLocale().isRTL()){
//		    languageBox.getElement().getStyle().setPaddingRight(padding, Unit.PX);
//	    }
//	    else{
//		    languageBox.getElement().getStyle().setPaddingLeft(padding, Unit.PX);
//	    }
	    languageBox.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
//	    languageBox.getElement().getStyle().setProperty("margin", "auto");
	    LanguageUtils.initializeLanguageBox(languageBox);

//	    Panel languagePanel = new Panel();
//	    languagePanel.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
//	    languagePanel.getElement().getStyle().setPadding(0, Unit.PX);
//	    languagePanel.getElement().getStyle().setBorderWidth(0, Unit.PX);
//	    languagePanel.getElement().getStyle().setBackgroundColor("transparent");
////	    languagePanel.getElement().getStyle().setBorderWidth(value, unit);
//	    languagePanel.add(languageBox);
	    
//	    panel.add(languageBox);
	    
	    showFooter();
	    addToFooter(languageBox);
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
	
	@Override
	public HasClickHandlers getNewAccountAnchor(){
		return registerAnchor;
	}

}
