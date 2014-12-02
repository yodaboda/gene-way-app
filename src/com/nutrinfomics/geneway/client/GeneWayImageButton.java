package com.nutrinfomics.geneway.client;

import com.googlecode.mgwt.ui.client.widget.button.ImageButton;
import com.nutrinfomics.geneway.client.icon.LocalImageHolder;
import com.nutrinfomics.geneway.client.style.Styles;

public class GeneWayImageButton extends AbstractImageButton {
	public GeneWayImageButton(){
		super(new GeneWayImageButtonAppearance(), LocalImageHolder.get().geneWay(), "", 
				Styles.WHITE, Styles.WHITE, Styles.GREEN);
	}
}
