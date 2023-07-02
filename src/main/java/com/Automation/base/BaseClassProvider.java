package com.Automation.base;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;

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

    //  protected final void generateYAMLFile(String yamlFilePath, Object object)
    //         throws JsonGenerationException, IOException {
    //     ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    //     // Write object as YAML file
    //     mapper.writeValue(new File(TestUtil.getResourceAbsolutePath(yamlFilePath)), object);
    // }
}
