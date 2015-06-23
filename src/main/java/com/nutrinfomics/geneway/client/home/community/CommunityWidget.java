package com.nutrinfomics.geneway.client.home.community;

import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton.TextPosition;
import com.googlecode.mgwt.ui.client.widget.image.ImageHolder;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Justification;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.nutrinfomics.geneway.client.AbstractImageButton;
import com.nutrinfomics.geneway.client.GeneWayImageButton;
import com.nutrinfomics.geneway.client.GeneWayImageButtonAppearance;
import com.nutrinfomics.geneway.client.home.AbstractTabBarWidget;
import com.nutrinfomics.geneway.client.style.Styles;

public class CommunityWidget extends AbstractTabBarWidget {
	public CommunityWidget(){
		this.setJustification(Justification.CENTER);
		FlexPanel panel = new FlexPanel();
		panel.setOrientation(Orientation.HORIZONTAL);
		panel.setJustification(Justification.CENTER);
		panel.setAlignment(Alignment.CENTER);
//		panel.getElement().getStyle().setBackgroundColor(Styles.WHITE);
//		panel.getElement().getStyle().setProperty("width", "50%");
		ImageButton imageButton = new AbstractImageButton(new GeneWayImageButtonAppearance(), ImageHolder.get().person(),
															"", Styles.BLUE, Styles.BLUE, Styles.BACKGROUND_COLOR_VALUE){};
		imageButton.setText("Clair");
		imageButton.setTextPosition(TextPosition.RIGHT);
		
		panel.add(imageButton);
		HTML html = new HTML("lost 5 kg!");
		html.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
		panel.add(html);
		
		ImageButton likeButton = new AbstractImageButton(new GeneWayImageButtonAppearance(), ImageHolder.get().sendNow(),
														"", Styles.GRAY, Styles.GREEN_DARK, Styles.BACKGROUND_COLOR_VALUE){};
		panel.add(likeButton);
		add(panel);
	}
}
