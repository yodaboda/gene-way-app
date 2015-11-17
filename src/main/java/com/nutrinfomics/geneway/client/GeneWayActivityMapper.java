package com.nutrinfomics.geneway.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.nutrinfomics.geneway.client.about.AboutActivity;
import com.nutrinfomics.geneway.client.about.AboutPlace;
import com.nutrinfomics.geneway.client.code.CodeActivity;
import com.nutrinfomics.geneway.client.code.CodePlace;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenActivity;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenPlace;
import com.nutrinfomics.geneway.client.home.HomeActivity;
import com.nutrinfomics.geneway.client.home.HomePlace;
import com.nutrinfomics.geneway.client.home.IgnredientsTabBarButton;
import com.nutrinfomics.geneway.client.ingredients.IngredientsActivity;
import com.nutrinfomics.geneway.client.ingredients.IngredientsPlace;
import com.nutrinfomics.geneway.client.login.LoginActivity;
import com.nutrinfomics.geneway.client.login.LoginPlace;
import com.nutrinfomics.geneway.client.nda.NDAActivity;
import com.nutrinfomics.geneway.client.nda.NDAPlace;
import com.nutrinfomics.geneway.client.payment.PaymentActivity;
import com.nutrinfomics.geneway.client.payment.PaymentPlace;
import com.nutrinfomics.geneway.client.personalDetails.PersonalDetailsActivity;
import com.nutrinfomics.geneway.client.personalDetails.PersonalDetailsPlace;
import com.nutrinfomics.geneway.client.personalIdentifier.PersonalIdentifierActivity;
import com.nutrinfomics.geneway.client.personalIdentifier.PersonalIdentifierPlace;
import com.nutrinfomics.geneway.client.privacyPolicy.PrivacyPolicyActivity;
import com.nutrinfomics.geneway.client.privacyPolicy.PrivacyPolicyPlace;
import com.nutrinfomics.geneway.client.register.RegisterActivity;
import com.nutrinfomics.geneway.client.register.RegisterPlace;
import com.nutrinfomics.geneway.client.status.StatusActivity;
import com.nutrinfomics.geneway.client.status.StatusPlace;
import com.nutrinfomics.geneway.client.termsOfService.TermsOfServiceActivity;
import com.nutrinfomics.geneway.client.termsOfService.TermsOfServicePlace;
import com.nutrinfomics.geneway.client.waiting.WaitingActivity;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace;

public class GeneWayActivityMapper implements ActivityMapper {

	@Override
	public Activity getActivity(Place place) {
		if(place instanceof LoginPlace){
			return new LoginActivity();
		}
		else if(place instanceof RegisterPlace){
			return new RegisterActivity();
		}
		else if(place instanceof HomePlace){ 
			return new HomeActivity();
		}
		else if(place instanceof AboutPlace){
			return new AboutActivity();
		}
		else if(place instanceof WaitingPlace){
			return new WaitingActivity();
		}
		else if(place instanceof FirstScreenPlace){
			return new FirstScreenActivity();
		}
		else if(place instanceof IngredientsPlace){
			return new IngredientsActivity();
		}
		else if(place instanceof CodePlace){
			return new CodeActivity();
		}
		else if(place instanceof TermsOfServicePlace){
			return new TermsOfServiceActivity();
		}
		else if(place instanceof PrivacyPolicyPlace){
			return new PrivacyPolicyActivity();
		}
		else if(place instanceof PersonalDetailsPlace){
			return new PersonalDetailsActivity();
		}
		else if(place instanceof StatusPlace){
			return new StatusActivity();
		}
		else if(place instanceof PaymentPlace){
			return new PaymentActivity();
		}
		else if(place instanceof PersonalIdentifierPlace){
			return new PersonalIdentifierActivity();
		}
		else if(place instanceof NDAPlace){
			return new NDAActivity();
		}
		return null;
	}

}
