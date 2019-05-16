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


public class FoodItemWidget extends AbstractImageButton {

	
//	@UiField
//	Element icon;
//	@UiField
//	Element amount;

//	private ImageButton imageButton;
	private FoodItemProxy foodItem;

	private NumberFormat fmt = NumberFormat.getDecimalFormat();
	
	private static final String ACTIVE_COLOR = Styles.GREEN_DARK;
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

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */