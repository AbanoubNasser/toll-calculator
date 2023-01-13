package com.evolve.service.tollcalculator.service;

import com.evolve.service.tollcalculator.model.Car;
import com.evolve.service.tollcalculator.model.Diplomat;
import com.evolve.service.tollcalculator.model.Emergency;
import com.evolve.service.tollcalculator.model.Foreign;
import com.evolve.service.tollcalculator.model.Military;
import com.evolve.service.tollcalculator.model.Motorbike;
import com.evolve.service.tollcalculator.model.Tractor;
import com.evolve.service.tollcalculator.utilities.DateUtility;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class TollFreeServiceTest {

    private final TollFreeService tollFreeService = new TollFreeService();

    @Test
    public void testCardVehicleISFree() {
        assertEquals(false, tollFreeService.isFreeTollVehicle(new Car()));
    }

    @Test
    public void testMotorBikeVehicleISFree() {
        assertEquals(true, tollFreeService.isFreeTollVehicle(new Motorbike()));
    }

    @Test
    public void testDiplomatVehicleISFree() {
        assertEquals(true, tollFreeService.isFreeTollVehicle(new Diplomat()));
    }

    @Test
    public void testEmergencyVehicleISFree() {
        assertEquals(true, tollFreeService.isFreeTollVehicle(new Emergency()));
    }

    @Test
    public void testMilitaryVehicleISFree() {
        assertEquals(true, tollFreeService.isFreeTollVehicle(new Military()));
    }

    @Test
    public void testTractorVehicleISFree() {
        assertEquals(true, tollFreeService.isFreeTollVehicle(new Tractor()));
    }

    @Test
    public void testForeignVehicleISFree() {
        assertEquals(true, tollFreeService.isFreeTollVehicle(new Foreign()));
    }

    @Test
    public void testWeekendDateFreeToll() {
        LocalDateTime date = DateUtility.parse("01-01-2023 10:00:00");
        assertEquals(true, tollFreeService.isFreeTollDate(date));
    }

    @Test
    public void testEndOfYearDate2013FreeToll() {
        LocalDateTime date = DateUtility.parse("31-12-2013 10:00:00");
        assertEquals(true, tollFreeService.isFreeTollDate(date));
    }

    @Test
    public void test29March2013FreeToll() {
        LocalDateTime date = DateUtility.parse("29-03-2013 17:00:00");
        assertEquals(true, tollFreeService.isFreeTollDate(date));
    }
}
