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

	@DefaultStringValue("You have entered wrong credentials")
	String invalidLogin();

	@DefaultStringValue("Invalid login")
	String invalidLoginTitle();

	
	@DefaultStringValue("User is not authorized to login from this device")	
	String unauthorizedDevice();

	@DefaultStringValue("Unauthorized Device")	
	String unauthorizedDeviceTitle();

	
	@DefaultStringValue("Username taken. Please choose a different username")
	String usernameExists();

	@DefaultStringValue("Error")		
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

	@DefaultStringValue("Please take your snack - it is about time")	
	String itsTimeToTakeYourMeal();

	@DefaultStringValue("Snack time")	
	String itsTimeToTakeYourMealTitle();
	
	
	@DefaultStringValue("Existing Account")
	String existingAccount();

	@DefaultStringValue("Join Now")
	String newAccount();
	
	@DefaultStringValue("Food Item")
	String foodItem();

	@DefaultStringValue("Reem")
	String reem();

	@DefaultStringValue("Lost 35 KG")
	String lost35kg();

	@DefaultStringValue("In 7 months")
	String in7Months();

	@DefaultStringValue("You?")
	String youQM();

	@DefaultStringValue("Time")
	String timeInTimeForChange();

	@DefaultStringValue("for")
	String forInTimeForChange();

	@DefaultStringValue("change")
	String changeInTimeForChange();

	@DefaultStringValue("Settings")
	String settings();

	@DefaultStringValue("Hours btw snacks")
	String timeBetweenMeals();

	@DefaultStringValue("Send me SMS alerts")
	String smsSnackAlerts();
}
