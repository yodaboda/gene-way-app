package com.nutrinfomics.geneway.client.icon;

/*
 * Copyright (C) 2019 Firas Swidan†
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.googlecode.mgwt.ui.client.widget.image.ImageHolder.ImageHolderAppearance;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder.LocalImageHolderAppearance.Images;
import com.nutrinfomics.geneway.shared.FoodItemType;

public class LocalImageHolder {

	private static final LocalImageHolderAppearance APPEARANCE = GWT.create(LocalImageHolderAppearance.class);

	public interface LocalImageHolderAppearance {
		public interface Images {
			ImageResource apple();
			ImageResource banana();
			ImageResource avocado();
			ImageResource cherry();
			ImageResource grape();
			ImageResource lemon();
			ImageResource strawberry();
			ImageResource watermelon();
			
			ImageResource almond();
			ImageResource beef_tenderloin();
			ImageResource egg();
			ImageResource egg_yolk();
			ImageResource fish();
			ImageResource goat();
			ImageResource kiwi();
			ImageResource tomato();
			
			ImageResource parsley();
			ImageResource spearmint();
			ImageResource arugula();
			ImageResource cabbage();
			ImageResource carrot();
			ImageResource eggplant();
			ImageResource pumpkin();
			ImageResource peppers_red();
			ImageResource peppers_yellow();
			
			ImageResource shrimp();
			ImageResource squid();
			
			ImageResource persimmon();
			
			ImageResource orange();
			ImageResource lettuce();

			ImageResource zucchini();
			ImageResource salmon();
			ImageResource brazilnut();
			ImageResource cucumber();
			ImageResource lamb();
			ImageResource olive_oil();
			ImageResource tangerine();

			
			ImageResource leaf();
			ImageResource meals();
			ImageResource ingredients();
			
			ImageResource geneWay();
			
			ImageResource reemBefore();
			
			ImageResource reemAfter();
			
			ImageResource logout();
		}
		Images get();
	}

	public static Images get() {
		return APPEARANCE.get();
	}

	public static ImageResource get(FoodItemType foodItemType){
		Images images = LocalImageHolder.get();
		switch(foodItemType){
			case KIWI: return images.kiwi();
			case GOAT: return images.goat();
			case TOMATO: return images.tomato();
			case EGG: return images.egg();
			case EGG_YOLK: return images.egg_yolk();
			case ALMOND: return images.almond();
			case APPLE: return images.apple();
			case BANANA: return images.banana();
			case AVOCADO: return images.avocado();
			case BEEF_TENDERLOIN: return images.beef_tenderloin();
			case LEMON_JUICE: return images.lemon();
			case GRAPE: return images.grape();
			case BEAN_SNAP_YELLOW: return images.fish();
			case PARSLEY: return images.parsley();
			case ARUGULA: return images.arugula();
			case SPEARMINT: return images.spearmint();
			case CABBAGE: return images.cabbage();
			case CARROT: return images.carrot();
			case PEPPERS_RED: return images.peppers_red();
			case PEPPERS_YELLOW: return images.peppers_yellow();
			case PUMPKIN: return images.pumpkin();
			case EGGPLANT: return images.eggplant();
			case GILT_HEAD_BREAM: return images.fish();
			case SHRIMP: return images.shrimp();
			case SQUID: return images.squid();
			case PERSIMMON_JAPANESE: return images.persimmon();
			case ORANGE: return images.orange();
			case LETTUCE: return images.lettuce();
			case ZUCCHINI: return images.zucchini();
			case SALMON: return images.salmon();
			case BRAZILNUT: return images.brazilnut();
			case CUCUMBER_PEELED: return images.cucumber();
			case LAMB: return images.lamb();
			case OLIVE_OIL: return images.olive_oil();
			case TANGERINES: return images.tangerine();

			default: return images.leaf();
		}
	}
}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */