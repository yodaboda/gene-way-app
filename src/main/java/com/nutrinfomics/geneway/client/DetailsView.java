package com.nutrinfomics.geneway.client;

import com.google.gwt.user.client.ui.IsWidget;

public interface DetailsView extends IsWidget{

	public void showAboutButton();

	public void hideAboutButton();

	public void showBackButton();

	public void hideBackButton();
	
	public void displayLoader();
	
	public void hideLoader();

}