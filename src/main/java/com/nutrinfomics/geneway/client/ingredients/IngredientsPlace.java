package com.nutrinfomics.geneway.client.ingredients;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class IngredientsPlace extends Place {

	public static class IngredientsPlaceTokenizer implements PlaceTokenizer<IngredientsPlace>{

		@Override
		public IngredientsPlace getPlace(String token) {
			return new IngredientsPlace();
		}

		@Override
		public String getToken(IngredientsPlace place) {
			return "";
		}
		
	}
}
