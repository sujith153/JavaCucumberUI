package utils;

public class Constants {
    // Timeouts
    public static final int DEFAULT_TIMEOUT = 10;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int IMPLICIT_WAIT = 10;

    // File Paths
    public static final String SCREENSHOT_PATH = "test-output/screenshots/";
    public static final String REPORT_PATH = "test-output/reports/";

    // Common Messages
    public static final String ELEMENT_NOT_FOUND = "Element not found: ";
    public static final String PAGE_LOAD_ERROR = "Page did not load within specified timeout";

    // Common URLs
    public static final String BASE_URL = "https://www.example.com";

    private Constants() {
        // Private constructor to prevent instantiation
    }
}
