package com.nutrinfomics.geneway.client.personalIdentifier;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;


public class PersonalIdentifierPlace extends Place {
	public static class PersonalIdentifierPlaceTokenizer implements PlaceTokenizer<PersonalIdentifierPlace>{

		@Override
		public PersonalIdentifierPlace getPlace(String token) {
			return new PersonalIdentifierPlace();
		}

		@Override
		public String getToken(PersonalIdentifierPlace place) {
			return "";
		}
		
	}
}
