package com.nutrinfomics.geneway.client.icon;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder.LocalImageHolderAppearance;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder.LocalImageHolderAppearance.Images;
import com.nutrinfomics.geneway.client.icon.LocalImageHolderDefaultAppearance.Resources;

public class LocalImageHolderDefaultHighAppearance implements
		LocalImageHolderAppearance {

	public interface Resources extends ClientBundle, Images {

		Resources INSTANCE = GWT.create(Resources.class);

		@Source("resources/ic_action_apple_hdpi.png")
		ImageResource apple();

		@Source("resources/ic_action_banana_hdpi.png")
		ImageResource banana();

		@Source("resources/ic_action_avocado_hdpi.png")
		ImageResource avocado();
		
		@Source("resources/ic_action_cherry_hdpi.png")
		ImageResource cherry();
		
		@Source("resources/ic_action_grape_hdpi.png")
		ImageResource grape();
		
		@Source("resources/ic_action_lemon_hdpi.png")
		ImageResource lemon();
		
		@Source("resources/ic_action_strawberry_hdpi.png")
		ImageResource strawberry();
		
		@Source("resources/ic_action_watermelon_hdpi.png")
		ImageResource watermelon();

		@Source("resources/ic_action_almond_hdpi.png")		
		ImageResource almond();
		
		@Source("resources/ic_action_beef_tenderloin_hdpi.png")
		ImageResource beef_tenderloin();
		
		@Source("resources/ic_action_egg_hdpi.png")
		ImageResource egg();
		
		@Source("resources/ic_action_egg_yolk_hdpi.png")
		ImageResource egg_yolk();
		
		@Source("resources/ic_action_fish_hdpi.png")
		ImageResource fish();
		
		@Source("resources/ic_action_goat_hdpi.png")
		ImageResource goat();
		
		@Source("resources/ic_action_kiwi_hdpi.png")
		ImageResource kiwi();
		
		@Source("resources/ic_action_tomato_hdpi.png")
		ImageResource tomato();

		@Source("resources/ic_action_parsley_hdpi.png")
		ImageResource parsley();

		@Source("resources/ic_action_spearmint_hdpi.png")
		ImageResource spearmint();
		
		@Source("resources/ic_action_arugula_hdpi.png")
		ImageResource arugula();

		
		@Source("resources/ic_action_cabbage_hdpi.png")
		ImageResource cabbage();
		
		@Source("resources/ic_action_carrot_hdpi.png")
		ImageResource carrot();
		
		@Source("resources/ic_action_eggplant_hdpi.png")
		ImageResource eggplant();
		
		@Source("resources/ic_action_halloween_hdpi.png")
		ImageResource pumpkin();

		@Source("resources/ic_action_pepper_hdpi.png")
		ImageResource peppers_red();

		@Source("resources/ic_action_pepper_hdpi.png")
		ImageResource peppers_yellow();

		
		@Source("resources/ic_action_shrimp_hdpi.png")
		ImageResource shrimp();

		@Source("resources/ic_action_squid_hdpi.png")
		ImageResource squid();

		@Source("resources/ic_action_persimmon_hdpi.png")
		ImageResource persimmon();

		@Source("resources/ic_action_orange_hdpi.png")
		ImageResource orange();
		
		@Source("resources/ic_action_lettuce_hdpi.png")
		ImageResource lettuce();

		@Source("resources/ic_action_zucchini_hdpi.png")
		ImageResource zucchini();
		
		@Source("resources/ic_action_salmon_hdpi.png")
		ImageResource salmon();
		
		@Source("resources/ic_action_brazilnut_hdpi.png")
		ImageResource brazilnut();
		
		@Source("resources/ic_action_cucumber_hdpi.png")
		ImageResource cucumber();

		@Source("resources/ic_action_lamb_hdpi.png")
		ImageResource lamb();
		
		@Source("resources/ic_action_olive_oil_hdpi.png")
		ImageResource olive_oil();
		
		@Source("resources/ic_action_tangerine_hdpi.png")
		ImageResource tangerine();

		
		
		@Source("resources/ic_action_leaf_hdpi.png")
		ImageResource leaf();

		@Source("resources/ic_action_waiter_hdpi.png")
		ImageResource meals();

		@Source("resources/ic_action_picnic_hdpi.png")
		ImageResource ingredients();
		
		@Source("resources/logo1WhiteThick_hdpi.png")
		ImageResource geneWay();


		@Source("resources/reemBefore_mdpi.png")
		ImageResource reemBefore();
		
		@Source("resources/reemAfter_mdpi.png")
		ImageResource reemAfter();

		@Source("resources/ic_action_logout_hdpi.png")
		ImageResource logout();

	}

	@Override
	public Images get() {
		return Resources.INSTANCE;
	}

}
