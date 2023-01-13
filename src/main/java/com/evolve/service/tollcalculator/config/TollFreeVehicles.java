package com.evolve.service.tollcalculator.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TollFreeVehicles {

    private List<String> freeVehicleTypes;
}
