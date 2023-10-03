package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Parse a date string in the format "yyyy-MM-dd" into a Date object
    public static Date parseDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Format a Date object as a string in the format "yyyy-MM-dd"
    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }
}