package com.nutrinfomics.geneway.client.home;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.googlecode.mgwt.ui.client.widget.button.ImageButtonAbstractAppearance;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetListAppearance.WidgetListCss;
import com.nutrinfomics.geneway.client.home.SnackWidgetListAppearance.Resources;
import com.nutrinfomics.geneway.client.home.SnackWidgetListAppearance.SnackWidgetListCss;

public class SnackImageButtonAppearance extends ImageButtonAbstractAppearance {

	static {
		Resources.INSTANCE.css().ensureInjected();
	}

	public interface SnackImageButtonCss extends ImageButtonCss {}

	interface Resources extends ClientBundle {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source({"com/googlecode/mgwt/ui/client/widget/button/imagebutton.css",
			"snackimagebutton.css", })
		SnackImageButtonCss css();
	}


	@Override
	public SnackImageButtonCss css() {
		return Resources.INSTANCE.css();
	}

}
