package com.nutrinfomics.geneway.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton;
import com.googlecode.mgwt.ui.client.widget.button.image.AboutImageButton;
import com.googlecode.mgwt.ui.client.widget.button.image.PreviousitemImageButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.nutrinfomics.geneway.client.about.AboutPlace;
import com.nutrinfomics.geneway.client.event.ActionEvent;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenPlace;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder;
import com.nutrinfomics.geneway.client.style.Styles;

public class DetailsViewImpl implements DetailsView{
		
	protected RootFlexPanel main;
	private PreviousitemImageButton headerBackButton;
	private AboutImageButton aboutButton;
	private HeaderPanel headerPanel;
	protected FlowPanel bodyPanel;
	private ButtonBar buttonBar;

	public DetailsViewImpl(){
		headerPanel = new HeaderPanel();
		headerPanel.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
//		headerPanel.getElement().setAttribute("style", Styles.BACKGROUND_COLOR + "border-bottom: initial; "
//				//taken from element native style attribute
//				+ "-webkit-box-orient: horizontal; flex-direction: row; -webkit-box-align: center; align-items: center;");
		
		if(!MGWT.getOsDetection().isAndroid() && MGWT.getFormFactor().isPhone()) {
			headerBackButton = new PreviousitemImageButton();
			headerBackButton.setIconActiveColor(Styles.WHITE);
			headerBackButton.setIconColor(Styles.WHITE);

			headerPanel.add(headerBackButton);
		  
			headerBackButton.addTapHandler(new TapHandler() {
				@Override
				public void onTap(TapEvent event) {
					ActionEvent.fire(ClientFactoryFactory.getClientFactory().getEventBus(), ActionEvent.ActionNames.BACK.toString());
				}
			});
			hideBackButton();			
		}
		headerPanel.add(new FlexSpacer());

		GeneWayImageButton title = new GeneWayImageButton();
		headerPanel.add(title);
		
		headerPanel.add(new FlexSpacer());

//		if(!MGWT.getOsDetection().isAndroid() && MGWT.getFormFactor().isPhone()) {
//			headerPanel.add(new FixedSpacer());
//		}



		ImageButton logoutButton = new ImageButton(LocalImageHolder.get().logout());
		logoutButton.setIconActiveColor(Styles.WHITE);
		logoutButton.setIconColor(Styles.WHITE);

		logoutButton.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				ClientFactoryFactory.getClientFactory().setSID(null);
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new FirstScreenPlace());
			}
		});
		headerPanel.add(logoutButton);

		aboutButton = new AboutImageButton();
		aboutButton.setIconActiveColor(Styles.WHITE);
		aboutButton.setIconColor(Styles.WHITE);
//		aboutButton.image.getStyle().setBackgroundColor("white");
//		setAttribute("style","background-color: white;");
//		aboutButton.setStyleName("rightAligned");
//	    if (MGWT.getFormFactor().isPhone()) {
	      headerPanel.add(aboutButton);
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
}
