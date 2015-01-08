package com.nutrinfomics.geneway.client.home;

import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;

public class WaitingTabBarWidget extends AbstractTabBarWidget {
	private ProgressIndicator progressIndicator;
	
	public void showProgressIndicator(){
		progressIndicator = new ProgressIndicator();
		add(progressIndicator);
	}
	
	public void removeProgressIndicator(){
		remove(progressIndicator);
		progressIndicator = null;
	}
}
