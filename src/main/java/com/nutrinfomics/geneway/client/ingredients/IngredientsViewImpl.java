package com.nutrinfomics.geneway.client.ingredients;

import com.nutrinfomics.geneway.client.ScrollingDetailsViewImplementation;

public class IngredientsViewImpl extends ScrollingDetailsViewImplementation implements
		IngredientsView{
	public IngredientsViewImpl(){		
		IngredientsWidgetList widgetList = new IngredientsWidgetList();
		setScrollWidget(widgetList);
	}
}
