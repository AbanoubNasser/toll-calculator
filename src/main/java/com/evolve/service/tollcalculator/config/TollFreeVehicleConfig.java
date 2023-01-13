package com.evolve.service.tollcalculator.config;
import com.evolve.service.tollcalculator.exception.YamlParsingException;
import com.evolve.service.tollcalculator.model.VehicleType;
import com.evolve.service.tollcalculator.utilities.YamlUtility;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TollFreeVehicleConfig is a config class to identify Free toll vehicles
 */
public class TollFreeVehicleConfig {

    private static final String FREE_VEHICLE_TYPES_CONFIG ="src/main/resources/freeVehiclesTypes.yaml";

    public static Set<VehicleType> getFreeVehicleTypes(){
        try{
            TollFreeVehicles tollFreeVehicles = YamlUtility.parseYaml(FREE_VEHICLE_TYPES_CONFIG, TollFreeVehicles.class);
            return tollFreeVehicles.getFreeVehicleTypes()
                    .stream()
                    .map(type -> VehicleType.valueOf(type.toUpperCase(Locale.ROOT)))
                    .collect(Collectors.toSet());
        }catch (Exception exception){
            throw new YamlParsingException("Error while parsing "+FREE_VEHICLE_TYPES_CONFIG);
        }
    }
}
