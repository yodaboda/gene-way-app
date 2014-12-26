package com.nutrinfomics.geneway.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.recognizer.longtap.LongTapEvent;
import com.googlecode.mgwt.dom.client.recognizer.longtap.LongTapHandler;
import com.googlecode.mgwt.ui.client.util.IconHandler;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.nutrinfomics.geneway.client.AbstractImageButton;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.FoodItemProxy;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.server.domain.plan.FoodItem;
import com.sun.java.swing.plaf.windows.WindowsBorders;

public class FoodItemWidget extends AbstractImageButton {

	
//	@UiField
//	Element icon;
//	@UiField
//	Element amount;

//	private ImageButton imageButton;
	private FoodItemProxy foodItem;

	private NumberFormat fmt = NumberFormat.getDecimalFormat();
	
	private static final String ACTIVE_COLOR = Styles.GREEN;
	private static final String INACTIVE_COLOR = Styles.GRAY;
	
//	interface FoodItemWidgetUiBinder extends UiBinder<Widget, FoodItemWidget> {}
//	private static FoodItemWidgetUiBinder uiBinder = GWT.create(FoodItemWidgetUiBinder.class);

	public FoodItemWidget(FoodItemProxy foodItem){
		this(foodItem, false);
	}
	public FoodItemWidget(FoodItemProxy foodItem, boolean active){
//		initWidget(uiBinder.createAndBindUi(this));

		super(new SnackImageButtonAppearance(), LocalImageHolder.get(foodItem.getFoodType()),"", INACTIVE_COLOR, ACTIVE_COLOR, Styles.WHITE);
		this.foodItem = foodItem;
		
		double paddingVal = 3;
		this.text.getStyle().setPaddingLeft(paddingVal, Unit.PX);
		this.text.getStyle().setPaddingRight(paddingVal, Unit.PT);
		
//		getElement().getStyle().setBackgroundColor("transparent");
//		getElement().getStyle().setBorderColor("transparent");
//		getElement().getStyle().setBorderStyle(BorderStyle.NONE);
//		imageButton = new ImageButton(new SnackImageButtonAppearance(), LocalImageHolder.get(this.foodItem.getFoodType()),"");
//		add(imageButton);

		addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				Dialogs.alert(ClientFactoryFactory.getClientFactory().getConstants().foodItem(), ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(FoodItemWidget.this.foodItem.getFoodType().toString()), null);
			}
		});
//		addLongTapHandler(new LongTapHandler() {
//			@Override
//			public void onLongTap(LongTapEvent event) {
//				Dialogs.alert(ClientFactoryFactory.getClientFactory().getConstants().foodItem(), ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(FoodItemWidget.this.foodItem.getFoodType().toString()), null);
//			}
//		});
		setActive(active);
	}
	public void setActive(boolean active){
		String state = active ? ACTIVE_COLOR : INACTIVE_COLOR;

//		IconHandler.setIcons(icon, LocalImageHolder.get(this.foodItem.getFoodType()), state);
		
		if(!active){
//			amount.setInnerText("");
//			imageButton.getElement().getStyle().setColor(ACTIVE_COLOR);
//			imageButton.setIconColor(INACTIVE_COLOR);
//			imageButton.setText("");
//			imageButton.setTitle("");
			setIconColor(INACTIVE_COLOR);
			setText("");
			setTitle("");

		}
		else{
//			amount.setInnerText("x " + this.foodItem.getAmount() + " " + this.foodItem.getMeasurementUnit());
//			amount.getStyle().setColor(ACTIVE_COLOR);
//			imageButton.getElement().getStyle().setColor(ACTIVE_COLOR);
//			imageButton.setIconColor(ACTIVE_COLOR);
//			imageButton.setText("x " + this.foodItem.getAmount() + " " + this.foodItem.getMeasurementUnit());
//			imageButton.setTitle(ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(this.foodItem.getFoodType().toString()));
			//ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(this.foodItem.getFoodType().toString())
			getElement().getStyle().setColor(ACTIVE_COLOR);
			setIconColor(ACTIVE_COLOR);
			setText(ClientFactoryFactory.getClientFactory().getMessages().foodAmount(this.foodItem.getAmount(), 
																					ClientFactoryFactory.getClientFactory().getMeasurementsConstants().getString(this.foodItem.getMeasurementUnit().toString())));
//			setText(" " + fmt.format(this.foodItem.getAmount()) + " " + ClientFactoryFactory.getClientFactory().getMeasurementsConstants().getString(this.foodItem.getMeasurementUnit().toString()));
			setTitle(ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(this.foodItem.getFoodType().toString()));
		}
	}
	
	public FoodItemProxy getFoodItem(){
		return foodItem;
	}
	
	public void setFoodItem(FoodItemProxy foodItem){
		this.foodItem = foodItem;
	}
}
