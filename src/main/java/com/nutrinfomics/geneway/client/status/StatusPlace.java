package com.nutrinfomics.geneway.client.status;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class StatusPlace extends Place {
	public static class StatusPlaceTokenizer implements PlaceTokenizer<StatusPlace>{

		@Override
		public StatusPlace getPlace(String token) {
			return new StatusPlace();
		}

		@Override
		public String getToken(StatusPlace place) {
			return "";
		}
		
	}
}
