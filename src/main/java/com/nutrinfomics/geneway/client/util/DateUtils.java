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

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;

public class DateUtils {
	private static final String D_M_YYYY = "d-M-yyyy";
    private static final String DATE_SEPARATOR = "-";

    public static Date getDate( Integer dd, Integer mm, Integer yyyy ){
        if ( dd == null || mm == null || yyyy == null )
            return null;

        Date retVal = null;
        try
        {
            retVal = DateTimeFormat.getFormat( D_M_YYYY ).parse( dd + DATE_SEPARATOR + mm + DATE_SEPARATOR + yyyy );
        }
        catch ( Exception e )
        {
            retVal = null;
        }

        return retVal;
    }

    public static String getDayAsString( Date date ){
        return ( date == null ) ? null : DateTimeFormat.getFormat( D_M_YYYY ).format( date ).split( DATE_SEPARATOR )[0];
    }

    public static String getMonthAsString( Date date ){
        return ( date == null ) ? null : DateTimeFormat.getFormat( D_M_YYYY ).format( date ).split( DATE_SEPARATOR )[1];
    }

    public static String getYearAsString( Date date ){
        return ( date == null ) ? null : DateTimeFormat.getFormat( D_M_YYYY ).format( date ).split( DATE_SEPARATOR )[2];
    }

    public static boolean isValidDate( Integer dd, Integer mm, Integer yyyy ){
        boolean isvalidDate = true;

        try{
            String transformedInput = DateTimeFormat.getFormat( D_M_YYYY ).format( getDate( dd, mm, yyyy ) );
            String originalInput = dd + DATE_SEPARATOR + mm + DATE_SEPARATOR + yyyy;

            isvalidDate = transformedInput.equals( originalInput );
        }
        catch ( Exception e ){
            isvalidDate = false;
        }

        return isvalidDate;
    }
	
	private static DateTimeFormat formater = DateTimeFormat.getFormat("yyyy.MM.dd");

	static public String getDate(int daysOffset) {
		Date timestamp = new Date();
		CalendarUtil.addDaysToDate(timestamp, daysOffset);
		return formater.format(timestamp);
	}

}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */