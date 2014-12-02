package com.nutrinfomics.geneway.client.home;

import com.nutrinfomics.geneway.server.domain.plan.Snack;

public class SnackTracker {
	public enum State{
		EATEN, CURRENT, TO_FOLLOW
	}
	private Snack snack;
	private State state;
	
	public SnackTracker(Snack snack){
		this(snack, State.TO_FOLLOW);
	}
	
	public SnackTracker(Snack snack, State state){
		this.snack = snack;
		this.state = state;
	}
	
	public Snack getSnack(){
		return snack;
	}
	
	public State getState(){
		return state;
	}
	
	public void setState(State state){
		this.state = state;
	}
}
