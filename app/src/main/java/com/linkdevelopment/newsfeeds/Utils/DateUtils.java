package com.linkdevelopment.newsfeeds.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by nsamir on 1/24/2019.
 */
public abstract class DateUtils {

    private static final String YYYY_MM_DD_T_HH_MM = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String MMMMM_DD_YYYY = "MMMM dd, yyyy";

    public static String formatDate(String date) {
        SimpleDateFormat sd1 = new SimpleDateFormat(YYYY_MM_DD_T_HH_MM, Locale.US);
        sd1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date dt;
        try {
            dt = sd1.parse(date);

            SimpleDateFormat sd2 = new SimpleDateFormat(MMMMM_DD_YYYY, Locale.US);
            return sd2.format(dt);

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}



