package com.nutrinfomics.geneway.client.icon;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder.LocalImageHolderAppearance;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder.LocalImageHolderAppearance.Images;

public class LocalImageHolderDefaultAppearance implements
		LocalImageHolderAppearance {

	public interface Resources extends ClientBundle, Images {

		Resources INSTANCE = GWT.create(Resources.class);

		@Source("resources/ic_action_apple_mdpi.png")
		ImageResource apple();

		@Source("resources/ic_action_banana_mdpi.png")
		ImageResource banana();

		@Source("resources/ic_action_avocado_mdpi.png")
		ImageResource avocado();
		
		@Source("resources/ic_action_cherry_mdpi.png")
		ImageResource cherry();
		
		@Source("resources/ic_action_grape_mdpi.png")
		ImageResource grape();
		
		@Source("resources/ic_action_lemon_mdpi.png")
		ImageResource lemon();
		
		@Source("resources/ic_action_strawberry_mdpi.png")
		ImageResource strawberry();
		
		@Source("resources/ic_action_watermelon_mdpi.png")
		ImageResource watermelon();

		@Source("resources/ic_action_almond_mdpi.png")		
		ImageResource almond();
		
		@Source("resources/ic_action_beef_tenderloin_mdpi.png")
		ImageResource beef_tenderloin();
		
		@Source("resources/ic_action_egg_mdpi.png")
		ImageResource egg();
		
		@Source("resources/ic_action_egg_yolk_mdpi.png")
		ImageResource egg_yolk();
		
		@Source("resources/ic_action_fish_mdpi.png")
		ImageResource fish();
		
		@Source("resources/ic_action_goat_mdpi.png")
		ImageResource goat();
		
		@Source("resources/ic_action_kiwi_mdpi.png")
		ImageResource kiwi();
		
		@Source("resources/ic_action_tomato_mdpi.png")
		ImageResource tomato();

		@Source("resources/ic_action_parsley_mdpi.png")
		ImageResource parsley();

		@Source("resources/ic_action_spearmint_mdpi.png")
		ImageResource spearmint();
		
		@Source("resources/ic_action_arugula_mdpi.png")
		ImageResource arugula();

		@Source("resources/ic_action_cabbage_mdpi.png")
		ImageResource cabbage();
		
		@Source("resources/ic_action_carrot_mdpi.png")
		ImageResource carrot();
		
		@Source("resources/ic_action_eggplant_mdpi.png")
		ImageResource eggplant();
		
		@Source("resources/ic_action_halloween_mdpi.png")
		ImageResource pumpkin();

		@Source("resources/ic_action_pepper_mdpi.png")
		ImageResource peppers_red();

		@Source("resources/ic_action_pepper_mdpi.png")
		ImageResource peppers_yellow();
		
		@Source("resources/ic_action_shrimp_mdpi.png")
		ImageResource shrimp();

		@Source("resources/ic_action_squid_mdpi.png")
		ImageResource squid();

		@Source("resources/ic_action_persimmon_mdpi.png")
		ImageResource persimmon();
		
		@Source("resources/ic_action_orange_mdpi.png")
		ImageResource orange();
		
		@Source("resources/ic_action_lettuce_mdpi.png")
		ImageResource lettuce();

		@Source("resources/ic_action_zucchini_mdpi.png")
		ImageResource zucchini();
		
		@Source("resources/ic_action_salmon_mdpi.png")
		ImageResource salmon();
		
		@Source("resources/ic_action_brazilnut_mdpi.png")
		ImageResource brazilnut();
		
		@Source("resources/ic_action_cucumber_mdpi.png")
		ImageResource cucumber();

		@Source("resources/ic_action_lamb_mdpi.png")
		ImageResource lamb();
		
		@Source("resources/ic_action_olive_oil_mdpi.png")
		ImageResource olive_oil();
		
		@Source("resources/ic_action_tangerine_mdpi.png")
		ImageResource tangerine();

		
		@Source("resources/ic_action_leaf_mdpi.png")
		ImageResource leaf();
		
		@Source("resources/ic_action_waiter_mdpi.png")
		ImageResource meals();

		@Source("resources/ic_action_picnic_mdpi.png")
		ImageResource ingredients();
		
		@Source("resources/logo1WhiteThick_mdpi.png")
		ImageResource geneWay();

	}

	@Override
	public Images get() {
		return Resources.INSTANCE;
	}

}
