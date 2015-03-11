package com.nutrinfomics.geneway.client.home;

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
