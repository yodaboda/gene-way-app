package com.nutrinfomics.geneway.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.googlecode.mgwt.ui.client.widget.button.ImageButtonAbstractAppearance;

public class BlinkImageButtonAbstractAppearance extends
		ImageButtonAbstractAppearance {
	static {
		Resources.INSTANCE.css().ensureInjected();
	}

	public interface BlinkImageButtonCss extends ImageButtonCss {}

	interface Resources extends ClientBundle {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source({"com/googlecode/mgwt/ui/client/widget/button/imagebutton.css",
			"blinkimagebutton.css", })
		BlinkImageButtonCss css();
	}


	@Override
	public BlinkImageButtonCss css() {
		return Resources.INSTANCE.css();
	}

}
