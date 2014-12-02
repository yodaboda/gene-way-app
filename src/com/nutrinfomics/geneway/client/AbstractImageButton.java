package com.nutrinfomics.geneway.client;

import com.google.gwt.resources.client.ImageResource;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton;
import com.googlecode.mgwt.ui.client.widget.button.ImageButtonAbstractAppearance;
import com.nutrinfomics.geneway.client.style.Styles;

abstract public class AbstractImageButton extends ImageButton {
	//colors *must* be in '#******' notation
	public AbstractImageButton(ImageButtonAbstractAppearance appearance,
								ImageResource imageResource, String text,
								String iconColor, String iconActiveColor,
								String backgroundColor){ //need to investigate why android does not like these
		super(appearance, imageResource, text);
		getElement().getStyle().setBackgroundColor(backgroundColor);
		setIconActiveColor(iconActiveColor);
		setIconColor(iconColor);
	}
}
