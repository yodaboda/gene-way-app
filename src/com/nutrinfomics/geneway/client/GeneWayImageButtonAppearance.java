package com.nutrinfomics.geneway.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.googlecode.mgwt.ui.client.widget.button.ImageButtonAbstractAppearance;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetListAppearance.WidgetListCss;

public class GeneWayImageButtonAppearance extends ImageButtonAbstractAppearance {

	static {
		Resources.INSTANCE.css().ensureInjected();
	}

	public interface GeneWayImageButtonCss extends ImageButtonCss {}

	interface Resources extends ClientBundle {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source({"com/googlecode/mgwt/ui/client/widget/button/imagebutton.css",
			"genewayimagebutton.css", })
		GeneWayImageButtonCss css();
	}


	@Override
	public GeneWayImageButtonCss css() {
		return Resources.INSTANCE.css();
	}

}
