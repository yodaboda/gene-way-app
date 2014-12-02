package com.nutrinfomics.geneway.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.googlecode.mgwt.ui.client.widget.list.celllist.Cell;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.shared.FoodItemType;

public class FoodItemTypeCell implements Cell<FoodItemType>{

	private static Template TEMPLATE = GWT.create(Template.class);

	public interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template("<div class=\"{0}\">{1}</div>")
		SafeHtml content(String classes, String cellContents);
	}

	@Override
	public void render(SafeHtmlBuilder safeHtmlBuilder, FoodItemType model) {
		safeHtmlBuilder.append(TEMPLATE.content("", SafeHtmlUtils.htmlEscape(getDisplayString(model))));
	}

	private String getDisplayString(FoodItemType model) {
		return ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(model.toString());
	}

	@Override
	public boolean canBeSelected(FoodItemType model) {
		return false;
	}
	

}
