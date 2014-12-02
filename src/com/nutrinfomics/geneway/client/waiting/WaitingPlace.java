package com.nutrinfomics.geneway.client.waiting;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WaitingPlace extends Place {
	public static class WaitingPlaceTokenizer implements PlaceTokenizer<WaitingPlace>{

		@Override
		public WaitingPlace getPlace(String token) {
			return new WaitingPlace();
		}

		@Override
		public String getToken(WaitingPlace place) {
			return "";
		}
		
	}
}
