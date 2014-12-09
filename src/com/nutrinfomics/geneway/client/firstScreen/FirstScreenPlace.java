package com.nutrinfomics.geneway.client.firstScreen;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class FirstScreenPlace extends Place {
	public static class FirstScreenPlaceTokenizer implements PlaceTokenizer<FirstScreenPlace>{

		@Override
		public FirstScreenPlace getPlace(String token) {
			return new FirstScreenPlace();
		}

		@Override
		public String getToken(FirstScreenPlace place) {
			return "";
		}
		
	}

}
