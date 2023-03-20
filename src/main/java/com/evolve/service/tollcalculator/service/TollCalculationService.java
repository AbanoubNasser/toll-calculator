package com.evolve.service.tollcalculator.service;

import com.evolve.service.tollcalculator.config.TollTimeSlotFeesConfig;
import com.evolve.service.tollcalculator.model.Vehicle;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * TollCalculationService is the responsible toll fee calculation based on provide dates and Vehicle
 */
public class TollCalculationService {

    private final TollFreeService tollFreeService;
    private final TimeSlotFeesMatcherService timeSlotFeesMatcherService;
    private final TollCalculationValidator tollCalculationValidator;

    public TollCalculationService() {
        this.tollFreeService = new TollFreeService();
        this.timeSlotFeesMatcherService = new TimeSlotFeesMatcherService();
        this.tollCalculationValidator = new TollCalculationValidator();
    }


    /**
     * Calculate the total toll fee for one day
     *
     * @param vehicle - the vehicle
     * @param dates   - date and time of all passes on one day
     * @return - the total toll fee for that day
     */
    public int calculateTollFees(final Vehicle vehicle, final LocalDateTime... dates) {
        tollCalculationValidator.validateVehicleAndDates(vehicle, dates);

        tollCalculationValidator.validateTollSameDates(dates);

        if (tollFreeService.isFreeTollVehicle(vehicle) || (tollFreeService.isFreeTollDate(dates[0]))) {
            return 0;
        } else {
            return calculateTotalTollFee(dates);
        }
    }

    private int calculateTotalTollFee(final LocalDateTime... dates) {
        LocalDateTime intervalStart = dates[0];
        int totalFee = 0;
        for (LocalDateTime date : dates) {
            totalFee = calculateTollFeeForDate(totalFee, intervalStart, date);
            if(ChronoUnit.MINUTES.between(intervalStart, date)>60){
                intervalStart = date;
            }
        }
        if (totalFee > TollTimeSlotFeesConfig.MAX_TOTAL_TOLL_FEE_PER_DAY) {
            totalFee = TollTimeSlotFeesConfig.MAX_TOTAL_TOLL_FEE_PER_DAY;
        }
        return totalFee;
    }

    private int calculateTollFeeForDate(int totalFee, LocalDateTime intervalStart, LocalDateTime date) {
        int nextFee = timeSlotFeesMatcherService.getFeesByDate(date);
        int tempFee = timeSlotFeesMatcherService.getFeesByDate(intervalStart);

        long minutes = ChronoUnit.MINUTES.between(intervalStart, date);

        if (minutes <= 60) {
            if (totalFee > 0) totalFee -= tempFee;
            if (nextFee >= tempFee) tempFee = nextFee;
            totalFee += tempFee;
        } else {
            totalFee += nextFee;
        }
        return totalFee;
    }
}
