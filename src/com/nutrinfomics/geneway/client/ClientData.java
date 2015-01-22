package com.nutrinfomics.geneway.client;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.nutrinfomics.geneway.client.ClientData.PlanPreferencesListener;
import com.nutrinfomics.geneway.client.requestFactory.GeneWayReceiver;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.device.SessionProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanPreferencesProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackHistoryProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.PlanPreferencesProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.AbstractFoodSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.specification.SnackOrderSpecificationProxy;
import com.nutrinfomics.geneway.client.requestFactory.request.EntityBaseRequest;
import com.nutrinfomics.geneway.client.requestFactory.request.PlanRequest;
import com.nutrinfomics.geneway.client.util.DateUtils;
import com.nutrinfomics.geneway.server.domain.specification.SnackOrderSpecification;
import com.nutrinfomics.geneway.shared.FoodItemType;
import com.nutrinfomics.geneway.shared.SnackStatus;

public class ClientData {
	private List<String> menuSummary;
	private Set<FoodItemType> ingredients;
	private SnackProxy nextSnack;
	private boolean snackForTomorrow;
	private PlanPreferencesProxy planPreferences;
	private List<String> snackOrder;
	private SnackOrderSpecificationProxy snackOrderSpecification;
	
	public interface NextSnackListener{
		public void nextSnack(SnackProxy snackProxy, boolean snackForTommorow);
	}

	public interface IngredientsListener{
		public void ingredinets(Set<FoodItemType> foodTypes);
	}
	
	public interface MenuSummaryListener{
		public void menuSummary(List<String> menuSummary);
	}
	
	public interface PlanPreferencesListener{
		public void planPreferences(PlanPreferencesProxy planPreferences);
	}
	
	public interface SnackOrderSpecificationListener{
		public void snackOrderSpecification(SnackOrderSpecificationProxy foodSpecification);
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

	public void findPlanPreferences(PlanPreferencesProxy planPreferences2,
			final PlanPreferencesListener planPreferencesListener) {
		Request<PlanPreferencesProxy> findRequest = (Request<PlanPreferencesProxy>) ClientFactoryFactory.getClientFactory().getRequestFactory().entityBaseRequest().find(planPreferences.stableId());
		findRequest.with("snackTimes");
		findRequest.fire(new GeneWayReceiver<PlanPreferencesProxy>() {
			@Override
			public void onSuccess(PlanPreferencesProxy planPreferencesProxy) {
				setPlanPreferences(planPreferencesProxy);
				planPreferencesListener.planPreferences(planPreferencesProxy);
			}
		});
	}

	
	public void requestPlanPreferences(final PlanPreferencesListener planPreferencesListener){
		PlanRequest planRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest();
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().createNewSession(planRequest);
		Request<PlanPreferencesProxy> planPreferencesRequest = planRequest.getPlanPreferences(sessionProxy);
		planPreferencesRequest.with("snackTimes");
		planPreferencesRequest.fire(new GeneWayReceiver<PlanPreferencesProxy>() {
			@Override
			public void onFailure(ServerFailure error) {
				Dialogs.alert(ClientFactoryFactory.getClientFactory().getConstants().error(),error.getMessage(), null);
			}
			@Override
			public void onSuccess(PlanPreferencesProxy planPreferences) {
				setPlanPreferences(planPreferences);
				planPreferencesListener.planPreferences(planPreferences);
			}
		});
	}
	
	public void requestIngredients(final IngredientsListener ingredientsListener){
		PlanRequest planRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest();
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().createNewSession(planRequest);
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
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().createNewSession(planRequest);
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
	
	public void requestSnackOrderSpecification(final SnackOrderSpecificationListener snackOrderSpecificationListener){
		PlanRequest planRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest();
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().getSession();
		planRequest.getSnackOrderSpecification(sessionProxy).fire(new GeneWayReceiver<SnackOrderSpecificationProxy>() {

			@Override
			public void onSuccess(SnackOrderSpecificationProxy foodSpecification) {
				setSnackOrderSpecification(foodSpecification);
				snackOrderSpecificationListener.snackOrderSpecification(foodSpecification);
			}
		});
	}
	
	private void requestNextSnack(final int daysOffset, final NextSnackListener nextSnackListener) {
		PlanRequest planRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest();
		SessionProxy sessionProxy = ClientFactoryFactory.getClientFactory().createNewSession(planRequest);
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
		PlanRequest planRequest = ClientFactoryFactory.getClientFactory().getRequestFactory().planRequest();
		Date timestamp = new Date();
		SnackHistoryProxy snackHistoryProxy = planRequest.create(SnackHistoryProxy.class);
		snackHistoryProxy.setEatenSnack(snackProxy);	
		snackHistoryProxy.setPlannedSnack(snackProxy);
		snackHistoryProxy.setDayString(DateUtils.getDate(0));
		snackHistoryProxy.setStatus(snackStatus);
		snackHistoryProxy.setTimestamp(timestamp);
		snackHistoryProxy.setTimeZoneDiff(timestamp.getTimezoneOffset());
		CustomerProxy customer = ClientFactoryFactory.getClientFactory().getSession().getCustomer();
		CustomerProxy sameRequestCustomer = planRequest.edit(customer);
		snackHistoryProxy.setCustomer(sameRequestCustomer);
		planRequest.markCurrentSnack(ClientFactoryFactory.getClientFactory().getSession(), snackProxy, snackHistoryProxy).fire(new GeneWayReceiver<Void>() {
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
	public PlanPreferencesProxy getPlanPreferences() {
		return planPreferences;
	}
	public void setPlanPreferences(PlanPreferencesProxy planPreferences) {
		this.planPreferences = planPreferences;
	}
	public List<String> getSnackOrder() {
		return snackOrder;
	}
	public void setSnackOrder(List<String> snackOrder) {
		this.snackOrder = snackOrder;
	}
	public SnackOrderSpecificationProxy getSnackOrderSpecification() {
		return snackOrderSpecification;
	}
	public void setSnackOrderSpecification(SnackOrderSpecificationProxy snackOrderSpecification) {
		this.snackOrderSpecification = snackOrderSpecification;
	}
}
