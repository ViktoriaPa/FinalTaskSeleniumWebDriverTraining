package com.coherentsolutions.training.auto.web.pashkovskaya.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.coherentsolutions.training.auto.web.pashkovskaya.util.MainConstants.SAUCELABS_URL;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DriverInit {

    private static DriverInit instanceDriver  = null;
    private WebDriver driver;
    private static final String USERNAME_PROPERTY_KEY = "username";
    private static final String ACCESSKEY_PROPERTY_KEY = "accessKey";

    private DriverInit() {

    }

    public synchronized static DriverInit getInstance() {
        if (instanceDriver == null) {
            instanceDriver = new DriverInit();
        }
        return instanceDriver;
    }

    public WebDriver getDriver(String browserName, String wayOfGetDriver) throws IOException {
        switch (wayOfGetDriver) {
            case "locally":
                if (browserName.equals("chrome")) {
                    ChromeOptions handlingSSL = new ChromeOptions();
                    handlingSSL.setAcceptInsecureCerts(true);
                    handlingSSL.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(handlingSSL);
                    driver.manage().timeouts().implicitlyWait(2, SECONDS);
                    driver.manage().window().maximize();
                } else if (browserName.equals("firefox")) {
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                }
                break;

            case "grid":
                DesiredCapabilities dc = new DesiredCapabilities();

                if(browserName.equals("chrome")) {
                    dc.setBrowserName("chrome");
                } else if (browserName.equals("firefox")) {
                    dc.setBrowserName("firefox");
                } else if (browserName.equals("edge")) {
                    dc.setBrowserName("MicrosoftEdge");
                } else if (browserName.equals("safari")) {
                    dc.setBrowserName("safari");
                }

                driver = new RemoteWebDriver(new URL("http://localhost:4444"), dc);
                driver.manage().timeouts().implicitlyWait(2, SECONDS);
                driver.manage().window().maximize();
                break;

            case "sauceLabs":
                System.out.println("browser name is: " + browserName);

                MutableCapabilities sauceOpts = new MutableCapabilities();
                sauceOpts.setCapability(USERNAME_PROPERTY_KEY, PropertiesFileReader.getSauceUsername());
                sauceOpts.setCapability(ACCESSKEY_PROPERTY_KEY, PropertiesFileReader.getSauceAccessKey());

                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setCapability("sauce:options", sauceOpts);

                if(browserName.equals("chrome")){
                    WebDriverManager.chromedriver().setup();
                    cap.setBrowserName("chrome");
                    cap.setPlatform(Platform.WIN10);
                    cap.setVersion("latest");
                } else if (browserName.equals("firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    cap.setBrowserName("firefox");
                    cap.setPlatform(Platform.WIN8_1);
                    cap.setVersion("latest");
                }

                driver = new RemoteWebDriver(new URL(SAUCELABS_URL), cap);
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
        }
        return driver;
    }
}
