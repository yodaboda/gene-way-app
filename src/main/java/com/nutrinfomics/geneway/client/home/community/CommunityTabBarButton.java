package com.nutrinfomics.geneway.client.home.community;

import com.google.gwt.resources.client.ImageResource;
import com.googlecode.mgwt.ui.client.widget.image.ImageHolder;
import com.nutrinfomics.geneway.client.home.AbstractTabBarButton;
import com.nutrinfomics.geneway.client.home.TabBarButtonData;

public class CommunityTabBarButton extends AbstractTabBarButton {
	public CommunityTabBarButton() {
		super(new TabBarButtonData() {
			
			@Override
			public ImageResource getUnselecetedImageResource() {
				return ImageHolder.get().group();
			}
			
			@Override
			public String getText() {
				return constants.community();
			}
			
			@Override
			public ImageResource getSelectedImageResource() {
				return ImageHolder.get().group();
			}
		});
	}
}
