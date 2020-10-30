package com.example.buysell;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarUtils {
    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_PATTERN2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static String getCurrentDateTime()
    {
        String dateStr = null;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN2, Locale.ENGLISH);
        dateStr = sdf.format(date);
        return dateStr;
    }
}
