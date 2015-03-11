package com.nutrinfomics.geneway.client.ingredients;


import com.google.gwt.dom.client.Style.Unit;
import com.nutrinfomics.geneway.client.AbstractImageButton;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.home.SnackImageButtonAppearance;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.shared.FoodItemType;

public class IngredientWidget extends AbstractImageButton {

	public IngredientWidget(FoodItemType ingredient) {
		super(new SnackImageButtonAppearance(), LocalImageHolder.get(ingredient),"", Styles.GRAY, Styles.GREEN_DARK, Styles.WHITE);
		getElement().getStyle().setColor(Styles.GREEN_DARK);
		setIconColor(Styles.GREEN_DARK);
		setText(ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(ingredient.toString()));
//		getElement().getStyle().setProperty("margin", "auto");
		getElement().getStyle().setProperty("maxWidth", "none");
	}

}
