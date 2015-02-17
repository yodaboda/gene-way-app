package com.nutrinfomics.geneway.client.ingredients;

import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.nutrinfomics.geneway.client.DetailsViewImpl;

public class IngredientsViewImpl extends DetailsViewImpl implements
		IngredientsView{
	public IngredientsViewImpl(){
		showAboutButton();
		
		showHeaderPanel();
		showBackButton();
		
		ScrollPanel scrollPanel = new ScrollPanel();
		IngredientsWidgetList widgetList = new IngredientsWidgetList();
		scrollPanel.setWidget(widgetList);
		scrollPanel.setScrollLock(false);
		addToRoot(scrollPanel);
	}
}
