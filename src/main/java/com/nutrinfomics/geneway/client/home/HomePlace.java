package com.nutrinfomics.geneway.client.home;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.nutrinfomics.geneway.server.domain.plan.Plan;

public class HomePlace extends Place {

	public static class HomePlaceTokenizer implements PlaceTokenizer<HomePlace>{

		@Override
		public HomePlace getPlace(String token) {
			return new HomePlace();
		}

		@Override
		public String getToken(HomePlace place) {
			return "";
		}
		
	}
}
