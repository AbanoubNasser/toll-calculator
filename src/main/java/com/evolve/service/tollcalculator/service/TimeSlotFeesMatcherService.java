package com.evolve.service.tollcalculator.service;

import com.evolve.service.tollcalculator.config.TollTimeSlotFee;
import com.evolve.service.tollcalculator.config.TollTimeSlotFeesConfig;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * TimeSlotFeesMatcherService is responsible to match the correct time slot for specific datetime and get the fees
 * for this specific date time
 */
public class TimeSlotFeesMatcherService {

    public int getFeesByDate(LocalDateTime date) {
        int hour = date.getHour();
        int minute = date.getMinute();

        Predicate<TollTimeSlotFee> tollTimeSlotFeePredicateForSpecificRange = slotForRange -> (hour >= slotForRange.getFromHour() && hour <= slotForRange.getToHour() && minute >= slotForRange.getFromMinute() && minute <= slotForRange.getToMinute());
        Optional<TollTimeSlotFee> optionalTollTimeSlotFeeForSpecificRange = findTollTimeSlotFee(tollTimeSlotFeePredicateForSpecificRange);
        if (optionalTollTimeSlotFeeForSpecificRange.isPresent()) {
            return optionalTollTimeSlotFeeForSpecificRange.get().getFees();
        }

        return 0;
    }

    private Optional<TollTimeSlotFee> findTollTimeSlotFee(Predicate<TollTimeSlotFee> tollTimeSlotFeePredicate) {
        return TollTimeSlotFeesConfig.getTollTimeSlotFees().stream().filter(slot -> tollTimeSlotFeePredicate.test(slot)).findFirst();
    }

}
