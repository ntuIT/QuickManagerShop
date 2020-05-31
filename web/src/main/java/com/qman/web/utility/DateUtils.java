package com.qman.web.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String formatTo(Date date) {
        String result = formatTo(date, null);
        return result;
    }

    public static String formatTo(Date date, String format) {
        SimpleDateFormat df;
        if (format == null || format.isEmpty()) {
            df = new SimpleDateFormat("yyyy-mm-dd");
        } else {
            df = new SimpleDateFormat(format);
        }
        String result = df.format(date);
        return result;
    }
}
