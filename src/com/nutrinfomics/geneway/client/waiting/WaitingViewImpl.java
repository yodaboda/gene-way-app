package com.nutrinfomics.geneway.client.waiting;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;
import com.nutrinfomics.geneway.client.DetailsViewImpl;
import com.nutrinfomics.geneway.client.GeneWayImageButton;

public class WaitingViewImpl extends DetailsViewImpl implements WaitingView {
	
	private ProgressIndicator progressIndicator;

	public WaitingViewImpl(){
		hideHeaderPanel();

	    bodyCenterAlign();

		progressIndicator = new ProgressIndicator();

//		progressIndicator.getElement().setAttribute("style", "margin-top: 50px");

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		add(verticalPanel);
		
		verticalPanel.add(new GeneWayImageButton());
		
		verticalPanel.setSpacing(40);
		verticalPanel.add(progressIndicator);
	}

}
