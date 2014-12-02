package com.nutrinfomics.geneway.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.googlecode.mgwt.ui.client.widget.list.celllist.Cell;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.server.domain.plan.FoodItem;
import com.nutrinfomics.geneway.server.domain.plan.Snack;

public class SnackTrackerCell implements Cell<SnackTracker>{

	private static Template TEMPLATE = GWT.create(Template.class);

	public interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template("<div class=\"{0}\">{1}</div>")
		SafeHtml content(String classes, String cellContents);
	}
	
	@Override
	public void render(SafeHtmlBuilder safeHtmlBuilder, SnackTracker model) {
		safeHtmlBuilder.append(TEMPLATE.content("cell" + model.getState().toString(), SafeHtmlUtils.htmlEscape(getDisplayString(model))));
	}

	private String getDisplayString(SnackTracker model) {
		String string = "";
		for(FoodItem foodItem : model.getSnack().getFoodItems()){
			string += ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(foodItem.getFoodType().toString()) + " + ";
		}
		if(!string.isEmpty()) string = string.substring(0, string.length() - 3);
		return string;
	}

	@Override
	public boolean canBeSelected(SnackTracker model) {
		return true;
	}

}
