package com.nutrinfomics.geneway.client.home;

import java.util.ArrayList;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.FoodItemProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.shared.SnackProperty;

public class MealsWidget extends AbstractTabBarWidget {
	private HTML mealContentHTML;
	private Label countdown;

	private PlanProxy plan;
	private int snackId;
	private Timer timer;
	
	private NumberFormat format = NumberFormat.getFormat("00");
	private GeneWayConstants constants;
	private CellList<SnackTracker> cellList;

	private ArrayList<SnackWidget> snacksWidgets = new ArrayList<>();
	private int currentSnackWidget = 0;
	
	private WeeklyCycle weeklyCycle;
	
	public MealsWidget(){
		
		snackId = 0;
		plan = ClientFactoryFactory.getClientFactory().getPlan();
		weeklyCycle = ClientFactoryFactory.getClientFactory().getWeeklyCycle();
		
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


		WidgetList widgetList = new SnackWidgetList();
		widgetList.getElement().setAttribute("style", Styles.HORIZONTAL_CENTER_ALIGN + Styles.VERTICAL_CENTER_ALIGN + Styles.BACKGROUND_COLOR +
														"border-top-color:" + Styles.BACKGROUND_COLOR_VALUE + ";");
		widgetList.container.getElement().setAttribute("style", Styles.BACKGROUND_COLOR);
		for(int i = 0; i < plan.getSnackMenu().getSnacks().size(); ++i){
			SnackProxy snack = plan.getSnackMenu().getSnacks().get(i);
			SnackWidget.State state = SnackWidget.State.TO_FOLLOW;
			if(i == currentSnackWidget) state = SnackWidget.State.CURRENT;
			
			SnackWidget snackWidget = (snack.getSnackProperty() == SnackProperty.REST ? new VaryingSnackWidget(snack, state, this) : 
																						new SnackWidget(snack, state, this));
			snacksWidgets.add(snackWidget);
			widgetList.add(snackWidget);
		}
		
		VerticalPanel verticalPanel = new VerticalPanel();
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.getElement().setAttribute("style", Styles.HORIZONTAL_CENTER_ALIGN + Styles.VERTICAL_CENTER_ALIGN + Styles.BACKGROUND_COLOR);
		
		
		verticalPanel.add(widgetList);
		scrollPanel.setWidget(verticalPanel);
		
		add(scrollPanel);
		
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
	

	public void nextMeal(){
		snacksWidgets.get(currentSnackWidget).setState(SnackWidget.State.EATEN);
		currentSnackWidget = (currentSnackWidget + 1) % snacksWidgets.size();
		snacksWidgets.get(currentSnackWidget).setState(SnackWidget.State.CURRENT);
		if(currentSnackWidget == 0){ // one day is over
			weeklyCycle.advanceBySingleUnit();
		}
	}
	
	private void updatePanel(final long timeOffsetInMillies) {
		SnackProxy snack = plan.getSnackMenu().getSnacks().get(snackId);
		
		String nextMeal = "";
		
		for(FoodItemProxy foodItem : snack.getFoodItems()){
			nextMeal += foodItem.getFoodType() + " ";
		}
		
		snackId = (snackId + 1) % plan.getSnackMenu().getSnacks().size();

		mealContentHTML.setHTML("<span style=\"text-align: center\">" + nextMeal +
	    		"</span>");
		countdown.setText(getTimeString((int) timeOffsetInMillies / 1000));
		
		timer = new Timer() {
			private int time = (int) timeOffsetInMillies / 1000;
			@Override
			public void run() {
				time-=1;
				if(time < 1){
					Window.alert(constants.itsTimeToTakeYourMeal());
					cancel();
				}
				
				String timeString = getTimeString(time);
				countdown.setText(timeString);
			}
		};
		timer.scheduleRepeating(1000);
	}

	private String getTimeString(int time) {

		int secs = time % 60;
		int minutes = (time / 60) % 60;
		int hour = time / 3600;

		return format.format(hour) + ":" + format.format(minutes) + ":" + format.format(secs);
	}

}
