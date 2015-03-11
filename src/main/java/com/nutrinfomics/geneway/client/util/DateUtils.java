package com.nutrinfomics.geneway.client.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;

public class DateUtils {
	private static DateTimeFormat formater = DateTimeFormat.getFormat("yyyy.MM.dd");

	static public String getDate(int daysOffset) {
		Date timestamp = new Date();
		CalendarUtil.addDaysToDate(timestamp, daysOffset);
		return formater.format(timestamp);
	}

}
