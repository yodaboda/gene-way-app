package com.nutrinfomics.geneway.client.home;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetListAbstractAppearance;

public class SnackWidgetListAppearance extends WidgetListAbstractAppearance {

	 static {
		    Resources.INSTANCE.css().ensureInjected();
		  }

		  public interface SnackWidgetListCss extends WidgetListCss {}

		  interface Resources extends ClientBundle {
		    Resources INSTANCE = GWT.create(Resources.class);

		    @Source({"com/googlecode/mgwt/ui/client/widget/list/widgetlist/widgetlist.css",
		            "snackwidgetlist.css", })
		      SnackWidgetListCss css();
		  }

		  @Override
		  public SnackWidgetListCss css() {
		    return Resources.INSTANCE.css();
		  }

}
