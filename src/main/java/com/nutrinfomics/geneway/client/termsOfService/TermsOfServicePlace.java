package com.nutrinfomics.geneway.client.termsOfService;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TermsOfServicePlace extends Place {
	public static class TermsOfSevicePlaceTokenizer implements PlaceTokenizer<TermsOfServicePlace>{

		@Override
		public TermsOfServicePlace getPlace(String token) {
			return new TermsOfServicePlace();
		}

		@Override
		public String getToken(TermsOfServicePlace place) {
			return "";
		}
		
	}

}
