package com.nutrinfomics.geneway.client.home.settingsPanel;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetListAbstractAppearance;

public class SettingsWidgetListAppearance extends WidgetListAbstractAppearance {

	static {
		Resources.INSTANCE.css().ensureInjected();
	}

	public interface SettingsWidgetListCss extends WidgetListCss {}

	interface Resources extends ClientBundle {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source({"com/googlecode/mgwt/ui/client/widget/list/widgetlist/widgetlist.css",
			"settingswidgetlist.css", })
		SettingsWidgetListCss css();
	}

	@Override
	public SettingsWidgetListCss css() {
		return Resources.INSTANCE.css();
	}
}
