package com.nutrinfomics.geneway.client.home;

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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton;
import com.googlecode.mgwt.ui.client.widget.button.ImageButtonAppearance;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton.TextPosition;
import com.googlecode.mgwt.ui.client.widget.button.ImageButtonAppearance.ImageButtonCss;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.panel.Panel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Justification;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.nutrinfomics.geneway.client.ClientData;
import com.nutrinfomics.geneway.client.GeneWayImageButtonAppearance;
import com.nutrinfomics.geneway.client.ClientData.NextSnackListener;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.ProgressChartPanel;
import com.nutrinfomics.geneway.client.home.SnackWidget.State;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder;
import com.nutrinfomics.geneway.client.ingredients.IngredientsPlace;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackHistoryProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.EntityBaseRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.client.util.DateUtils;

public class MealsWidget extends AbstractTabBarWidget implements NextSnackListener{
//	private HTML mealContentHTML;
//	private Label countdown;
//
//	private Timer timer;
//	
//	private NumberFormat format = NumberFormat.getFormat("00");
//	private GeneWayConstants constants;
//	private CellList<SnackTracker> cellList;
//
//	private ArrayList<SnackWidget> snacksWidgets = new ArrayList<>();
//	private int currentSnackWidget = 0;
	
	private SnackProxy snackProxy = null;
	
	private SnackWidget snackWidget;
	
	private ImageButton ingredientsButton;
	
	private Panel snackPanel;
	private Panel actionPanel;
	
	@Override
	public void nextSnack(SnackProxy snackProxy, boolean snackForTomorrow) {
		this.snackProxy = snackProxy;
		if(snackWidget != null) snackPanel.remove(snackWidget);
		snackWidget = new SnackWidget(snackProxy, State.CURRENT, MealsWidget.this);
		if(snackForTomorrow){
			Date currentTime = new Date();
			Date startingTime = CalendarUtil.copyDate(currentTime);
			CalendarUtil.addDaysToDate(startingTime, 1);
			startingTime.setHours(8);
			startingTime.setMinutes(0);
			startingTime.setSeconds(0);
			
			snackWidget.setHoursInterval( (startingTime.getTime() - currentTime.getTime()) * 1.0 / (1000 * 60 * 60) );
		}
		MealsWidget.this.snackProxy = snackProxy;
		snackPanel.add(snackWidget);
		
	}

	
	void switchToNextSnack() {
		ClientFactoryFactory.getClientFactory().getClientData().persistCurrentSnack(snackProxy, snackWidget.getSnackStatus(), this);
	}
	
