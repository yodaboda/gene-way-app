package com.nutrinfomics.geneway.client;

/*
 * Copyright (C) 2019 Firas Swidan†
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

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

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */