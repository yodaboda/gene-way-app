package com.nutrinfomics.geneway.client;

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

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton;
import com.googlecode.mgwt.ui.client.widget.button.image.AboutImageButton;
import com.googlecode.mgwt.ui.client.widget.button.image.PreviousitemImageButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.panel.Panel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Justification;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;
import com.nutrinfomics.geneway.client.about.AboutPlace;
import com.nutrinfomics.geneway.client.event.ActionEvent;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public class DetailsViewImpl implements DetailsView{
		
	protected RootFlexPanel main;
	private PreviousitemImageButton headerBackButton;
	private AboutImageButton aboutButton;
	private HeaderPanel headerPanel;
	protected FlowPanel bodyPanel;
	private ButtonBar buttonBar;

	protected GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();
	static private FlexPanel loadingPanel;
	private Panel firstHeaderPanel;
	static{
		
		loadingPanel = new FlexPanel();
		loadingPanel.setAlignment(Alignment.CENTER);
		loadingPanel.setJustification(Justification.CENTER);
		loadingPanel.addStyleName("loader");
		ProgressIndicator progressIndicator = new ProgressIndicator();
		loadingPanel.add(progressIndicator);
		RootPanel.getBodyElement().insertFirst(loadingPanel.getElement());
	}
	
	public DetailsViewImpl(){
		headerPanel = new HeaderPanel();
		headerPanel.setJustification(Justification.SPACE_BETWEEN);
		headerPanel.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
//		headerPanel.getElement().setAttribute("style", Styles.BACKGROUND_COLOR + "border-bottom: initial; "
//				//taken from element native style attribute
//				+ "-webkit-box-orient: horizontal; flex-direction: row; -webkit-box-align: center; align-items: center;");
		
		firstHeaderPanel = new Panel();
		firstHeaderPanel.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
		firstHeaderPanel.getElement().getStyle().setBorderStyle(BorderStyle.NONE);
		Panel centerPanel = new Panel();
		centerPanel.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
		centerPanel.getElement().getStyle().setBorderStyle(BorderStyle.NONE);
		FlexPanel lastPanel = new FlexPanel();
		lastPanel.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
		lastPanel.setOrientation(Orientation.HORIZONTAL);
		lastPanel.getElement().getStyle().setBorderStyle(BorderStyle.NONE);
		
		headerPanel.add(firstHeaderPanel);
		headerPanel.add(centerPanel);
		headerPanel.add(lastPanel);
		
		if(!MGWT.getOsDetection().isAndroid() && MGWT.getFormFactor().isPhone()) {
			headerBackButton = new PreviousitemImageButton();
			toggleHeaderButton(headerBackButton);
			
			firstHeaderPanel.add(headerBackButton);
		  
			headerBackButton.addTapHandler(new TapHandler() {
				@Override
				public void onTap(TapEvent event) {
					ActionEvent.fire(ClientFactoryFactory.getClientFactory().getEventBus(), ActionEvent.ActionNames.BACK.toString());
				}
			});
			hideBackButton();			
		}
//		headerPanel.add(new FlexSpacer());

		GeneWayImageButton title = new GeneWayImageButton();
		centerPanel.add(title);
		
//		headerPanel.add(new FlexSpacer());

//		if(!MGWT.getOsDetection().isAndroid() && MGWT.getFormFactor().isPhone()) {
//			headerPanel.add(new FixedSpacer());
//		}



		ImageButton logoutButton = new ImageButton(LocalImageHolder.get().logout());
		toggleHeaderButton(logoutButton);
		logoutButton.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				ClientFactoryFactory.getClientFactory().logout();
			}
		});
		lastPanel.add(logoutButton);

		aboutButton = new AboutImageButton();
		toggleHeaderButton(aboutButton);
//		setAttribute("style","background-color: white;");
//	    if (MGWT.getFormFactor().isPhone()) {
	      lastPanel.add(aboutButton);
//	    }
		aboutButton.addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new AboutPlace());
			}
		});

		
		main = new RootFlexPanel();
		main.setAlignment(Alignment.CENTER);
		main.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
//		main.getElement().setAttribute("style", Styles.BACKGROUND_COLOR);
		main.add(headerPanel);
		
		bodyPanel = new FlowPanel();

		main.add(bodyPanel);
		
		buttonBar = new ButtonBar();
		buttonBar.getElement().getStyle().setBorderWidth(0, Unit.PX);
		main.add(buttonBar);
		buttonBar.setVisible(false);
		
		hideLoader();
	}

	protected void toggleHeaderButton(ImageButton imageButton){
		imageButton.setIconActiveColor(Styles.WHITE);
		imageButton.setIconColor(Styles.WHITE);
		imageButton.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
	}
	
	protected void addToFirstHeaderPanel(IsWidget w){
		firstHeaderPanel.add(w);
	}
	
	public void addToFooter(IsWidget w){
		buttonBar.add(w);
	}
	
	public void showFooter(){
		buttonBar.setVisible(true);
	}
	
	public void bodyCenterAlign(){
		bodyPanel.getElement().setAttribute("style", Styles.HORIZONTAL_CENTER_ALIGN + Styles.VERTICAL_CENTER_ALIGN);
	}
	
	@Override
	public void hideBackButton() {
		if(headerBackButton != null) headerBackButton.setVisible(false);
	}
	
	@Override
	public void showBackButton(){
		if(headerBackButton != null &&
				!MGWT.getOsDetection().isAndroid() && 
				MGWT.getFormFactor().isPhone()) 
			headerBackButton.setVisible(true);
	}
	
	@Override
	public void hideAboutButton(){
		aboutButton.setVisible(false);
	}
	
	@Override
	public void showAboutButton(){
		aboutButton.setVisible(true);
	}
	
	public void hideHeaderPanel(){
		headerPanel.setVisible(false);
	}
	
	public void showHeaderPanel(){
		headerPanel.setVisible(true);
	}
	
	protected void add(Widget widget){
		bodyPanel.add(widget);
	}
	protected void remove(Widget widget){
		bodyPanel.remove(widget);
	}
	@Override
	public Widget asWidget() {
		return main;
	}

	protected void addToRoot(Widget w){
		main.remove(bodyPanel);
		main.add(w);
	}

	@Override
	public void displayLoader() {
		loadingPanel.getElement().getStyle().setVisibility(Visibility.VISIBLE);
	};

	@Override
	public void hideLoader() {
		loadingPanel.getElement().getStyle().setVisibility(Visibility.HIDDEN);
	};

}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */