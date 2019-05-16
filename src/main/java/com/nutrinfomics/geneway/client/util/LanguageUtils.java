package com.nutrinfomics.geneway.client.util;

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

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */