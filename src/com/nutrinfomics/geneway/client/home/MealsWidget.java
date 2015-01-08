package com.nutrinfomics.geneway.client.home;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.nutrinfomics.geneway.client.ClientData;
import com.nutrinfomics.geneway.client.ClientData.NextSnackListener;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.home.SnackWidget.State;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackHistoryProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.EntityBaseRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
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
	
	@Override
	public void nextSnack(SnackProxy snackProxy, boolean snackForTomorrow) {
		this.snackProxy = snackProxy;
		if(snackWidget != null) this.remove(snackWidget);
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
		add(snackWidget);
		
	}

	
	void switchToNextSnack() {
		ClientFactoryFactory.getClientFactory().getClientData().persistCurrentSnack(snackProxy, snackWidget.getSnackStatus(), this);
	}
	
	public MealsWidget(){
		ClientData clientData = ClientFactoryFactory.getClientFactory().getClientData();
		nextSnack(clientData.getNextSnack(), clientData.isSnackForTomorrow());
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
