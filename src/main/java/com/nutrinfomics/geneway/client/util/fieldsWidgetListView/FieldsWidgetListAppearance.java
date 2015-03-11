package com.nutrinfomics.geneway.client.util.fieldsWidgetListView;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetListAbstractAppearance;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetListAppearance.WidgetListCss;


public class FieldsWidgetListAppearance extends WidgetListAbstractAppearance {

	static {
		Resources.INSTANCE.css().ensureInjected();
	}

	public interface FieldsWidgetListCss extends WidgetListCss {
	}

	interface Resources extends ClientBundle {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source({
				"com/googlecode/mgwt/ui/client/widget/list/widgetlist/widgetlist.css",
				"fieldswidgetlist.css", })
		FieldsWidgetListCss css();
	}

	@Override
	public FieldsWidgetListCss css() {
		return Resources.INSTANCE.css();
	}

}
