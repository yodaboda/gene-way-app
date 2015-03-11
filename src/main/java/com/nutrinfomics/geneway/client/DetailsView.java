package com.nutrinfomics.geneway.client;

import com.google.gwt.user.client.ui.IsWidget;

public interface DetailsView extends IsWidget{

	public abstract void showAboutButton();

	public abstract void hideAboutButton();

	public abstract void showBackButton();

	public abstract void hideBackButton();

}