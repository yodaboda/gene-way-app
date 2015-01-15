package com.nutrinfomics.geneway.client.home;

import com.google.gwt.dom.client.Style.Unit;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.constants.GeneWayConstants;

abstract public class AbstractTabBarButton extends TabBarButton {
	
	final protected static GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();

	public AbstractTabBarButton(TabBarButtonData data){
		super(new HomeTabBarAppearance(), data.getUnselecetedImageResource(),
				data.getSelectedImageResource());
		setText(data.getText());
		text.getStyle().setTop(1, Unit.PX);
	}
}
