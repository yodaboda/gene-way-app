package com.nutrinfomics.geneway.client.payment;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PaymentPlace extends Place {
	public static class PaymentPlaceTokenizer implements PlaceTokenizer<PaymentPlace>{

		@Override
		public PaymentPlace getPlace(String token) {
			return new PaymentPlace();
		}

		@Override
		public String getToken(PaymentPlace place) {
			return "";
		}
		
	}
}
