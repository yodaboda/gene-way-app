package com.nutrinfomics.geneway.client.home.settingsPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.nutrinfomics.geneway.client.ClientData.SnackOrderSpecificationListener;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.AbstractFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.AnimalFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.FoodItemTypeFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.SnackOrderSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.EntityBaseRequest;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.shared.FoodItemType;
import com.nutrinfomics.geneway.shared.SnackProperty;

public class SnackOrderWidgetList extends WidgetList {

	private List<String> snackSummary;
	private List<Boolean> selectedSnackSummary;
	private SnackOrderSpecificationProxy snackOrderSpecification;
	
	public SnackOrderWidgetList(){
		super(new SettingsWidgetListAppearance());
		setHeader(new HTML(ClientFactoryFactory.getClientFactory().getConstants().snackOrder()));
		snackSummary = ClientFactoryFactory.getClientFactory().getClientData().getSnackSummary();
		snackOrderSpecification = ClientFactoryFactory.getClientFactory().getClientData().getSnackOrderSpecification();
		initSnackSummary();
	}
	
	private void initSnackSummary(){

		List<MListBox> listBoxes = new ArrayList<>(snackSummary.size());
		selectedSnackSummary = new ArrayList<>(snackSummary.size());
//		Dialogs.alert("snack count", "" + snackSummary.size(), null);
		
		for(int i = 0; i < snackSummary.size(); ++i){
			selectedSnackSummary.add(false);
			
			final IndexedBox snackSummaryBox = new IndexedBox(i);
			snackSummaryBox.getElement().getStyle().setColor(Styles.BLACK);
			listBoxes.add(snackSummaryBox);

			snackSummaryBox.getElement().setDraggable(Element.DRAGGABLE_TRUE);
			
			snackSummaryBox.addItem(ClientFactoryFactory.getClientFactory().getConstants().auto(), "auto");

			AbstractFoodSpecificationProxy foodSpecification = snackOrderSpecification.getSnackOrderSpecification().get(i);
			
			for(int j = 0; j < snackSummary.size(); ++j){
				String label;
				try{
					label = ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(snackSummary.get(j));
					snackSummaryBox.addItem(label, snackSummary.get(j));
					if(foodSpecification instanceof FoodItemTypeFoodSpecificationProxy){
						FoodItemTypeFoodSpecificationProxy foodSpecificationFoodItem = (FoodItemTypeFoodSpecificationProxy) foodSpecification;
						if(foodSpecificationFoodItem.getFoodItemType() == FoodItemType.valueOf(snackSummary.get(j))){
							snackSummaryBox.setSelectedIndex(snackSummaryBox.getItemCount() - 1);
						}
					}
				}
				catch(MissingResourceException ex){
					label = ClientFactoryFactory.getClientFactory().getMiscConstants().REST();
					snackSummaryBox.addItem(label, snackSummary.get(j));
					if(foodSpecification instanceof AnimalFoodSpecificationProxy){
						snackSummaryBox.setSelectedIndex(snackSummaryBox.getItemCount() - 1);
					}
				}
			}

			snackSummaryBox.addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent event) {
		    		String snackSelected = snackSummaryBox.getValue(snackSummaryBox.getSelectedIndex());
					int indexSelected = snackSummary.indexOf(snackSelected);
					selectedSnackSummary.set(indexSelected, true);
					updateSnackSpecification(snackSummaryBox.getIndex(), snackSelected);
				}
			});
			
			add(snackSummaryBox);
		}
	}
	
	private void updateSnackSpecification(int snackIndex, String snackSelected){
		EntityBaseRequest entityBaseRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().entityBaseRequest();
		SnackOrderSpecificationProxy snackOrderSpecificationEdit = entityBaseRequest.edit(snackOrderSpecification);
		if(snackSelected.equals(SnackProperty.REST.toString())){
			AnimalFoodSpecificationProxy animalFoodSpecificationProxy = entityBaseRequest.create(AnimalFoodSpecificationProxy.class);
			snackOrderSpecificationEdit.getSnackOrderSpecification().set(snackIndex, animalFoodSpecificationProxy);
		}
		else{
			FoodItemTypeFoodSpecificationProxy foodItemTypeSpecification = entityBaseRequest.create(FoodItemTypeFoodSpecificationProxy.class);
			FoodItemType foodItemType = FoodItemType.valueOf(snackSelected);
			foodItemTypeSpecification.setFoodItemType(foodItemType);
			snackOrderSpecificationEdit.getSnackOrderSpecification().set(snackIndex, foodItemTypeSpecification);			
		}
		
		entityBaseRequest.merge(snackOrderSpecificationEdit).fire(new GeneWayReceiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				ClientFactoryFactory.getClientFactory().getClientData().requestSnackOrderSpecification(new SnackOrderSpecificationListener() {
					@Override
					public void snackOrderSpecification(SnackOrderSpecificationProxy snackOrderSpecification) {
						setSnackOrderSpecification(snackOrderSpecification);
					}
				});
			}
		});
	}
	
	private void setSnackOrderSpecification(SnackOrderSpecificationProxy snackOrderSpecification){
		this.snackOrderSpecification = snackOrderSpecification;
	}
	
	@Override
	public void add(Widget w) {
		super.add(w);
		//hacking WidgetListEntry to force it take customized CSS into consideration.
		setSelectAble(getWidgetCount() - 1, true);
	}

}
