package com.nutrinfomics.geneway.client.home;

import com.google.gwt.resources.client.ImageResource;
import com.googlecode.mgwt.ui.client.widget.image.ImageHolder;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder;

public class MealsTabBarButton extends AbstractTabBarButton {

	public MealsTabBarButton(){
		super(new TabBarButtonData() {
			
			@Override
			public ImageResource getUnselecetedImageResource() {
				return LocalImageHolder.get().meals();
			}
			
			@Override
			public String getText() {
				return constants.meals();
			}
			
			@Override
			public ImageResource getSelectedImageResource() {
				return LocalImageHolder.get().meals();
			}
		});
	}

}
