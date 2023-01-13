package com.evolve.service.tollcalculator.service;

import com.evolve.service.tollcalculator.exception.NotNullDatesException;
import com.evolve.service.tollcalculator.exception.NotNullVehicleException;
import com.evolve.service.tollcalculator.exception.NotSameDateException;
import com.evolve.service.tollcalculator.model.Vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TollCalculationValidator {

    public void validateVehicleAndDates(final Vehicle vehicle, final LocalDateTime... dates) {
        if (vehicle == null) {
            throw new NotNullVehicleException("Vehicle value should not be null or empty");
        }
        if (dates == null) {
            throw new NotNullDatesException("Toll Dates should not be null or empty");
        }
    }

    public void validateTollSameDates(final LocalDateTime... dates) {
        LocalDate firstLocalDate = dates[0].toLocalDate();
        Arrays.stream(dates).forEach(date -> {
            if (!firstLocalDate.isEqual(date.toLocalDate())) {
                throw new NotSameDateException("Provided dates are not in the same date");
            }
        });
    }
}
