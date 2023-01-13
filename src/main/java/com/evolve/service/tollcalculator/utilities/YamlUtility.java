package com.evolve.service.tollcalculator.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YamlUtility {

    public static <T> T parseYaml(String filePath, Class<?> target) throws IOException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return mapper.readValue(new File(filePath), mapper.getTypeFactory().constructType(Class.forName(target.getName())));
    }
}
