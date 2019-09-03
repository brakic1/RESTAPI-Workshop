package org.workshop.api.utilities;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtility {
    
    private static Logger LOG = LoggerFactory.getLogger(ConfigUtility.class);
    
    public static String getProperty(String property, String config){
        if(property == null || "".equals(property)){
            LOG.error("The property is null or empty string!");
            return null;
        }
            
        if(config == null || "".equals(config)){
            LOG.error("The value is null or empty string");
            return null;
        }
        return loadConfig(config).getProperty(property);
    }

    private static Properties loadConfig(String config){
        Properties p = new Properties();
        InputStream in = ConfigUtility.class.getClassLoader().getResourceAsStream(config);
        try {
            if(in == null)
                throw new Exception();
            p.load(in);
        } catch (Exception e) {
            LOG.error("Error while loading properties", e.fillInStackTrace());
        }
        return p;
    }
}
