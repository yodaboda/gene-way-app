package com.nutrinfomics.geneway.client.home;

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
import com.nutrinfomics.geneway.client.localization.GeneWayConstants;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.FoodItemProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.server.domain.plan.FoodItem;
import com.nutrinfomics.geneway.server.domain.plan.Snack;

public class SnackWidget extends HorizontalPanel{

	public enum State{
		EATEN, CURRENT, TO_FOLLOW
	}
	protected SnackProxy snack;
	protected State state;
	private GeneWayConstants constants;
	private MealsWidget mealsWidget;
	protected ArrayList<FoodItemWidget> foodItemWidgets;
	
	private Label countdown;
	private NumberFormat format = NumberFormat.getFormat("00");
	private Timer timer;

	protected VerticalPanel foodItemPanel = new VerticalPanel();
	private VerticalPanel operationsPanel = new VerticalPanel();
	
	public SnackWidget(SnackProxy snack, State state, MealsWidget mealsWidget){
		this.setSnack(snack);
		this.mealsWidget = mealsWidget;
		
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

	private void initOperationsPanel() {
		ImageButton acceptButton = new AbstractImageButton(new SnackImageButtonAppearance(), ImageHolder.get().accept(), "", Styles.BACKGROUND_COLOR_VALUE,
														Styles.BACKGROUND_COLOR_VALUE, Styles.WHITE) {
		};
//				new ImageButton(new SnackImageButtonAppearance(), ImageHolder.get().accept(), "");
//		acceptButton.image.getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
		
		acceptButton.setIconActiveColor(Styles.BACKGROUND_COLOR_VALUE);
		acceptButton.setIconColor(Styles.BACKGROUND_COLOR_VALUE);
		
	    countdown = new Label();
	    countdown.setStylePrimaryName("timer");
	    countdown.getElement().getStyle().setBackgroundColor(Styles.WHITE);
		
//		acceptButton.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
////		acceptButton.getElement().getStyle().setBorderColor("white");
//		acceptButton.getElement().getStyle().setColor(Styles.BACKGROUND_COLOR_VALUE);
		
	    operationsPanel.setHorizontalAlignment(ALIGN_CENTER);
	    operationsPanel.add(acceptButton);
		operationsPanel.add(countdown);
		
		acceptButton.addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				timer.cancel();
				mealsWidget.getNextSnack();
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
			
			final int timeOffsetInMillies = (int) (1.5 * 60 * 60 * 1000);
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
		else operationsPanel.setVisible(false);
	}

	private String getTimeString(int time) {

		int secs = time % 60;
		int minutes = (time / 60) % 60;
		int hour = time / 3600;

		return format.format(hour) + ":" + format.format(minutes) + ":" + format.format(secs);
	}

	
	private String getDisplayString() {
		String string = "";
		for(FoodItemProxy foodItem : getSnack().getFoodItems()){
			string += ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(foodItem.getFoodType().toString()) + " + ";
		}
		if(!string.isEmpty()) string = string.substring(0, string.length() - 3);
		return string;
	}

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
	
}
