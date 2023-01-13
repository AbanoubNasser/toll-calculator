package com.evolve.service.tollcalculator.config;

import com.evolve.service.tollcalculator.exception.YamlParsingException;
import com.evolve.service.tollcalculator.utilities.YamlUtility;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class TollFreeDateConfig {

    private static final String TOLL_FREE_DATES_CONFIG_PATH = "src/main/resources/freeDates.yaml";

    public static Set<LocalDate> getTollFreeDates() {

        try {
            TollFreeDates tollFreeDates = YamlUtility.parseYaml(TOLL_FREE_DATES_CONFIG_PATH, TollFreeDates.class);
            return tollFreeDates.getDates()
                    .stream()
                    .map(tollFreeDate -> tollFreeDate
                            .getDays()
                            .stream()
                            .map(day -> LocalDate.of(tollFreeDate.getYear(), tollFreeDate.getMonth(), day))
                            .collect(Collectors.toSet()))
                    .collect(Collectors.toSet()).stream().flatMap(Collection::stream).collect(Collectors.toSet());
        } catch (Exception ex) {
            throw new YamlParsingException("Error while parsing "+TOLL_FREE_DATES_CONFIG_PATH);
        }
    }
}
