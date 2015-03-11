package com.nutrinfomics.geneway.client.home;

import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.SnackProxy;


public class SnackTracker {
	public enum State{
		EATEN, CURRENT, TO_FOLLOW
	}
	private SnackProxy snack;
	private State state;
	
	public SnackTracker(SnackProxy snack){
		this(snack, State.TO_FOLLOW);
	}
	
	public SnackTracker(SnackProxy snack, State state){
		this.snack = snack;
		this.state = state;
	}
	
	public SnackProxy getSnack(){
		return snack;
	}
	
	public State getState(){
		return state;
	}
	
	public void setState(State state){
		this.state = state;
	}
}
