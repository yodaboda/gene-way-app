package com.nutrinfomics.geneway.client.nda;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NDAPlace extends Place {
	public static class NDAPlaceTokenizer implements PlaceTokenizer<NDAPlace>{

		@Override
		public NDAPlace getPlace(String token) {
			return new NDAPlace();
		}

		@Override
		public String getToken(NDAPlace place) {
			return "";
		}
		
	}
}
