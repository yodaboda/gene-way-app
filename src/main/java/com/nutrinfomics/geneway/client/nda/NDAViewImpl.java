package com.nutrinfomics.geneway.client.nda;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.ScrollingDetailsViewImplementation;

public class NDAViewImpl extends ScrollingDetailsViewImplementation implements
		NDAView {
	private Button confirmButton;
	private CheckBox agreeCheckBox;

	public NDAViewImpl(){
		FlexPanel flexPanel = new FlexPanel();
		HTML title = new HTML(constants.ndaTitle());
		HTML content = new HTML(constants.ndaText());
		content.getElement().getStyle().setMarginLeft(30, Unit.PX);
		
		flexPanel.add(title);
		flexPanel.add(content);

		Label name = new Label("Firas Swidan");
		Label time = new Label(DateTimeFormat.getFormat(PredefinedFormat.DATE_LONG).format(new Date()));
		
		flexPanel.add(new FixedSpacer());
		flexPanel.add(new FlexSpacer());
		flexPanel.add(name);
		flexPanel.add(time);
		
		agreeCheckBox = new CheckBox("I agree");
		flexPanel.add(agreeCheckBox);
		
		
		confirmButton = new Button("Confirm");
		flexPanel.add(confirmButton);
		
		setScrollWidget(flexPanel);

	}

	@Override
	public HasTapHandlers getConfirmButton() {
		return confirmButton;
	}

	@Override
	public CheckBox getCheckBox() {
		return agreeCheckBox;
	}
}
