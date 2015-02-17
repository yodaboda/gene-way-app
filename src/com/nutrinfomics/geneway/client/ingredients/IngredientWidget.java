package com.nutrinfomics.geneway.client.ingredients;


import com.nutrinfomics.geneway.client.AbstractImageButton;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.home.SnackImageButtonAppearance;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.shared.FoodItemType;

public class IngredientWidget extends AbstractImageButton {

	public IngredientWidget(FoodItemType ingredient) {
		super(new SnackImageButtonAppearance(), LocalImageHolder.get(ingredient),"", Styles.GRAY, Styles.GREEN, Styles.WHITE);
		getElement().getStyle().setColor(Styles.GREEN);
		setIconColor(Styles.GREEN);
		setText(ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(ingredient.toString()));
	}

}
