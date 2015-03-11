package com.nutrinfomics.geneway.client.util;

import java.util.Date;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;

public class LanguageUtils {

	static public void initializeLanguageBox(final MListBox languageBox) {
		final String cookieName=LocaleInfo.getLocaleCookieName();
	    final String queryParam=LocaleInfo.getLocaleQueryParam();
	    if (cookieName == null && queryParam == null) {
//	      localeSelectionCell.getStyle().setDisplay(Display.NONE);
	      return;
	    }
	    String currentLocale=LocaleInfo.getCurrentLocale().getLocaleName();
	    if (currentLocale.equals("default")) {
	    	currentLocale="en";
	    }
	    String[] localeNames=LocaleInfo.getAvailableLocaleNames();
	    for (  String localeName : localeNames) {
	    	if(!localeName.equals("default")){
	    		String nativeName=LocaleInfo.getLocaleNativeDisplayName(localeName);
	    		languageBox.addItem(nativeName,localeName);
	    		if (localeName.equals(currentLocale)) {
	    			languageBox.setSelectedIndex(languageBox.getItemCount() - 1);
	    		}
	    	}
	    }
	    languageBox.addChangeHandler(new ChangeHandler() {

	    	@Override
	    	public void onChange(ChangeEvent event) {
	    		String localeName=languageBox.getValue(languageBox.getSelectedIndex());
	    		if (cookieName != null) {
	    			Date expires=new Date();
	    			expires.setYear(expires.getYear() + 1);
	    			Cookies.setCookie(cookieName,localeName,expires);
	    		}
//	    		if (queryParam != null) {
//	    			UrlBuilder builder=Location.createUrlBuilder().setParameter(queryParam,localeName);
//	    			Window.Location.replace(builder.buildString());
//	    		}
//	    		else {
	    			Window.Location.reload();
//	    		}
	    	}			
	    });
	}

}
