package com.evolve.service.tollcalculator.service;

import com.evolve.service.tollcalculator.exception.NotNullDatesException;
import com.evolve.service.tollcalculator.exception.NotNullVehicleException;
import com.evolve.service.tollcalculator.exception.NotSameDateException;
import com.evolve.service.tollcalculator.model.Car;
import com.evolve.service.tollcalculator.utilities.DateUtility;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertThrows;

public class TollCalculationValidatorTest {

    private final TollCalculationValidator tollCalculationValidator = new TollCalculationValidator();

    @Test
    public void testNullVehicleWithSomeDates() throws ParseException {
        LocalDateTime date = DateUtility.parse("01-01-2023 10:00:00");
        assertThrows(NotNullVehicleException.class, () -> tollCalculationValidator.validateVehicleAndDates(null, date));
    }

    @Test
    public void testNullDatesWithVehicle() throws ParseException {
        assertThrows(NotNullDatesException.class, () -> tollCalculationValidator.validateVehicleAndDates(new Car(), null));
    }

    @Test
    public void testTollNotTheSameDate() throws ParseException {
        LocalDateTime date1 = DateUtility.parse("02-01-2023 10:00:00");
        LocalDateTime date2 = DateUtility.parse("03-01-2023 12:00:00");
        LocalDateTime date3 = DateUtility.parse("04-01-2023 21:00:00");
        assertThrows(NotSameDateException.class, () -> tollCalculationValidator.validateTollSameDates(date1, date2, date3));
    }

}
