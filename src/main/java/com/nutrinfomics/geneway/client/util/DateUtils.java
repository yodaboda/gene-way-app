package com.nutrinfomics.geneway.client.util;

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
