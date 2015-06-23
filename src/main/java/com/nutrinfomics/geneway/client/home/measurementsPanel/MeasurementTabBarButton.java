package com.nutrinfomics.geneway.client.home.measurementsPanel;

import com.google.gwt.resources.client.ImageResource;
import com.googlecode.mgwt.ui.client.widget.image.ImageHolder;
import com.nutrinfomics.geneway.client.home.AbstractTabBarButton;
import com.nutrinfomics.geneway.client.home.TabBarButtonData;

public class MeasurementTabBarButton extends AbstractTabBarButton {

	public MeasurementTabBarButton() {
		super(new TabBarButtonData() {
			
			@Override
			public ImageResource getUnselecetedImageResource() {
				return ImageHolder.get().storage();
			}
			
			@Override
			public String getText() {
				return constants.measurements();
			}
			
			@Override
			public ImageResource getSelectedImageResource() {
				return ImageHolder.get().storage();
			}
		});
	}

}
