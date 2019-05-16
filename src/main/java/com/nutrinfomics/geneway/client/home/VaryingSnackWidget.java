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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.user.client.Random;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.FoodItemProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.shared.FoodCategory;
import com.nutrinfomics.geneway.shared.FoodItemType;

public class VaryingSnackWidget extends SnackWidget  {

	private ArrayList<FoodItemProxy> animalFoodItems = new ArrayList<>();
	private ArrayList<FoodItemProxy> salad = new ArrayList<>();
	private ArrayList<FoodItemProxy> otherFoodItems = new ArrayList<>();
//	private Map<FoodItemProxy, ArbitraryCycle> cycleMap = new HashMap<>();
	
	private FoodItemWidget animalFoodItemWidget;
	private ArrayList<FoodItemWidget> otherFoodItemWidgets = new ArrayList<>();
	
	
	public VaryingSnackWidget(SnackProxy snack, State state, MealsWidget mealsWidget) {
		super(snack, State.CURRENT, mealsWidget);
		
		initVaryingSnackWidgetPanels();
		
		setState(state);
		
//		ClientFactoryFactory.getClientFactory().getWeeklyCycle().addWeeklyBehaving(this);
	}

	private void initVaryingSnackWidgetPanels() {
		foodItemWidgets = new ArrayList<>(snack.getFoodItems().size());
		
		initFoodItemClassification();

		initAnimalFoodItemWidget();
		initSaladFoodItemWidgets();
		initOtherFoodItemWidgets();
		
	}

	@Override
	protected void initFoodItemPanel(){
		
	}


	private void initOtherFoodItemWidgets() {
//		for(FoodItemProxy foodItem : otherFoodItems){
//			ArbitraryCycle arbitraryCycle = cycleMap.get(foodItem);
//			if(arbitraryCycle.getCycleLength() >= 1 && 
//					(arbitraryCycle.getRemainingLength() == ClientFactoryFactory.getClientFactory().getWeeklyCycle().getRemainingLength() ||
//					Random.nextBoolean() )){
//				FoodItemWidget foodItemWidget = new FoodItemWidget(foodItem);
//				otherFoodItemWidgets.add(foodItemWidget);
//				foodItemWidgets.add(foodItemWidget);
//				foodItemPanel.add(foodItemWidget);
//			}
//		}
		
	}

	private void initSaladFoodItemWidgets() {
		for(FoodItemProxy foodItem : salad){
			FoodItemWidget foodItemWidget = new FoodItemWidget(foodItem);
			foodItemWidgets.add(foodItemWidget);
			foodItemPanel.add(foodItemWidget);
		}
		
	}

	private void initAnimalFoodItemWidget() {
//		for(FoodItemProxy foodItem : animalFoodItems){
//			if(cycleMap.get(foodItem).getRemainingLength() >= 1){
//				animalFoodItemWidget = new FoodItemWidget(foodItem);
//				foodItemWidgets.add(animalFoodItemWidget);
//				foodItemPanel.add(animalFoodItemWidget);
//				break;
//			}
//		}
	}

	private void initFoodItemClassification() {
		for(FoodItemProxy foodItem : snack.getFoodItems()){
//			cycleMap.put(foodItem, new ArbitraryCycle(foodItem.getWeeklyDays()));
			
			FoodItemType foodType = foodItem.getFoodType();
			FoodCategory foodCategory = foodType.getFoodCategory();
			
			if(foodCategory == FoodCategory.VEGETABLE_FRUIT && foodType != FoodItemType.ZUCCHINI && foodType != FoodItemType.SQUASH_SUMMER &&
					foodType != FoodItemType.PUMPKIN){
				salad.add(foodItem);
			}
			else if(foodCategory == FoodCategory.VEGETABLE &&
					(foodType == FoodItemType.PARSLEY || foodType == FoodItemType.LETTUCE || foodType == FoodItemType.ARUGULA ||
					foodType == FoodItemType.SPEARMINT || foodType == FoodItemType.ONION_YOUNG_GREEN || foodType == FoodItemType.CABBAGE ||
					foodType == FoodItemType.BROCCOLI)){
				salad.add(foodItem);
			}
			else if(foodType == FoodItemType.LEMON_JUICE){
				salad.add(foodItem);
			}
			else if(foodType == FoodItemType.CARROT){
				salad.add(foodItem);
			}
			else if(foodType == FoodItemType.OLIVE_OIL || foodType == FoodItemType.COCONUT_OIL){
				salad.add(foodItem);
			}
			else if(foodType == FoodItemType.AVOCADO || foodType == FoodItemType.OLIVE || foodType == FoodItemType.CHEESE_FETA){
				salad.add(foodItem);
			}
			else if(foodCategory == FoodCategory.MEAT || foodCategory == FoodCategory.FISH || foodCategory == FoodCategory.SEAFOOD){
				animalFoodItems.add(foodItem);
			}
			else{
				otherFoodItems.add(foodItem);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.nutrinfomics.geneway.client.home.WeeklyBehaving#nextDay()
	 */
//	@Override
	public void nextDay(){
		otherFoodItemWidgets.clear();
		foodItemWidgets.clear();
		foodItemPanel.clear();
		
		initAnimalFoodItemWidget();
		
		initSaladFoodItemWidgets();
		
		initOtherFoodItemWidgets();
	}
	
	/* (non-Javadoc)
	 * @see com.nutrinfomics.geneway.client.home.WeeklyBehaving#weeklyReset()
	 */
//	@Override
	public void weeklyReset(){
		
//		for(FoodItemProxy foodItem : animalFoodItems)
//			cycleMap.get(foodItem).reset();
//		
//		for(FoodItemProxy otherFoodItem : otherFoodItems)
//			cycleMap.get(otherFoodItem).reset();
				
		nextDay();
		
	}
	
	@Override
	public void setState(State state){
		if(this.state == State.CURRENT && state != State.CURRENT){
//			cycleMap.get(animalFoodItemWidget.getFoodItem()).advanceBySingleUnit();;

			for(FoodItemWidget foodItemWidget : otherFoodItemWidgets){
//				cycleMap.get(foodItemWidget.getFoodItem()).advanceBySingleUnit();;
			}
		}
		super.setState(state);
	}
	
	
}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */