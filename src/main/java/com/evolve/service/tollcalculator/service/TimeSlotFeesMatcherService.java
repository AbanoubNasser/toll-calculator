package com.evolve.service.tollcalculator.service;

import com.evolve.service.tollcalculator.config.TollTimeSlotFee;
import com.evolve.service.tollcalculator.config.TollTimeSlotFeesConfig;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

public class TimeSlotFeesMatcherService {

    public int getFeesByDate(LocalDateTime date) {


        int hour = date.getHour();
        int minute =date.getMinute();

        Predicate<TollTimeSlotFee> tollTimeSlotFeePredicateForSpecificHour = slot -> (slot.getFromHour() == hour && minute >= slot.getFromMinute() && minute <= slot.getToMinute());

        Optional<TollTimeSlotFee> optionalTollTimeSlotFeeForSpecificHour = findTollTimeSlotFee(tollTimeSlotFeePredicateForSpecificHour);
        if (optionalTollTimeSlotFeeForSpecificHour.isPresent()) {
            return optionalTollTimeSlotFeeForSpecificHour.get().getFees();
        }

        Predicate<TollTimeSlotFee> tollTimeSlotFeePredicateForSpecificRange = slotForRange -> (hour >= slotForRange.getFromHour() && hour < slotForRange.getToHour() && minute >= slotForRange.getFromMinute()
                && minute <= slotForRange.getToMinute());

        Optional<TollTimeSlotFee> optionalTollTimeSlotFeeForSpecificRange = findTollTimeSlotFee(tollTimeSlotFeePredicateForSpecificRange);
        if (optionalTollTimeSlotFeeForSpecificRange.isPresent()) {
            return optionalTollTimeSlotFeeForSpecificRange.get().getFees();
        }

        return 0;
    }

    private Optional<TollTimeSlotFee> findTollTimeSlotFee(Predicate<TollTimeSlotFee> tollTimeSlotFeePredicate) {
        return TollTimeSlotFeesConfig
                .getTollTimeSlotFees()
                .stream()
                .filter(slot -> tollTimeSlotFeePredicate.test(slot)).findFirst();
    }

}
