package com.nutrinfomics.geneway.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.nutrinfomics.geneway.client.about.AboutPlace.AboutPlaceTokenizer;
import com.nutrinfomics.geneway.client.code.CodePlace.CodePlaceTokenizer;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenPlace.FirstScreenPlaceTokenizer;
import com.nutrinfomics.geneway.client.home.HomePlace.HomePlaceTokenizer;
import com.nutrinfomics.geneway.client.ingredients.IngredientsPlace.IngredientsPlaceTokenizer;
import com.nutrinfomics.geneway.client.login.LoginPlace.LoginPlaceTokenizer;
import com.nutrinfomics.geneway.client.payment.PaymentPlace.PaymentPlaceTokenizer;
import com.nutrinfomics.geneway.client.personalDetails.PersonalDetailsPlace.PersonalDetailsPlaceTokenizer;
import com.nutrinfomics.geneway.client.personalIdentifier.PersonalIdentifierPlace.PersonalIdentifierPlaceTokenizer;
import com.nutrinfomics.geneway.client.privacyPolicy.PrivacyPolicyPlace.PrivacyPolicyPlaceTokenizer;
import com.nutrinfomics.geneway.client.register.RegisterPlace.RegisterPlaceTokenizer;
import com.nutrinfomics.geneway.client.status.StatusPlace.StatusPlaceTokenizer;
import com.nutrinfomics.geneway.client.termsOfService.TermsOfServicePlace.TermsOfSevicePlaceTokenizer;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace.WaitingPlaceTokenizer;

@WithTokenizers({LoginPlaceTokenizer.class, RegisterPlaceTokenizer.class, HomePlaceTokenizer.class,
				WaitingPlaceTokenizer.class, AboutPlaceTokenizer.class, FirstScreenPlaceTokenizer.class,
				IngredientsPlaceTokenizer.class, CodePlaceTokenizer.class, 
				TermsOfSevicePlaceTokenizer.class, PrivacyPolicyPlaceTokenizer.class,
				PersonalDetailsPlaceTokenizer.class, StatusPlaceTokenizer.class, 
				PaymentPlaceTokenizer.class, PersonalIdentifierPlaceTokenizer.class})
public interface GeneWayPlaceHistoryMapper extends PlaceHistoryMapper{
}
