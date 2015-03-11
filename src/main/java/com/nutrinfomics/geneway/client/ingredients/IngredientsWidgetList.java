package com.nutrinfomics.geneway.client.ingredients;

import java.util.Set;

import com.google.gwt.user.client.ui.Widget;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.GeneWayWidgetList;
import com.nutrinfomics.geneway.shared.FoodItemType;

public class IngredientsWidgetList extends GeneWayWidgetList {
	public IngredientsWidgetList(){
		Set<FoodItemType> ingredients = ClientFactoryFactory.getClientFactory().getClientData().getIngredients();
		for(FoodItemType ingredient : ingredients){
			add(new IngredientWidget(ingredient));
		}
	}
	
	@Override
	public void add(Widget w) {
		super.add(w);
		//hacking WidgetListEntry to force it take customized CSS into consideration.
		setSelectAble(getWidgetCount() - 1, true);

	}
}
