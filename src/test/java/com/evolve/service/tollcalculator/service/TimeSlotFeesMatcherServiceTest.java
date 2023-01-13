package com.evolve.service.tollcalculator.service;

import com.evolve.service.tollcalculator.utilities.DateUtility;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class TimeSlotFeesMatcherServiceTest {

    private final TimeSlotFeesMatcherService timeSlotFeesMatcherService = new TimeSlotFeesMatcherService();

    @Test
    public void testTimeSlotFeePredicateForSpecificHour() {

        LocalDateTime date = DateUtility.parse("02-01-2023 06:00:00");
        assertEquals(8, timeSlotFeesMatcherService.getFeesByDate(date));
    }

    @Test
    public void testTimeSlotFeePredicateForSpecificRange() {

        LocalDateTime date = DateUtility.parse("02-01-2023 13:35:00");
        assertEquals(8, timeSlotFeesMatcherService.getFeesByDate(date));
    }

    @Test
    public void testTimeSlotFeePredicateForSpecificRange15To16() {

        LocalDateTime date = DateUtility.parse("02-01-2023 15:35:00");
        assertEquals(18, timeSlotFeesMatcherService.getFeesByDate(date));
    }

    @Test
    public void testTimeSlotFeePredicateForFreeHours() {

        LocalDateTime date = DateUtility.parse("02-01-2023 19:22:00");
        assertEquals(0, timeSlotFeesMatcherService.getFeesByDate(date));
    }
}
