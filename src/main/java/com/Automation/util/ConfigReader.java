package com.Automation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

 public class  ConfigReader {
     private String applicationUrl = "";
     private static final Logger LOGGER = LoggerFactory.getLogger(ConfigReader.class);

     public ConfigReader() {
            FileInputStream fis;
            try {
                fis = new FileInputStream(System.getProperty("user.dir") +"/src/main/java/com/Automation/config/config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                LOGGER.info("Cant't read config.properties file!");
                return;
            }
            Properties properties = new Properties();
            try {
                properties.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
               LOGGER.info("Cant't read config.properties file!");
                return;
            }
            applicationUrl = properties.getProperty("applicationUrl");
  
        }

        public  String getApplicationUrl () {
            return applicationUrl;
        }



}