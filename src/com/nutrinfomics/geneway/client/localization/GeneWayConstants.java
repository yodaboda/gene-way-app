package com.nutrinfomics.geneway.client.localization;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.safehtml.shared.SafeHtml;

public interface GeneWayConstants extends ConstantsWithLookup {
	@DefaultStringValue("Login")
	String login();
	
	@DefaultStringValue("Register")
	String register();
	
	@DefaultStringValue("Password")
	String password();
	
	@DefaultStringValue("Username")
	String username();
	
	@DefaultStringValue("Private name")	
	String privatename();
	
	@DefaultStringValue("Family name")
	String familyname();
	
	@DefaultStringValue("Email")
	String email();
	
	@DefaultStringValue("Phone number")
	String phonenumber();
	
	@DefaultStringValue("Brith date")
	String birthdate();
	
	@DefaultStringValue("Weight")
	String weight();
	
	@DefaultStringValue("Height")
	String height();
	
	@DefaultStringValue("Confirm your password")	
	String repeatpassword();
	
	@DefaultStringValue("Gender")	
	String gender();

	@DefaultStringValue("Female")	
	String female();
	
	@DefaultStringValue("Male")	
	String male();

	@DefaultStringValue("Invalid login credentials")
	String invalidLogin();

	@DefaultStringValue("User is not authorized to login from this device")	
	String unauthorizedDevice();

	@DefaultStringValue("Username taken. Please choose a different username")
	String usernameExists();

	@DefaultStringValue("Error:")		
	String error();

	@DefaultStringValue("Registration was succesful. You will be redirected next to login page")			
	String registrationSuccessful();

	@DefaultStringValue("Next meal")
	String nextMeal();

	@DefaultStringValue("Meal taken")	
	String mealTaken();

	@DefaultStringValue("Meals")	
	String meals();

	@DefaultStringValue("Favorites")	
	String favorites();

	@DefaultStringValue("Ingredients")	
	String ingredients();

	@DefaultStringValue("Meal time :)")	
	String itsTimeToTakeYourMeal();
}
