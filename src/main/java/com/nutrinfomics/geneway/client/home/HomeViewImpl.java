package com.nutrinfomics.geneway.client.home;



import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.ui.client.widget.button.ImageButton;
import com.googlecode.mgwt.ui.client.widget.image.ImageHolder;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Orientation;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;
import com.nutrinfomics.geneway.client.AbstractImageButton;
import com.nutrinfomics.geneway.client.BlinkImageButton;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.DetailsViewImpl;
import com.nutrinfomics.geneway.client.GeneWayImageButton;
import com.nutrinfomics.geneway.client.GeneWayImageButtonAppearance;
import com.nutrinfomics.geneway.client.home.community.CommunityTabBarButton;
import com.nutrinfomics.geneway.client.home.community.CommunityWidget;
import com.nutrinfomics.geneway.client.home.measurementsPanel.MeasurementTabBarButton;
import com.nutrinfomics.geneway.client.home.measurementsPanel.MeasurementWidget;
import com.nutrinfomics.geneway.client.home.settingsPanel.SettingsTabBarButton;
import com.nutrinfomics.geneway.client.home.settingsPanel.SettingsWidgetList;
import com.nutrinfomics.geneway.client.style.Styles;

public class HomeViewImpl extends DetailsViewImpl implements HomeView {
	private TabPanel tabPanel;
	private MealsWidget mealsWidget;
//	private IngredientsWidget ingredientsWidget;
	private SettingsWidgetList settingsWidget;
	private ImageButton statusImageButton;
	
	public HomeViewImpl(){
		super();
		hideBackButton();
		showAboutButton();
		
		showHeaderPanel();
		
		initStatusButton();
		FlexPanel statusPanel = new FlexPanel();
		statusPanel.setAlignment(Alignment.CENTER);
		statusPanel.setOrientation(Orientation.HORIZONTAL);
		String nickName = ClientFactoryFactory.getClientFactory().getSession().getCustomer().getNickName();
		HTML html = new HTML(constants.welcome() + " " + nickName + " ");
		html.getElement().getStyle().setFontSize(75, Unit.PCT);
		statusPanel.add(html);
		statusPanel.add(statusImageButton);
		
		addToFirstHeaderPanel(statusPanel);
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
		
		MealsTabBarButton mealsTabBarButton = new MealsTabBarButton();
		tabPanel.add(mealsTabBarButton, mealsWidget);
//		tabPanel.add(new MealsTabBarButton(), new Label("meals widget"));


		
//	    ingredientsWidget = new IngredientsWidget();
//		tabPanel.add(new IgnredientsTabBarButton(), ingredientsWidget);
//		tabPanel.add(new IgnredientsTabBarButton(), new Label("ingredient widget"));

//	    tabPanel.add(new MyFavoritesTabBarButton(), new FavoritesWidget());

		tabPanel.add(new CommunityTabBarButton(), new CommunityWidget());
		
		MeasurementWidget measurementWidget = new MeasurementWidget();
		
		tabPanel.add(new MeasurementTabBarButton(), measurementWidget);
		
		settingsWidget = new SettingsWidgetList();
		tabPanel.add(new SettingsTabBarButton(), settingsWidget);
		
		settingsWidget.getParent().setHeight("100%");
		
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

	private void initStatusButton() {
		if(Random.nextBoolean()){
			statusImageButton = new BlinkImageButton(ImageHolder.get().warning(), "", Styles.RED, Styles.RED, Styles.BACKGROUND_COLOR_VALUE);			
		}
		else{
			statusImageButton = new AbstractImageButton(new GeneWayImageButtonAppearance(), ImageHolder.get().important(),
															"", Styles.YELLOW, Styles.YELLOW, Styles.BACKGROUND_COLOR_VALUE) {};
			statusImageButton.setSmall(true);
		}
	}

	@Override
	public ImageButton getStatusButton() {
		return statusImageButton;
	}
}