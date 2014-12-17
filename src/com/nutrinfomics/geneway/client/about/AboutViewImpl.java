package com.nutrinfomics.geneway.client.about;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.panel.Panel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.nutrinfomics.geneway.client.DetailsViewImpl;

public class AboutViewImpl extends DetailsViewImpl implements AboutView {

  private Panel round;
//  private Button button;

  public AboutViewImpl() {
    round = new Panel();
    FlexPanel flexPanel = new FlexPanel();
    flexPanel.setOrientation(Orientation.VERTICAL);
    flexPanel.setAlignment(Alignment.CENTER);
    round.add(flexPanel);
    round.setRound(true);

    flexPanel.add(new HTML("Gene-Way™"));
    flexPanel.add(new HTML("Your way to well-being"));
    
    flexPanel.add(new HTML("<div>Icons made by Freepik, Icons8, Agata Kuczminska from <a href=\"http://www.flaticon.com\" title=\"Flaticon\">www.flaticon.com</a>         is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\">CC BY 3.0</a></div>"));

//    if (MGWT.getFormFactor().isPhone()) {
//      button = new Button("back");
//
//      flexPanel.add(button);
//    }
    ScrollPanel scrollPanel = new ScrollPanel();
    scrollPanel.setWidget(round);
    
    add(scrollPanel);
  }
}
