package com.nutrinfomics.geneway.client.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Set;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.Panel;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.client.util.DateUtils;
import com.nutrinfomics.geneway.shared.FoodItemType;

public class SnackOrderWidgetList extends WidgetList {

	private List<String> snackSummary;
	private List<Boolean> selectedSnackSummary;
	
	
	public SnackOrderWidgetList(){
		setHeader(new HTML(ClientFactoryFactory.getClientFactory().getConstants().snackOrder()));
		snackSummary = ClientFactoryFactory.getClientFactory().getClientData().getSnackSummary();
		initSnackSummary();
	}
	
	private void initSnackSummary(){

		List<MListBox> listBoxes = new ArrayList<>(snackSummary.size());
		selectedSnackSummary = new ArrayList<>(snackSummary.size());
		
//		Dialogs.alert("snack count", "" + snackSummary.size(), null);
		
		for(int i = 0; i < snackSummary.size(); ++i){
			selectedSnackSummary.add(false);
			
			final MListBox snackSummaryBox = new MListBox();
			snackSummaryBox.getElement().getStyle().setColor(Styles.BLACK);
			listBoxes.add(snackSummaryBox);

			snackSummaryBox.getElement().setDraggable(Element.DRAGGABLE_TRUE);
			
			for(int j = 0; j < snackSummary.size(); ++j){
				String label;
				try{
					label = ClientFactoryFactory.getClientFactory().getFoodItemTypeConstants().getString(snackSummary.get(j));
				}
				catch(MissingResourceException ex){
					label = "rest snack";
				}
				snackSummaryBox.addItem(label, snackSummary.get(j));
			}
			
			snackSummaryBox.addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent event) {
		    		String snackSelected = snackSummaryBox.getValue(snackSummaryBox.getSelectedIndex());
					int indexSelected = snackSummary.indexOf(snackSelected);
					selectedSnackSummary.set(indexSelected, true);
				}
			});
			
			add(snackSummaryBox);
		}
	}
}
