package com.oerlemans.siemiatycze.balance.util;

import com.oerlemans.siemiatycze.balance.service.BalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class BalanceUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceService.class);

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
                .append(" ")
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

    public static double scaleResultAsdouble(String scaleData) {
        /* serial data sample ->02,79,30,30,20,30,30,30,30,30,30,30,30,30,30,30,0D
         *  serial data sample ->02,79,30,30,20,30,30,30,39,30,30,30,30,30,30,30,0D
         *                                                         | -> last char to parse
         * */
        StringBuilder resultNumberString = new StringBuilder();

        List<String> dataList = new ArrayList<String>(Arrays.asList(scaleData.split(",")));
        for (int i = 5; i < dataList.size() - 5; i++) {
            int tempAsciiCode;
            try {
                tempAsciiCode = Integer.parseInt(dataList.get(i), 16);
            } catch (NumberFormatException nEx) {
                LOGGER.error("Cannot convert scale input to number. Input:" + dataList.get(i));
                tempAsciiCode = 30;
            }
            resultNumberString.append((char) tempAsciiCode);
        }
        LOGGER.info("Converted string:" + resultNumberString);
        BigDecimal resultNumber = new BigDecimal(resultNumberString.toString());
        return resultNumber.doubleValue() / 100;
    }

}
