package com.nutrinfomics.geneway.client.home;

import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;
import com.nutrinfomics.geneway.client.DetailsViewImpl;
import com.nutrinfomics.geneway.client.home.settingsPanel.SettingsTabBarButton;
import com.nutrinfomics.geneway.client.home.settingsPanel.SettingsWidget;
import com.nutrinfomics.geneway.client.style.Styles;

public class HomeViewImpl extends DetailsViewImpl implements HomeView {
	private TabPanel tabPanel;
	private MealsWidget mealsWidget;
	private IngredientsWidget ingredientsWidget;
	private SettingsWidget settingsWidget;
	
	public HomeViewImpl(){
		super();
		hideBackButton();
		showAboutButton();
		
		showHeaderPanel();
		main.remove(bodyPanel);
		
//		ScrollPanel scrollPanel = new ScrollPanel();
//		main.add(scrollPanel);
//		bodyCenterAlign();
		
		tabPanel = new TabPanel();
		tabPanel.getElement().setAttribute("style", Styles.BACKGROUND_COLOR);
		tabPanel.setWidth("100%");
		
		tabPanel.getElement().setDir("ltr");
		
//	    tabPanel.add(new ContactsTabBarButton(), new Label("Contacts"));
//	    tabPanel.add(new HistoryTabBarButton(), new Label("History"));
//	    tabPanel.add(new MostViewedTabBarButton(), new Label("Most Viewed"));
//	    tabPanel.add(new SearchTabBarButton(), new Label("Search"));
		
		mealsWidget = new MealsWidget();
//		scrollPanel.setWidget(mealsWidget);
		
//		ButtonBar footerPanel = new ButtonBar();
//		footerPanel.getElement().getStyle().setBackgroundColor(Styles.BACKGROUND_COLOR_VALUE);
//		footerPanel.add(new MealsTabBarButton());
//		footerPanel.add(new IgnredientsTabBarButton());
//		main.add(footerPanel);
		
		tabPanel.add(new MealsTabBarButton(), mealsWidget);
//		tabPanel.add(new MealsTabBarButton(), new Label("meals widget"));

	    ingredientsWidget = new IngredientsWidget();
		tabPanel.add(new IgnredientsTabBarButton(), ingredientsWidget);
//		tabPanel.add(new IgnredientsTabBarButton(), new Label("ingredient widget"));

//	    tabPanel.add(new MyFavoritesTabBarButton(), new FavoritesWidget());

		settingsWidget = new SettingsWidget();
		tabPanel.add(new SettingsTabBarButton(), settingsWidget);
		
		tabPanel.tabBar.getElement().setAttribute("style", Styles.BACKGROUND_COLOR + "border-top-color:" + Styles.BACKGROUND_COLOR_VALUE + ";");
		tabPanel.tabContainer.getElement().setAttribute("style", Styles.BACKGROUND_COLOR);
//
//		main.remove(bodyPanel);
//
//
////		FlexPanel flexPanel = new FlexPanel();
////		flexPanel.add(tabPanel, 1);
//////		flexPanel.getElement().setAttribute("style", Styles.HORIZONTAL_CENTER_ALIGN + Styles.VERTICAL_CENTER_ALIGN);
////		main.add(flexPanel);

		main.add(tabPanel, 1);
////		main.getElement().setAttribute("style", Styles.HORIZONTAL_CENTER_ALIGN + Styles.VERTICAL_CENTER_ALIGN);
	}
}