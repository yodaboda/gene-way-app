package com.nutrinfomics.geneway.client.home.settingsPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.nutrinfomics.geneway.client.ClientData.SnackOrderSpecificationListener;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.AbstractFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.AcceptAllSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.AnimalFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.FoodItemTypeFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.SnackOrderSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.EntityBaseRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.shared.FoodItemType;
import com.nutrinfomics.geneway.shared.SnackProperty;

public class SnackOrderWidgetList extends WidgetList {

	private static final String AUTO = "auto";
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
			
			snackSummaryBox.addItem(ClientFactoryFactory.getClientFactory().getConstants().auto(), AUTO);

			//first added boxes show up at the bottom
			AbstractFoodSpecificationProxy foodSpecification = snackOrderSpecification.getFoodOrderSpecification().get(i);
			
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
		    		if(snackSelected != AUTO){
						int indexSelected = snackSummary.indexOf(snackSelected);
						selectedSnackSummary.set(indexSelected, true);
		    		}
					updateSnackSpecification(snackSummaryBox.getIndex(), snackSelected);
				}
			});
			
			add(snackSummaryBox);
		}
	}
	
	private void updateSnackSpecification(int snackIndex, String snackSelected){
		Window.alert("snack index: " + snackIndex);
		PlanRequest planRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest();
		SnackOrderSpecificationProxy snackOrderSpecificationEdit = planRequest.edit(snackOrderSpecification);
		AbstractFoodSpecificationProxy oldFoodSpecification = snackOrderSpecificationEdit.getFoodOrderSpecification().get(snackIndex);;
		if(snackSelected.equals(SnackProperty.REST.toString())){
			AnimalFoodSpecificationProxy animalFoodSpecificationProxy = planRequest.create(AnimalFoodSpecificationProxy.class);
			snackOrderSpecificationEdit.getFoodOrderSpecification().set(snackIndex, animalFoodSpecificationProxy);
		}
		else if(snackSelected == AUTO){
			AcceptAllSpecificationProxy acceptAllSpecificationProxy = planRequest.create(AcceptAllSpecificationProxy.class);
			snackOrderSpecificationEdit.getFoodOrderSpecification().set(snackIndex, acceptAllSpecificationProxy);
		}
		else{
			FoodItemTypeFoodSpecificationProxy foodItemTypeSpecification = planRequest.create(FoodItemTypeFoodSpecificationProxy.class);
			FoodItemType foodItemType = FoodItemType.valueOf(snackSelected);
			foodItemTypeSpecification.setFoodItemType(foodItemType);
			snackOrderSpecificationEdit.getFoodOrderSpecification().set(snackIndex, foodItemTypeSpecification);			
		}

		planRequest.updateSpecifications(snackOrderSpecificationEdit, oldFoodSpecification).fire(new GeneWayReceiver<Void>() {
			@Override
			public void onFailure(ServerFailure error) {
				Dialogs.alert(ClientFactoryFactory.getClientFactory().getConstants().error(),error.getMessage(), null);
			}
			
			@Override
			public void onSuccess(Void response) {
				ClientFactoryFactory.getClientFactory().getClientData().requestSnackOrderSpecification(new SnackOrderSpecificationListener() {
					@Override
					public void snackOrderSpecification(SnackOrderSpecificationProxy snackOrderSpecification) {
						List<AbstractFoodSpecificationProxy> foodOrderSpecification = snackOrderSpecification.getFoodOrderSpecification();
						String display = "";
						for(AbstractFoodSpecificationProxy foodSpecification : foodOrderSpecification){
							display += foodSpecification.getClass().getName() + " ";
						}
						Window.alert(display);
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
