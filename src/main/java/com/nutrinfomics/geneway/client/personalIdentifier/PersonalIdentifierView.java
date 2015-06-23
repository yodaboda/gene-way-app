package com.nutrinfomics.geneway.client.personalIdentifier;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.FieldsWidgetListView;

public interface PersonalIdentifierView extends FieldsWidgetListView {

	public HasTapHandlers getUnlockButton();

	public String getIdentifier();

}
