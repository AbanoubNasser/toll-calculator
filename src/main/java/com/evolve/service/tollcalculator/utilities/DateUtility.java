package com.evolve.service.tollcalculator.utilities;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * DateUtility is responsible class for different Date functionality
 */
public class DateUtility {

    private static  final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static boolean isWeekendDate(LocalDateTime localDate) {

        if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return true;
        }
        return false;
    }

    public static LocalDateTime parse(String datetime){
        return LocalDateTime.parse(datetime, DateUtility.formatter);
    }

}
