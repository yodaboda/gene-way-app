package com.nutrinfomics.geneway.client.home;

import java.util.Vector;

import com.nutrinfomics.geneway.client.requestFactory.proxy.plan.WeeklyBehaving;

public class WeeklyCycle extends ArbitraryCycle{
	private static final int CYCLE_LENGTH = 7;

	private Vector<WeeklyBehaving> weeklyBehavings = new Vector<>();
	
	public WeeklyCycle(){
		super(CYCLE_LENGTH);
	}
	
	@Override
	public void advanceBySingleUnit() {
		super.advanceBySingleUnit();
		if(getRemainingLength() < 1){
			setRemainingLength(CYCLE_LENGTH);; //restart
			for(WeeklyBehaving weeklyBehaving : weeklyBehavings)
				weeklyBehaving.weeklyReset();
		}
		else{
			for(WeeklyBehaving weeklyBehaving : weeklyBehavings)
				weeklyBehaving.nextDay();
		}
	}
	
	public void addWeeklyBehaving(WeeklyBehaving weeklyBehaving){
		weeklyBehavings.add(weeklyBehaving);
	}
}
