package com.oerlemans.siemiatycze.balance.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BalanceUtils {

    public static String nowToString() {
        GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
        return gregorianCalenadarToString(now);
    }

    private static String gregorianCalenadarToString(GregorianCalendar calendar) {
        return new StringBuilder()
                .append(calendar.get(Calendar.YEAR))
                .append("-")
                .append(intOfGivenLenWit(calendar.get(Calendar.MONTH) + 1, 2))
                .append("-")
                .append(calendar.get(Calendar.DAY_OF_MONTH))
                .append( " ")
                .append(calendar.get(Calendar.HOUR_OF_DAY))
                .append(":")
                .append(calendar.get(Calendar.MINUTE))
                .append(":")
                .append(calendar.get(Calendar.SECOND))
                .toString();
    }

    private static String intOfGivenLenWit(int number, int length) {
        StringBuilder resultString = new StringBuilder();
        String numberAsString = String.valueOf(number);
        while (resultString.length() + numberAsString.length() < length) {
            resultString.append('0');
        }
        resultString.append(number);
        return resultString.toString();
    }

}
