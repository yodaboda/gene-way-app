package com.nutrinfomics.geneway.client.home;

import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.nutrinfomics.geneway.client.GeneWayWidgetList;

public class SnackWidgetList extends GeneWayWidgetList {

	public SnackWidgetList(){
		super(new SnackWidgetListAppearance());
	}
	@Override
	public void add(Widget w) {
		super.add(w);
		//hacking WidgetListEntry to force it take customized CSS into consideration.
		setSelectAble(getWidgetCount() - 1, true);

	}

}
