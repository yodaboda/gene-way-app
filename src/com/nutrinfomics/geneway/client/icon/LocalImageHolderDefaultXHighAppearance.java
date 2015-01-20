package com.nutrinfomics.geneway.client.icon;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder.LocalImageHolderAppearance;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder.LocalImageHolderAppearance.Images;
import com.nutrinfomics.geneway.client.icon.LocalImageHolderDefaultHighAppearance.Resources;

public class LocalImageHolderDefaultXHighAppearance implements
		LocalImageHolderAppearance {

	public interface Resources extends ClientBundle, Images {

		Resources INSTANCE = GWT.create(Resources.class);

		@Source("resources/ic_action_apple_xhdpi.png")
		ImageResource apple();

		@Source("resources/ic_action_banana_xhdpi.png")
		ImageResource banana();

		@Source("resources/ic_action_avocado_xhdpi.png")
		ImageResource avocado();
		
		@Source("resources/ic_action_cherry_xhdpi.png")
		ImageResource cherry();
		
		@Source("resources/ic_action_grape_xhdpi.png")
		ImageResource grape();
		
		@Source("resources/ic_action_lemon_xhdpi.png")
		ImageResource lemon();
		
		@Source("resources/ic_action_strawberry_xhdpi.png")
		ImageResource strawberry();
		
		@Source("resources/ic_action_watermelon_xhdpi.png")
		ImageResource watermelon();

		
		@Source("resources/ic_action_almond_xhdpi.png")		
		ImageResource almond();
		
		@Source("resources/ic_action_beef_tenderloin_xhdpi.png")
		ImageResource beef_tenderloin();
		
		@Source("resources/ic_action_egg_xhdpi.png")
		ImageResource egg();
		
		@Source("resources/ic_action_egg_yolk_xhdpi.png")
		ImageResource egg_yolk();
		
		@Source("resources/ic_action_fish_xhdpi.png")
		ImageResource fish();
		
		@Source("resources/ic_action_goat_xhdpi.png")
		ImageResource goat();
		
		@Source("resources/ic_action_kiwi_xhdpi.png")
		ImageResource kiwi();
		
		@Source("resources/ic_action_tomato_xhdpi.png")
		ImageResource tomato();

		@Source("resources/ic_action_parsley_xhdpi.png")
		ImageResource parsley();

		@Source("resources/ic_action_spearmint_xhdpi.png")
		ImageResource spearmint();
		
		@Source("resources/ic_action_arugula_xhdpi.png")
		ImageResource arugula();

		@Source("resources/ic_action_cabbage_xhdpi.png")
		ImageResource cabbage();
		
		@Source("resources/ic_action_carrot_xhdpi.png")
		ImageResource carrot();
		
		@Source("resources/ic_action_eggplant_xhdpi.png")
		ImageResource eggplant();
		
		@Source("resources/ic_action_halloween_xhdpi.png")
		ImageResource pumpkin();

		@Source("resources/ic_action_pepper_xhdpi.png")
		ImageResource peppers_red();

		@Source("resources/ic_action_pepper_xhdpi.png")
		ImageResource peppers_yellow();

		@Source("resources/ic_action_shrimp_xhdpi.png")
		ImageResource shrimp();

		@Source("resources/ic_action_squid_xhdpi.png")
		ImageResource squid();

		@Source("resources/ic_action_persimmon_xhdpi.png")
		ImageResource persimmon();

		@Source("resources/ic_action_orange_xhdpi.png")
		ImageResource orange();
		
		@Source("resources/ic_action_lettuce_xhdpi.png")
		ImageResource lettuce();

		@Source("resources/ic_action_zucchini_xhdpi.png")
		ImageResource zucchini();
		
		@Source("resources/ic_action_salmon_xhdpi.png")
		ImageResource salmon();
		
		@Source("resources/ic_action_brazilnut_xhdpi.png")
		ImageResource brazilnut();
		
		@Source("resources/ic_action_cucumber_xhdpi.png")
		ImageResource cucumber();

		@Source("resources/ic_action_lamb_xhdpi.png")
		ImageResource lamb();
		
		@Source("resources/ic_action_olive_oil_xhdpi.png")
		ImageResource olive_oil();
		
		@Source("resources/ic_action_tangerine_xhdpi.png")
		ImageResource tangerine();

		
		@Source("resources/ic_action_leaf_xhdpi.png")
		ImageResource leaf();

		
		
		@Source("resources/ic_action_waiter_xhdpi.png")
		ImageResource meals();

		@Source("resources/ic_action_picnic_xhdpi.png")
		ImageResource ingredients();
		
		@Source("resources/logo1WhiteThick_xhdpi.png")
		ImageResource geneWay();

		@Source("resources/reemBefore_hdpi.png")
		ImageResource reemBefore();
		
		@Source("resources/reemAfter_hdpi.png")
		ImageResource reemAfter();

		@Source("resources/ic_action_logout_xhdpi.png")
		ImageResource logout();


	}

	@Override
	public Images get() {
		return Resources.INSTANCE;
	}

}
