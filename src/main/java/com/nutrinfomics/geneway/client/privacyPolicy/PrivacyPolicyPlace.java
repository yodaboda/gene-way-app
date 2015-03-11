package com.nutrinfomics.geneway.client.privacyPolicy;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PrivacyPolicyPlace extends Place {
	public static class PrivacyPolicyPlaceTokenizer implements PlaceTokenizer<PrivacyPolicyPlace>{

		@Override
		public PrivacyPolicyPlace getPlace(String token) {
			return new PrivacyPolicyPlace();
		}

		@Override
		public String getToken(PrivacyPolicyPlace place) {
			return "";
		}
		
	}
}
