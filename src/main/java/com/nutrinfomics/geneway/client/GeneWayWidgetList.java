package com.nutrinfomics.geneway.client;

import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetListAppearance;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetListDefaultAppearance;
import com.nutrinfomics.geneway.client.style.Styles;

public class GeneWayWidgetList extends WidgetList {
	public GeneWayWidgetList(WidgetListAppearance widgetListAppearance){
		super(widgetListAppearance);

//		getElement().getStyle().setBackgroundColor(Styles.WHITE);
//		getElement().getStyle().setVerticalAlign(VerticalAlign.MIDDLE);
//		container.getElement().getStyle().setBackgroundColor(Styles.WHITE);

		getElement().setAttribute("style", Styles.HORIZONTAL_CENTER_ALIGN + Styles.VERTICAL_CENTER_ALIGN + Styles.WHITE_BACKGROUND_COLOR +
				"border-top: none");
		container.getElement().setAttribute("style", Styles.WHITE_BACKGROUND_COLOR);
	}
	
	public GeneWayWidgetList(){
		this(new WidgetListDefaultAppearance());
	}
}
