package com.nutrinfomics.geneway.client.home;

import com.google.gwt.resources.client.ImageResource;
import com.googlecode.mgwt.ui.client.widget.image.ImageHolder;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder;

public class IgnredientsTabBarButton extends AbstractTabBarButton {

	public IgnredientsTabBarButton() {
		super(new TabBarButtonData() {
			
			@Override
			public ImageResource getUnselecetedImageResource() {
				return LocalImageHolder.get().ingredients();
			}
			
			@Override
			public String getText() {
				return constants.ingredients();
			}
			
			@Override
			public ImageResource getSelectedImageResource() {
				return LocalImageHolder.get().ingredients();
			}
		});
	}

}
