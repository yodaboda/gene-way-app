package com.nutrinfomics.geneway.client.home;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarAbstractAppearance;

public class HomeTabBarAppearance extends TabBarAbstractAppearance {

	static {
		Resources.INSTANCE.homeTabBarCss().ensureInjected();
		Resources.INSTANCE.homeTabBarButtonCss().ensureInjected();
	}

	public interface HomeTabBarCss extends TabBarCss {}

	public interface HomeTabBarButtonCss extends TabBarButtonCss{}
	
	interface Resources extends ClientBundle {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source({"com/googlecode/mgwt/ui/client/widget/tabbar/tabbar.css"})
		HomeTabBarCss homeTabBarCss();
		
		@Source({"com/googlecode/mgwt/ui/client/widget/tabbar/tabbar-button.css", "hometabbarbutton.css"})
		HomeTabBarButtonCss homeTabBarButtonCss();

	}

	@Override
	public TabBarButtonCss css() {
		return Resources.INSTANCE.homeTabBarButtonCss();
	}

	@Override
	public TabBarCss barCss() {
		return Resources.INSTANCE.homeTabBarCss();
	}

}