	public MealsWidget(){
		
		snackPanel = new Panel();
		snackPanel.setRound(true);
		
		actionPanel = new Panel();
		actionPanel.setRound(true);
		actionPanel.getElement().getStyle().setProperty("alignSelf", "flex-end");
		actionPanel.getElement().getStyle().setProperty("WebkitAlignSelf", "flex-end");

		FlexPanel updateSharingPanel = new FlexPanel();
		updateSharingPanel.setOrientation(Orientation.HORIZONTAL);
		updateSharingPanel.setJustification(Justification.CENTER);
		updateSharingPanel.setAlignment(Alignment.CENTER);
		String milestone = "Congratulations for losing " + (Random.nextInt(5) + 1) + "kg in one week!";
		HTML congratulationHTML = new HTML(milestone);
		congratulationHTML.getElement().getStyle().setColor(Styles.GREEN_DARK);
		congratulationHTML.getElement().getStyle().setPaddingTop(5, Unit.PT);
//		congratulationHTML.getElement().getStyle().setProperty("alignSelf", "center");
//		congratulationHTML.getElement().getStyle().setProperty("WebkitAlignSelf", "center");
		updateSharingPanel.add(congratulationHTML);

		
//	  	HTML faceSharing = new HTML("<div id=\"fb-root\"></div>"
//	  			+ "<script>(function(d, s, id) {"
//	  			+ "var js, fjs = d.getElementsByTagName(s)[0];"
//	  			+ "if (d.getElementById(id)) return;"
//	  			+ "js = d.createElement(s); js.id = id;"
//	  			+ "js.src = \"//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.3&appId=598977940112238\";"
//	  			+ "fjs.parentNode.insertBefore(js, fjs);"
//	  			+ "}(document, 'script', 'facebook-jssdk'));</script>");
//
//		RootPanel.getBodyElement().insertFirst(faceSharing.getElement());
//		HTML facebookShare = new HTML("<div class=\"fb-share-button\" data-href=\"https://developers.facebook.com/docs/plugins/\" data-layout=\"button\"></div>");

		HTML facebookShare = new HTML("<a href=\"http://www.facebook.com/sharer.php?u=http://www.gene-way.com\" target=\"_blank\"><img class=\"shareImg\" width=\"30\" style=\"padding:5px;\" src=\"http://www.simplesharebuttons.com/images/somacro/facebook.png\" alt=\"Facebook\" /></a>"
				+ "<a href=\"http://twitter.com/share?url=http://www.gene-way.com&text=" + milestone + "&hashtags=gene-way\" target=\"_blank\"><img class=\"shareImg\" width=\"30\" style=\"padding:5px;\" src=\"http://www.simplesharebuttons.com/images/somacro/twitter.png\" alt=\"Twitter\" /></a> "
				+ "<a href=\"https://plus.google.com/share?url=http://www.gene-way.com\" target=\"_blank\"><img class=\"shareImg\" width=\"30\" style=\"padding:5px;\" src=\"http://www.simplesharebuttons.com/images/somacro/google.png\" alt=\"Google\" /></a>");
 
//<!-- Google+ -->
// ");
		updateSharingPanel.add(facebookShare);
		
		add(updateSharingPanel);
		
		add(snackPanel);
//		add(ProgressChartPanel.getInstance());
		add(actionPanel);
		
		ClientData clientData = ClientFactoryFactory.getClientFactory().getClientData();
		nextSnack(clientData.getNextSnack(), clientData.isSnackForTomorrow());
		
		
		ingredientsButton = new ImageButton(new GeneWayImageButtonAppearance(), LocalImageHolder.get().ingredients(), "");
		ingredientsButton.addTapHandler(new TapHandler() {		
			@Override
			public void onTap(TapEvent event) {
				ClientFactoryFactory.getClientFactory().getPlaceController().goTo(new IngredientsPlace());
			}
		});
		ingredientsButton.setText(ClientFactoryFactory.getClientFactory().getConstants().ingredients());

//		ingredientsButton.getElement().getStyle().setBackgroundColor(Styles.WHITE);
		//		ingredientsButton.setTextPosition(TextPosition.LEFT);
		actionPanel.add(ingredientsButton);
//		snackId = 0;
//		plan = ClientFactoryFactory.getClientFactory().getPlan();
//		weeklyCycle = ClientFactoryFactory.getClientFactory().getWeeklyCycle();
		
//		cellList = new CellList<>(new SnackTrackerCell());
//		add(cellList);
//
//		getList().addCellSelectedHandler(new CellSelectedHandler() {
//			@Override
//			public void onCellSelected(CellSelectedEvent event) {
//				Window.alert("selected cell: " + event.getIndex());
//			}
//		});
//		
//		cellList.render( convetToSnackTrackerList(plan.getSnackMenu().getSnacks()) );

//		Button nextMeal = new Button(constants.nextMeal());
//		nextMeal.setRound(true);
//		add(nextMeal);


//		WidgetList widgetList = new SnackWidgetList();
////		widgetList.getElement().setAttribute("style", Styles.HORIZONTAL_CENTER_ALIGN + Styles.VERTICAL_CENTER_ALIGN + Styles.BACKGROUND_COLOR +
////														"border-top-color:" + Styles.BACKGROUND_COLOR_VALUE + ";");
////		widgetList.container.getElement().setAttribute("style", Styles.BACKGROUND_COLOR);
//		for(int i = 0; i < plan.getSnackMenu().getSnacks().size(); ++i){
//			SnackProxy snack = plan.getSnackMenu().getSnacks().get(i);
//			SnackWidget.State state = SnackWidget.State.TO_FOLLOW;
//			if(i == currentSnackWidget) state = SnackWidget.State.CURRENT;
//			
//			SnackWidget snackWidget = (snack.getSnackProperty() == SnackProperty.REST ? new VaryingSnackWidget(snack, state, this) : 
//																						new SnackWidget(snack, state, this));
//			snacksWidgets.add(snackWidget);
//			widgetList.add(snackWidget);
//		}
		
//		VerticalPanel verticalPanel = new VerticalPanel();
//		ScrollPanel scrollPanel = new ScrollPanel();
//		scrollPanel.getElement().setAttribute("style", Styles.HORIZONTAL_CENTER_ALIGN + Styles.VERTICAL_CENTER_ALIGN + Styles.BACKGROUND_COLOR);
//		
//		
//		verticalPanel.add(widgetList);
//		scrollPanel.setWidget(verticalPanel);
		
//		add(scrollPanel);
		
//		scrollPanel.setWidget(widgetList);
//		scrollPanel.setHeight("2000px");
		
//		scrollPanel.setScrollLock(false);

//		render();
//	    WidgetList widgetList = new WidgetList();
//	    constants = ClientFactoryFactory.getClientFactory().getConstants();
//		widgetList.setHeader(new Label(constants.nextMeal()));
//
////	    flexPanel = new FlexPanel();
//
//	    mealContentHTML = new HTML();
////	    flexPanel.add(mealContentHTML);
//	    countdown = new Label();
//	    countdown.setStylePrimaryName("timer");
////	    flexPanel.add(countdown);
//
////	    widgetList.add(flexPanel);
//
//	    widgetList.add(mealContentHTML);
//	    widgetList.add(countdown);
//	    
//	    Button mealTakenButton = new Button(constants.mealTaken());
//	    mealTakenButton.addTapHandler(new TapHandler() {
//			
//			@Override
//			public void onTap(TapEvent event) {
//				if(timer != null) timer.cancel();
//				if(snackId == 0){ //took last meal for the day
//					Date currentTime = new Date();
//					Date startingTime = CalendarUtil.copyDate(currentTime);
//					CalendarUtil.addDaysToDate(startingTime, 1);
//					startingTime.setHours(8);
//					startingTime.setMinutes(0);
//					startingTime.setSeconds(0);
//					updatePanel(startingTime.getTime() - currentTime.getTime());
//				}
//				else updatePanel((long) (60*60*1.5*1000));
//			}
//		});
//	    
//	    widgetList.add(mealTakenButton);
	
//	    widgetList.setSize("300px", "200px");
	    
//	    flexPanel.add(mealTakenButton);
//	    add(widgetList);
	    
//	    add(mealContentHTML);
//	    add(countdown);
//	    add(mealTakenButton);
	    
//	    add(flexPanel);
	    
//	    setRound(true);
//	    updatePanel((long) (60*60*1.5*1000)); //1.5 hours
	}
	

//	public void nextMeal(){
//		snacksWidgets.get(currentSnackWidget).setState(SnackWidget.State.EATEN);
//		currentSnackWidget = (currentSnackWidget + 1) % snacksWidgets.size();
//		snacksWidgets.get(currentSnackWidget).setState(SnackWidget.State.CURRENT);
//		if(currentSnackWidget == 0){ // one day is over
//			weeklyCycle.advanceBySingleUnit();
//		}
//	}
//	
//	private void updatePanel(final long timeOffsetInMillies) {
//		SnackProxy snack = plan.getSnackMenu().getSnacks().get(snackId);
//		
//		String nextMeal = "";
//		
//		for(FoodItemProxy foodItem : snack.getFoodItems()){
//			nextMeal += foodItem.getFoodType() + " ";
//		}
//		
//		snackId = (snackId + 1) % plan.getSnackMenu().getSnacks().size();
//
//		mealContentHTML.setHTML("<span style=\"text-align: center\">" + nextMeal +
//	    		"</span>");
//		countdown.setText(getTimeString((int) timeOffsetInMillies / 1000));
//		
//		timer = new Timer() {
//			private int time = (int) timeOffsetInMillies / 1000;
//			@Override
//			public void run() {
//				time-=1;
//				if(time < 1){
//					Window.alert(constants.itsTimeToTakeYourMeal());
//					cancel();
//				}
//				
//				String timeString = getTimeString(time);
//				countdown.setText(timeString);
//			}
//		};
//		timer.scheduleRepeating(1000);
//	}

//	private String getTimeString(int time) {
//
//		int secs = time % 60;
//		int minutes = (time / 60) % 60;
//		int hour = time / 3600;
//
//		return format.format(hour) + ":" + format.format(minutes) + ":" + format.format(secs);
//	}

}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */