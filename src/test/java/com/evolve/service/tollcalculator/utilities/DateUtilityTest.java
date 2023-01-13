package com.evolve.service.tollcalculator.utilities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDateTime;

public class DateUtilityTest {
    @Test
    public void testWeedEndDate() {
        LocalDateTime date = DateUtility.parse("01-01-2023 10:00:00");
        assertEquals(true, DateUtility.isWeekendDate(date));
    }

    @Test
    public void testNonWeedEndDate() throws ParseException {
        LocalDateTime date = DateUtility.parse("23-12-2022 10:00:00");
        assertEquals(false, DateUtility.isWeekendDate(date));
    }
}
