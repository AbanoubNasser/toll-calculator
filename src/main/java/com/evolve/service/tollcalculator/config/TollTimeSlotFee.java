package com.evolve.service.tollcalculator.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TollTimeSlotFee {

    private int fromHour;

    private int toHour;

    private int fromMinute;

    private int toMinute;

    private int fees;
}
