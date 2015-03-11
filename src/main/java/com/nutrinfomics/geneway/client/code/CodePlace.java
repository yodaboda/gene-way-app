package com.nutrinfomics.geneway.client.code;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CodePlace extends Place {
	public static class CodePlaceTokenizer implements PlaceTokenizer<CodePlace>{

		@Override
		public CodePlace getPlace(String token) {
			return new CodePlace();
		}

		@Override
		public String getToken(CodePlace place) {
			return "";
		}
		
	}
}
