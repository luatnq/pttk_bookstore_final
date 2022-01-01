package com.example.bookstore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date convertStringToDate(String input, String format) throws ParseException {
        return  new SimpleDateFormat(format).parse(input);
    }
}
