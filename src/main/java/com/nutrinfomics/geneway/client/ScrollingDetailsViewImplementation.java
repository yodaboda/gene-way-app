package com.nutrinfomics.geneway.client;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public class ScrollingDetailsViewImplementation extends DetailsViewImpl {
	private ScrollPanel scrollPanel;

	public ScrollingDetailsViewImplementation(){
		
		showAboutButton();
		
		showHeaderPanel();
		showBackButton();
		
		scrollPanel = new ScrollPanel();
		
		scrollPanel.setScrollLock(false);
		addToRoot(scrollPanel);
	}
	
	public void setScrollWidget(IsWidget widget){
		scrollPanel.setWidget(widget);
	}
}
