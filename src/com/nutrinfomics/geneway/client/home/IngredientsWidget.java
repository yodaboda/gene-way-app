package com.nutrinfomics.geneway.client.home;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.FoodItemProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.server.domain.plan.FoodItem;
import com.nutrinfomics.geneway.server.domain.plan.Plan;
import com.nutrinfomics.geneway.server.domain.plan.Snack;
import com.nutrinfomics.geneway.shared.FoodItemType;

public class IngredientsWidget extends AbstractTabBarWidget {

//	private PlanProxy plan;
	private CellList<FoodItemType> cellList;
	
	
	public IngredientsWidget(){
//		plan = ClientFactoryFactory.getClientFactory().getPlan();
		ScrollPanel scrollPanel = new ScrollPanel();
		cellList = new CellList<>(new FoodItemTypeCell());
		scrollPanel.add(cellList);
		add(scrollPanel);

		cellList.addCellSelectedHandler(new CellSelectedHandler() {
			
			@Override
			public void onCellSelected(CellSelectedEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Set<FoodItemType> foodItemTypesList = new HashSet<>();
//		for(SnackProxy snack : plan.getSnackMenu().getSnacks()){
//			for(FoodItemProxy foodItem : snack.getFoodItems()){
//				foodItemTypesList.add(foodItem.getFoodType());
//			}
//		}
		
		cellList.render( new ArrayList<>(foodItemTypesList) );

	}
	
}
