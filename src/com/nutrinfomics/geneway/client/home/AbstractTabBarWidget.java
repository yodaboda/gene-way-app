package com.nutrinfomics.geneway.client.home;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.Panel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Justification;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;

abstract public class AbstractTabBarWidget extends FlexPanel{
	public AbstractTabBarWidget(){
		this.setOrientation(Orientation.VERTICAL);
		this.setJustification(Justification.SPACE_BETWEEN);
	}

}
