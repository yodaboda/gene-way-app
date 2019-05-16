package com.nutrinfomics.geneway.client.home;

/*
 * Copyright (C) 2019 Firas Swidan†
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

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

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */