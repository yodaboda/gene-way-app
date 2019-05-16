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

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.util.IconHandler;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton;
import com.googlecode.mgwt.ui.client.widget.button.image.AboutImageButton;
import com.googlecode.mgwt.ui.client.widget.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.image.ImageHolder;
import com.googlecode.mgwt.ui.client.widget.panel.Panel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.AbstractImageButton;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.ClientFactoryImpl;
import com.nutrinfomics.geneway.client.home.SnackTracker.State;
import com.nutrinfomics.geneway.client.home.SnackTrackerCell.Template;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.FoodItemProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.server.domain.plan.FoodItem;
import com.nutrinfomics.geneway.server.domain.plan.Snack;
import com.nutrinfomics.geneway.shared.SnackStatus;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public class SnackWidget extends HorizontalPanel{

	public enum State{
		EATEN, CURRENT, TO_FOLLOW
	}
	protected SnackProxy snack;
	protected State state;
	private SnackStatus snackStatus = SnackStatus.UNKNOWN;
	private GeneWayConstants constants;
	private MealsWidget mealsWidget;
	protected ArrayList<FoodItemWidget> foodItemWidgets;
	
	private Label countdown;
	private NumberFormat format = NumberFormat.getFormat("00");
	private Timer timer;

	protected VerticalPanel foodItemPanel = new VerticalPanel();
	private VerticalPanel operationsPanel = new VerticalPanel();
	private double hoursInterval = ClientFactoryFactory.getClientFactory().getClientData().getPlanPreferences().getSnackTimes().getTimeBetweenSnacks();
	
	public SnackWidget(SnackProxy snack, State state, MealsWidget mealsWidget){
		this.setSnack(snack);
		this.mealsWidget = mealsWidget;
		
		constants = ClientFactoryFactory.getClientFactory().getConstants();
		
		getElement().getStyle().setBackgroundColor(Styles.WHITE);
		
		
		add(foodItemPanel);
		foodItemPanel.getElement().getStyle().setProperty("width", "100%");
		add(new FixedSpacer());
		add(new FlexSpacer());
		add(operationsPanel);
		operationsPanel.getElement().getStyle().setProperty("width", "100%");
		
		
		initFoodItemPanel();
		initOperationsPanel();
		
		this.setState(state);
		
	}

	public double getHoursInterval(){
		return hoursInterval;
	}
	
	public void setHoursInterval(double hoursInterval){
		this.hoursInterval = hoursInterval;
		updateContent();
	}
	
	private void initOperationsPanel() {
		ImageButton acceptButton = new AbstractImageButton(new SnackImageButtonAppearance(), ImageHolder.get().accept(), "", Styles.BACKGROUND_COLOR_VALUE,
														Styles.BACKGROUND_COLOR_VALUE, Styles.WHITE) {
		};
		
		ImageButton skipButton = new AbstractImageButton(new SnackImageButtonAppearance(), ImageHolder.get().cancel(), "", Styles.BACKGROUND_COLOR_VALUE,
				Styles.BACKGROUND_COLOR_VALUE, Styles.WHITE) {
		};
	
//		ImageButton likeButton = new AbstractImageButton(new SnackImageButtonAppearance(), ImageHolder.get().good(), "", Styles.WHITE,
//				Styles.BACKGROUND_COLOR_VALUE, Styles.WHITE) {
//		};

		
//				new ImageButton(new SnackImageButtonAppearance(), ImageHolder.get().accept(), "");
//		acceptButton.image.getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
		
//		acceptButton.setIconActiveColor(Styles.BACKGROUND_COLOR_VALUE);
//		acceptButton.setIconColor(Styles.BACKGROUND_COLOR_VALUE);
		
//	    countdown = new Label();
//	    countdown.setStylePrimaryName("timer");
//	    countdown.getElement().getStyle().setBackgroundColor(Styles.WHITE);
		
//		acceptButton.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
////		acceptButton.getElement().getStyle().setBorderColor("white");
//		acceptButton.getElement().getStyle().setColor(Styles.BACKGROUND_COLOR_VALUE);
		
	    operationsPanel.setHorizontalAlignment(ALIGN_CENTER);
	    operationsPanel.setVerticalAlignment(ALIGN_MIDDLE);
	    
	    HorizontalPanel buttonsPanel = new HorizontalPanel();
	    buttonsPanel.add(acceptButton);
	    buttonsPanel.add(skipButton);
//	    buttonsPanel.add(likeButton);
	    operationsPanel.add(buttonsPanel);

//	    operationsPanel.add(countdown);
		
		acceptButton.addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				nextSnack(SnackStatus.CONSUMED);
			}
		});
		
		skipButton.addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				nextSnack(SnackStatus.SKIPPED);
			}
		});
	}

	protected void initFoodItemPanel() {
		foodItemWidgets = new ArrayList<>(snack.getFoodItems().size());

		for(FoodItemProxy foodItem : snack.getFoodItems()){
			FoodItemWidget foodItemWidget = new FoodItemWidget(foodItem);
			foodItemWidgets.add(foodItemWidget);
			foodItemPanel.add(foodItemWidget);
		}
	}

	private void updateContent() {
		if(foodItemWidgets == null) return;
		for(FoodItemWidget widget : foodItemWidgets){
			widget.setActive(state == State.CURRENT);
		}
		if(state == State.CURRENT){
			operationsPanel.setVisible(true);
			
//			final int timeOffsetInMillies = (int) (hoursInterval * 60 * 60 * 1000);
//			countdown.setText(getTimeString((int) timeOffsetInMillies / 1000));
			
//			if(timer != null && timer.isRunning()){
//				timer.cancel();
//			}
//			timer = new Timer() {
//				private int time = (int) timeOffsetInMillies / 1000;
//				@Override
//				public void run() {
//					time-=1;
//					if(time < 1){
//						cancel();
//						Dialogs.confirm(constants.itsTimeToTakeYourMealTitle(), constants.itsTimeToTakeYourMeal(), new ConfirmCallback() {
//							@Override
//							public void onOk() {
//								nextSnack(SnackStatus.CONSUMED);
//							}
//							@Override
//							public void onCancel() {
//								nextSnack(SnackStatus.SKIPPED);
//							}
//						});
//					}
//					
//					String timeString = getTimeString(time);
//					countdown.setText(timeString);
//				}
//			};
//			timer.scheduleRepeating(1000);

		}
		else operationsPanel.setVisible(false);
	}

	private String getTimeString(int time) {

		int secs = time % 60;
		int minutes = (time / 60) % 60;
		int hour = time / 3600;

		return format.format(hour) + ":" + format.format(minutes) + ":" + format.format(secs);
	}

	
//	private String getDisplayString() {
//		String string = "";
//		for(FoodItemProxy foodItem : getSnack().getFoodItems()){
//			string += ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(foodItem.getFoodType().toString()) + " + ";
//		}
//		if(!string.isEmpty()) string = string.substring(0, string.length() - 3);
//		return string;
//	}

	public SnackProxy getSnack() {
		return snack;
	}

	public void setSnack(SnackProxy snack) {
		this.snack = snack;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
		updateContent();
	}

	public SnackStatus getSnackStatus() {
		return snackStatus;
	}

	public void setSnackStatus(SnackStatus snackStatus) {
		this.snackStatus = snackStatus;
	}

	public void nextSnack(SnackStatus snackStatus) {
		setSnackStatus(snackStatus);
//		timer.cancel();
		mealsWidget.switchToNextSnack();
	}
	
}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */