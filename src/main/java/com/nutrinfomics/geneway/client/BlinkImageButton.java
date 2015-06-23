package com.nutrinfomics.geneway.client;

import com.google.gwt.resources.client.ImageResource;

public class BlinkImageButton extends AbstractImageButton {

	public BlinkImageButton(ImageResource imageResource, String text, String iconColor,
			String iconActiveColor, String backgroundColor) {
		super(new BlinkImageButtonAbstractAppearance(), imageResource, text, iconColor, iconActiveColor,
				backgroundColor);
	}

}
