package com.nutrinfomics.geneway.client.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.Timer;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.input.MTextArea;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.client.util.DateUtils;
import com.nutrinfomics.geneway.shared.FoodItemType;

public class IngredientsWidget extends MTextArea {

//	private PlanProxy plan;
//	private CellList<FoodItemType> cellList;
//	private MTextArea textArea;
	
	public IngredientsWidget(){
		
//		ScrollPanel scrollPanel = new ScrollPanel();
//		cellList = new CellList<>(new FoodItemTypeCell());
//		add(cellList);
//		scrollPanel.add(cellList);
//		add(scrollPanel);
//
//		cellList.addCellSelectedHandler(new CellSelectedHandler() {
//			
//			@Override
//			public void onCellSelected(CellSelectedEvent event) {
//				// TODO Auto-generated method stub
//				
//			}
//		});

//		textArea = new MTextArea();
//		add(textArea);
		setHeight("100%");
		getElement().getStyle().setColor(Styles.BLACK);
		getElement().getStyle().setBackgroundColor(Styles.WHITE);
		box.getElement().getStyle().setColor(Styles.BLACK);
		init();

	}


	private void init(){
		List<FoodItemType> foodItemTypesList = new ArrayList<>(ClientFactoryFactory.getClientFactory().getClientData().getIngredients());
		Collections.sort(foodItemTypesList, new Comparator<FoodItemType>() {
			@Override
	        public int compare(FoodItemType f1, FoodItemType f2) {
				String foodItemType1LocaleName = ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(f1.toString());
				String foodItemType2LocaleName = ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(f2.toString());
				return foodItemType1LocaleName.compareTo(foodItemType2LocaleName);
			}
		});

		int count = 0;
		String text = "";
		for(FoodItemType foodItem : foodItemTypesList){
			if(count == 2){
				text += "\n";
				count = 0;
			}
			if(count > 0){
				text += "\t\t";
			}
			String textToAdd = ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(foodItem.toString());
			if(count == 0){
				if(textToAdd.length() < 6){
					textToAdd += "      ";
				}
			}
			text += textToAdd;
			count++;
		}

		setText(text);

		//					cellList.render( foodItemTypesList );

	}
}
