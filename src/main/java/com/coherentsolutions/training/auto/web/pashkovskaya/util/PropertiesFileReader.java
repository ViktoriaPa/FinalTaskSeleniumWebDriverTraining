package com.coherentsolutions.training.auto.web.pashkovskaya.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
    private static Properties prop;
    private static final String SAUCELABS_PROPERTIES_PATH = "properties/sauceLabs.properties";

    private PropertiesFileReader() throws IOException {
        setPropsCache();
    }

    private static void setPropsCache() throws IOException {
        try (InputStream input = new FileInputStream(SAUCELABS_PROPERTIES_PATH)) {
            prop = new Properties();
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getSauceUsername() throws IOException {
        setPropsCache();
        String sauceUsername = prop.getProperty("sauceUsername");
        return sauceUsername;
    }

    public static String getSauceAccessKey() throws IOException {
        setPropsCache();
        String sauceAccessKey = prop.getProperty("sauceAccessKey");
        return sauceAccessKey;
    }
}
