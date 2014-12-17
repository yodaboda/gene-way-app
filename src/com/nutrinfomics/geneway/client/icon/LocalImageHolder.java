package com.nutrinfomics.geneway.client.icon;

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
