package com.demo.base;

import com.github.dockerjava.api.model.Capability;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class Context {

    public static WebDriver driver;
    protected Properties properties;
    protected static Logger log;

    public Context() {
        try (FileInputStream ip = new FileInputStream(
                "config.properties")) {
            properties = new Properties();
            properties.load(ip);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }


    protected void init(String browserName) {
        this.logger();
//        String browserName = properties.getProperty("browser");
        driver = getDriver(browserName);
        log.info(browserName + " is configured");
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    protected WebDriver getDriver(String browser) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
            URL url = new URL("http://localhost:4444/wd/hub");
//            if (driver == null) {
            switch (browser) {
                case "chrome":
//                        WebDriverManager.chromedriver().setup();
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
//                    driver = new ChromeDriver();
                    driver = new RemoteWebDriver(url, capabilities);
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "firefox":
//                        WebDriverManager.firefoxdriver().setup();
//                        driver = new FirefoxDriver();
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
                    driver = new RemoteWebDriver(url, capabilities);
                    break;
                default:
                    log.error("browser not matched");
                    break;
            }
//            }


        } catch (MalformedURLException e) {
            log.error(e);
        }
        return driver;
    }

    //    To configure the log4j xml file
    private void logger() {
        log = Logger.getLogger(Context.class);
        PropertyConfigurator.configure("src/test/resources/log4j.xml");
    }

    protected void tearDown() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
