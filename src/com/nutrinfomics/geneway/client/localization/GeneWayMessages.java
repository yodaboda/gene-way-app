package com.nutrinfomics.geneway.client.localization;

import com.google.gwt.i18n.client.Messages;

public interface GeneWayMessages extends Messages {

	@DefaultMessage("{0,number} {1}")
	String foodAmount(double amount, String foodType);

}
