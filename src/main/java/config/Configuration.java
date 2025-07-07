package config;

import com.codeborne.selenide.Configuration;
import utils.PropertyReader;

public class Configuration {
    public static void setupConfig() {
        // Browser Configuration
        Configuration.browser = PropertyReader.getProperty("browser", "chrome");
        Configuration.headless = PropertyReader.getBooleanProperty("headless");
        Configuration.timeout = PropertyReader.getIntProperty("browser.timeout") * 1000;
        Configuration.pageLoadTimeout = PropertyReader.getIntProperty("page.load.timeout") * 1000;

        // URL Configuration
        Configuration.baseUrl = PropertyReader.getProperty("base.url");

        // Screenshots and Reports
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
        Configuration.reportsFolder = PropertyReader.getProperty("report.path");

        // Browser Window Configuration
        if (PropertyReader.getBooleanProperty("window.maximize")) {
            Configuration.browserSize = String.format("%dx%d",
                PropertyReader.getIntProperty("default.window.width"),
                PropertyReader.getIntProperty("default.window.height"));
        }

        // Downloads Configuration
        Configuration.downloadsFolder = PropertyReader.getProperty("download.path");
    }

    public static void setCustomTimeout(long timeout) {
        Configuration.timeout = timeout;
    }

    public static void enableHeadlessMode(boolean enable) {
        Configuration.headless = enable;
    }
}
