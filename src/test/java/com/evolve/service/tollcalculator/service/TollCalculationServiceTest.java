package com.evolve.service.tollcalculator.service;

import com.evolve.service.tollcalculator.exception.NotNullDatesException;
import com.evolve.service.tollcalculator.exception.NotNullVehicleException;
import com.evolve.service.tollcalculator.exception.NotSameDateException;
import com.evolve.service.tollcalculator.model.Car;
import com.evolve.service.tollcalculator.model.Military;
import com.evolve.service.tollcalculator.utilities.DateUtility;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TollCalculationServiceTest {

    private TollCalculationService tollCalculationService = new TollCalculationService();

    @Test
    public void test_NullVehicle(){
        assertThrows(NotNullVehicleException.class, ()-> tollCalculationService.calculateTollFees(null, LocalDateTime.now()));
    }

    @Test
    public void test_NullDates(){
        assertThrows(NotNullDatesException.class, ()-> tollCalculationService.calculateTollFees(new Car(), null));
    }

    @Test
    public void test_ProvidedDatesOnTheSameDay() {
        LocalDateTime date = DateUtility.parse("06-01-2023 12:00:00");
        LocalDateTime date1 = DateUtility.parse("07-01-2023 12:00:00");
        assertThrows(NotSameDateException.class, ()-> tollCalculationService.calculateTollFees(new Car(), date, date1));
    }

    @Test
    public void test_FreeVehicleCalculation(){
        assertEquals(0, tollCalculationService.calculateTollFees(new Military(), LocalDateTime.now()));
    }

    @Test
    public void test_WeekendDateCalculation(){
        LocalDateTime weekEndDate = DateUtility.parse("01-01-2023 10:00:00");
        assertEquals(0, tollCalculationService.calculateTollFees(new Car(), weekEndDate));
    }

    @Test
    public void test_OneDateFee() {
        LocalDateTime date = DateUtility.parse("09-01-2023 06:10:04");
        assertEquals(8, tollCalculationService.calculateTollFees(new Car(), date));
    }

    @Test
    public void test_DatesFee_1() {
        LocalDateTime date = DateUtility.parse("09-01-2023 06:10:04");
        LocalDateTime date1 = DateUtility.parse("09-01-2023 15:10:20");
        LocalDateTime date2 = DateUtility.parse("09-01-2023 17:20:20");
        assertEquals(34, tollCalculationService.calculateTollFees(new Car(),date, date1, date2));
    }

    @Test
    public void test_TollFee_WithTheSameHour() {
        LocalDateTime date = DateUtility.parse("09-01-2023 06:10:04");
        LocalDateTime date1 = DateUtility.parse("09-01-2023 06:35:20");
        LocalDateTime date2 = DateUtility.parse("09-01-2023 17:20:20");
        assertEquals(34, tollCalculationService.calculateTollFees(new Car(),date, date1, date2));
    }

    @Test
    public void test_TollFee_WithDifferentDates() {
        LocalDateTime date = DateUtility.parse("09-01-2023 06:15:00");
        LocalDateTime date1 = DateUtility.parse("09-01-2023 07:30:00");
        LocalDateTime date2 = DateUtility.parse("09-01-2023 08:15:00");
        assertEquals(39, tollCalculationService.calculateTollFees(new Car(),date, date1, date2));
    }

    @Test
    public void test_TollFee_WithDifferentDatesWithRange() {
        LocalDateTime date = DateUtility.parse("09-01-2023 06:15:00");
        LocalDateTime date1 = DateUtility.parse("09-01-2023 07:30:00");
        LocalDateTime date2 = DateUtility.parse("09-01-2023 08:15:00");
        LocalDateTime date3 = DateUtility.parse("09-01-2023 15:15:00");
        LocalDateTime date4 = DateUtility.parse("09-01-2023 16:45:00");
        LocalDateTime date5 = DateUtility.parse("09-01-2023 17:15:00");
        assertEquals(60, tollCalculationService.calculateTollFees(new Car(),date, date1, date2, date3, date4, date5));
    }

    @Test
    public void test_TollFee_WithDifferentDatesWithMoreThan60SEK() {
        LocalDateTime date = DateUtility.parse("09-01-2023 06:15:00");
        LocalDateTime date1 = DateUtility.parse("09-01-2023 07:30:00");
        LocalDateTime date2 = DateUtility.parse("09-01-2023 08:15:00");
        LocalDateTime date3 = DateUtility.parse("09-01-2023 15:15:00");
        LocalDateTime date4 = DateUtility.parse("09-01-2023 16:45:00");
        LocalDateTime date5 = DateUtility.parse("09-01-2023 18:20:00");
        assertEquals(60, tollCalculationService.calculateTollFees(new Car(),date, date1, date2, date3, date4, date5));
    }

}
