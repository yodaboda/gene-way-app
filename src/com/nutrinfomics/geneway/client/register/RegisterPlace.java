package com.nutrinfomics.geneway.client.register;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class RegisterPlace extends Place {
	public static class RegisterPlaceTokenizer implements PlaceTokenizer<RegisterPlace>{

		@Override
		public RegisterPlace getPlace(String token) {
			return new RegisterPlace();
		}

		@Override
		public String getToken(RegisterPlace place) {
			return "";
		}
		
	}
}
