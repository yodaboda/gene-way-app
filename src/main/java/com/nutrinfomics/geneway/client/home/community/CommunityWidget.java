package com.nutrinfomics.geneway.client.home.community;

/*
 * Copyright (C) 2019 Firas Swidan†
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

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

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */