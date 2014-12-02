package com.nutrinfomics.geneway.client.home;

public class ArbitraryCycle implements Repetition {

	private int cycleLength;
	private int remainingLength;
	
	public ArbitraryCycle(int cycleLength){
		this.cycleLength = cycleLength;
		remainingLength = cycleLength;
	}
	
	@Override
	public int getCycleLength() {
		return cycleLength;
	}

	@Override
	public int getRemainingLength() {
		return remainingLength;
	}

	@Override
	public void advanceBySingleUnit() {
		remainingLength--;
	}

	public void setRemainingLength(int remainingLength){
		this.remainingLength = remainingLength;
	}
	
	public boolean isToBeUsed(){
		return getRemainingLength() >= 1;
	}
	
	public void reset(){
		setRemainingLength(getCycleLength());
	}
}
