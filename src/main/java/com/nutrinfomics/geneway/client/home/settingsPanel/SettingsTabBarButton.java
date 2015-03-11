package com.nutrinfomics.geneway.client.home.settingsPanel;

import com.google.gwt.resources.client.ImageResource;
import com.googlecode.mgwt.ui.client.widget.image.ImageHolder;
import com.nutrinfomics.geneway.client.home.AbstractTabBarButton;
import com.nutrinfomics.geneway.client.home.TabBarButtonData;

public class SettingsTabBarButton extends AbstractTabBarButton {
	public SettingsTabBarButton(){
		super(new TabBarButtonData() {
			
			@Override
			public ImageResource getUnselecetedImageResource() {
				return ImageHolder.get().settings();
			}
			
			@Override
			public String getText() {
				return constants.settings();
			}
			
			@Override
			public ImageResource getSelectedImageResource() {
				return ImageHolder.get().settings();
			}
		});

	}
}
