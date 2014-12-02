package com.nutrinfomics.geneway.client.home;

import com.google.gwt.resources.client.ImageResource;
import com.googlecode.mgwt.ui.client.widget.tabbar.FavoritesTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.resources.TabBarImageHolder;

public class MyFavoritesTabBarButton extends AbstractTabBarButton {

	public MyFavoritesTabBarButton() {
		super(new TabBarButtonData() {
			
			@Override
			public ImageResource getUnselecetedImageResource() {
				return TabBarImageHolder.get().favorites();
			}
			
			@Override
			public String getText() {
				return constants.favorites();
			}
			
			@Override
			public ImageResource getSelectedImageResource() {
				return TabBarImageHolder.get().favoritesSelected();
			}
		});
	}

}
