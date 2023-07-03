package com.Automation.base;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

public class BaseClassProvider {

    private static final Logger LOG = LoggerFactory.getLogger(BaseClassProvider.class);
    public static <T> Object[][] testDataGenerator(final String inputDataFile, Class<T[]> classz) {
        T[] dateSet = loadDataFromYamlFile(inputDataFile, classz);
        Object[][] obj = new Object[dateSet.length][1];
        for (int index = 0; index < obj.length; index++) {
            obj[index][0] = dateSet[index];
        }
        return obj;
    }
    public static <T> T[] loadDataFromYamlFile(final String inputDataFile, Class<T[]> classz) {
        LOG.info("LOADING DATA FROM YAML FILE");
        LOG.info(inputDataFile);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        T[] user = null;
        try {
            user = mapper.readValue(new File(inputDataFile), classz);
        } catch (Exception e) {
            LOG.error("ENCOUNGTERED EXCEPTION WHILE CONVERTING YAML FILE TO POJO. EXCEPTION MESSAGE :" + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }
}
