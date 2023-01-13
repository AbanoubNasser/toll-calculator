package com.evolve.service.tollcalculator.config;

import com.evolve.service.tollcalculator.exception.YamlParsingException;
import com.evolve.service.tollcalculator.utilities.YamlUtility;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * TollTimeSlotFeesConfig is a config class for defined fees for toll per time slot
 */
public class TollTimeSlotFeesConfig {

    public static final int MAX_TOTAL_TOLL_FEE_PER_DAY = 60;

    private static final String TOLL_HOUR_FEE_CONFIG_PATH = "src/main/resources/tollTimeSlotFees.yaml";

    public static Set<TollTimeSlotFee> getTollTimeSlotFees(){
        try{
            TollTimeSlotsFees tollTimeSlotsFees = YamlUtility.parseYaml(TOLL_HOUR_FEE_CONFIG_PATH, TollTimeSlotsFees.class);
            return new LinkedHashSet<>(tollTimeSlotsFees.getFees());
        }catch (Exception exception){
            throw new YamlParsingException("Error while parsing "+TOLL_HOUR_FEE_CONFIG_PATH);
        }
    }
}
