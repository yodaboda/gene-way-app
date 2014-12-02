package com.nutrinfomics.geneway.client.home;

public interface Repetition {
	public int getCycleLength();
	public int getRemainingLength();
	public void advanceBySingleUnit();
}
