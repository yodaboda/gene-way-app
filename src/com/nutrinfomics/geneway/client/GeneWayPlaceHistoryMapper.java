package com.nutrinfomics.geneway.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.nutrinfomics.geneway.client.about.AboutPlace.AboutPlaceTokenizer;
import com.nutrinfomics.geneway.client.firstScreen.FirstScreenPlace.FirstScreenPlaceTokenizer;
import com.nutrinfomics.geneway.client.home.HomePlace.HomePlaceTokenizer;
import com.nutrinfomics.geneway.client.ingredients.IngredientsPlace.IngredientsPlaceTokenizer;
import com.nutrinfomics.geneway.client.login.LoginPlace.LoginPlaceTokenizer;
import com.nutrinfomics.geneway.client.register.RegisterPlace.RegisterPlaceTokenizer;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace.WaitingPlaceTokenizer;

@WithTokenizers({LoginPlaceTokenizer.class, RegisterPlaceTokenizer.class, HomePlaceTokenizer.class,
				WaitingPlaceTokenizer.class, AboutPlaceTokenizer.class, FirstScreenPlaceTokenizer.class,
				IngredientsPlaceTokenizer.class})
public interface GeneWayPlaceHistoryMapper extends PlaceHistoryMapper{
}
