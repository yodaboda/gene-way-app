package com.nutrinfomics.geneway.client;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackHistoryProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.EntityBaseRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.util.DateUtils;
import com.nutrinfomics.geneway.shared.FoodItemType;
import com.nutrinfomics.geneway.shared.SnackStatus;

public class ClientData {
	private List<String> menuSummary;
	private Set<FoodItemType> ingredients;
	private SnackProxy nextSnack;
	private boolean snackForTomorrow;
	
	public interface NextSnackListener{
		public void nextSnack(SnackProxy snackProxy, boolean snackForTommorow);
	}

	public interface IngredientsListener{
		public void ingredinets(Set<FoodItemType> foodTypes);
	}
	
	public interface MenuSummaryListener{
		public void menuSummary(List<String> menuSummary);
	}
	
	public List<String> getSnackSummary() {
		return menuSummary;
	}
	public void setMenuSummary(List<String> menuSummary) {
		this.menuSummary = menuSummary;
	}
	public Set<FoodItemType> getIngredients() {
		return ingredients;
	}
	private void setIngredients(Set<FoodItemType> ingredients){
		this.ingredients = ingredients;
	}
	public void requestIngredients(final IngredientsListener ingredientsListener){
		PlanRequest planRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest();
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().getNewSession(planRequest);
		planRequest.getIngredients(sessionProxy, DateUtils.getDate(0)).fire(new GeneWayReceiver<Set<FoodItemType>>() {
			@Override
			public void onFailure(ServerFailure error) {
				Dialogs.alert(ClientFactoryFactory.getClientFactory().getConstants().error(),error.getMessage(), null);
			}
			@Override
			public void onSuccess(Set<FoodItemType> foodTypes) {
				setIngredients(foodTypes);
				ingredientsListener.ingredinets(foodTypes);
			}
		});

	}
	
	public void requestMenuSummary(final MenuSummaryListener menuSummaryListener){
		PlanRequest planRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest();
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().getNewSession(planRequest);
		planRequest.getMenuSummary(sessionProxy, DateUtils.getDate(0)).fire(new GeneWayReceiver<List<String>>() {
			@Override
			public void onFailure(ServerFailure error) {
				Dialogs.alert(ClientFactoryFactory.getClientFactory().getConstants().error(),error.getMessage(), null);
			}
			@Override
			public void onSuccess(List<String> menuSummary) {
				setMenuSummary(menuSummary);
				menuSummaryListener.menuSummary(menuSummary);
			}
		});
	}
	
	private void requestNextSnack(final int daysOffset, final NextSnackListener nextSnackListener) {
		PlanRequest planRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest();
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().getNewSession(planRequest);
		Request<SnackProxy> nextSnackRequest = planRequest.getNextSnack(sessionProxy, DateUtils.getDate(daysOffset));
		nextSnackRequest.with("foodItems", "snackProperty", "time","foodItems.measurementUnit", "foodItems.foodType");
		nextSnackRequest.fire(new GeneWayReceiver<SnackProxy>() {
			@Override
			public void onFailure(ServerFailure error) {
				Dialogs.alert(ClientFactoryFactory.getClientFactory().getConstants().error(),error.getMessage(), null);
			}
			@Override
			public void onSuccess(SnackProxy snackProxy) {
				if(snackProxy.getSnackProperty() == null){ // get next day snack
					requestNextSnack(1, nextSnackListener);
				}
				else{
					setNextSnack(snackProxy);
					setSnackForTomorrow(daysOffset == 1);
					nextSnackListener.nextSnack(snackProxy, isSnackForTomorrow());
				}
			}
		});
	}

	public void persistCurrentSnack(SnackProxy snackProxy, SnackStatus snackStatus, final NextSnackListener nextSnackListener) {
		if(snackProxy == null){
			requestNextSnack(0, nextSnackListener);
			return;
		}
		
		EntityBaseRequest snackHistoryRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().entityBaseRequest();
		Date timestamp = new Date();
		SnackHistoryProxy snackHistoryProxy = snackHistoryRequest.create(SnackHistoryProxy.class);
		snackHistoryProxy.setEatenSnack(snackProxy);	
		snackHistoryProxy.setDayString(DateUtils.getDate(0));
		snackHistoryProxy.setStatus(snackStatus);
		snackHistoryProxy.setTimestamp(timestamp);
		snackHistoryProxy.setTimeZoneDiff(timestamp.getTimezoneOffset());
		snackHistoryRequest.persist().using(snackHistoryProxy).fire(new GeneWayReceiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestNextSnack(0, nextSnackListener);
			}
		});
	}
	public SnackProxy getNextSnack() {
		return nextSnack;
	}
	private void setNextSnack(SnackProxy nextSnack) {
		this.nextSnack = nextSnack;
	}
	public boolean isSnackForTomorrow() {
		return snackForTomorrow;
	}
	private void setSnackForTomorrow(boolean snackForTomorrow) {
		this.snackForTomorrow = snackForTomorrow;
	}

}
