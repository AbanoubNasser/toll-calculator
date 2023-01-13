package com.evolve.service.tollcalculator.service;

import com.evolve.service.tollcalculator.config.TollFreeDateConfig;
import com.evolve.service.tollcalculator.config.TollFreeVehicleConfig;
import com.evolve.service.tollcalculator.model.Vehicle;
import com.evolve.service.tollcalculator.utilities.DateUtility;

import java.time.LocalDateTime;


public class TollFreeService {

    public boolean isFreeTollVehicle(Vehicle vehicle) {
        return TollFreeVehicleConfig.getFreeVehicleTypes().contains(vehicle.getType());
    }

    public boolean isFreeTollDate(LocalDateTime date) {
        if (DateUtility.isWeekendDate(date)
                || TollFreeDateConfig.getTollFreeDates().contains(date.toLocalDate())) {
            return true;
        }
        return false;
    }
}
