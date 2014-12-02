package com.nutrinfomics.geneway.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.nutrinfomics.geneway.client.about.AboutActivity;
import com.nutrinfomics.geneway.client.about.AboutPlace;
import com.nutrinfomics.geneway.client.home.HomeActivity;
import com.nutrinfomics.geneway.client.home.HomePlace;
import com.nutrinfomics.geneway.client.login.LoginActivity;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.client.register.RegisterActivity;
import com.nutrinfomics.geneway.client.register.RegisterPlace;
import com.nutrinfomics.geneway.client.waiting.WaitingActivity;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace;

public class GeneWayActivityMapper implements ActivityMapper {

	@Override
	public Activity getActivity(Place place) {
		if(place instanceof LoginPlace){
			return new LoginActivity();
		}
		else if(place instanceof RegisterPlace){
			return new RegisterActivity();
		}
		else if(place instanceof HomePlace){ 
			return new HomeActivity();
		}
		else if(place instanceof AboutPlace){
			return new AboutActivity();
		}
		else if(place instanceof WaitingPlace){
			return new WaitingActivity();
		}
		return null;
	}

}
