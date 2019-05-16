package com.nutrinfomics.geneway.client.firstScreen;

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
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
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
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Justification;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
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
import com.nutrinfomics.geneway.client.util.TextBoxViewImpl;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public class FirstScreenViewImpl extends TextBoxViewImpl implements
		FirstScreenView {

	private Button existingAccountButton;
	private Button newAccountButton;
	private Anchor registerAnchor;
	private Button goButton;
	private MPasswordTextBox password;
	private Button registerAnchorButton;
	
	public FirstScreenViewImpl(){
		GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();

		hideHeaderPanel();
		
		FlexPanel panel = new FlexPanel();
		panel.setOrientation(Orientation.VERTICAL);
		panel.setJustification(Justification.SPACE_BETWEEN);
		panel.setAlignment(Alignment.CENTER);
		panel.getElement().getStyle().setProperty("height", "100%");
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
	    registerAnchorButton = new Button(constants.startHere());
	    registerAnchorButton.setStylePrimaryName("sp-circle-link");
	    
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
//		registerPanel.add(registerAnchor);
		registerPanel.add(registerAnchorButton);
		
		newAccountButton.getElement().getStyle().setMarginTop(80, Unit.PX);
		
//		Panel animatedPanel = new Panel();
////		animatedPanel.setStylePrimaryName("crossfade");
//		animatedPanel.setStylePrimaryName("cf");
//		animatedPanel.setPixelSize(imageWidth, imageHeight);
//		afterImage.setStylePrimaryName("bottom");
//		animatedPanel.add(afterImage);
//		beforeImage.setStylePrimaryName("top");
//		animatedPanel.add(beforeImage);
//		animatedPanel.getElement().getStyle().setMarginTop(50, Unit.PX);
		
//		password = new MPasswordTextBox();
//		password.setPlaceHolder("Speak friend and enter");
////		password.setWidth("75%");
//		password.getElement().getStyle().setBackgroundColor(Styles.WHITE);
//		
//		password.setFocus(true);
		
		panel.add(new FixedSpacer());
		
//		panel.add(password);
//		goButton = new Button(constants.start());
//		toggleButtonAppearance(goButton);
		
		panel.add(new FixedSpacer());
//		panel.add(goButton);
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
//		return goButton;
	}

	@Override
	public HasTapHandlers getExistingAccountButton() {
		return existingAccountButton;
	}
	
	@Override
	public HasClickHandlers getNewAccountAnchor(){
		return registerAnchor;
	}

	@Override
	public String getPassword() {
		return password.getValue();
	}

	@Override
	public HasTapHandlers getAccountAnchorButton() {
		return registerAnchorButton;
	}

}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */