package com.nutrinfomics.geneway.client.personalDetails;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PersonalDetailsPlace extends Place {
	public static class PersonalDetailsPlaceTokenizer implements PlaceTokenizer<PersonalDetailsPlace>{

		@Override
		public PersonalDetailsPlace getPlace(String token) {
			return new PersonalDetailsPlace();
		}

		@Override
		public String getToken(PersonalDetailsPlace place) {
			return "";
		}
		
	}
}
