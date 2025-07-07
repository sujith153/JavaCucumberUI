import com.codeborne.selenide.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BaseActionClass {
    Faker faker = new Faker();

    public String getRandomName() {
        return faker.name().fullName();
    }

    public void openUrl(String url) {
        open(url);
    }

    public void click(By locator) {
        $(locator).shouldBe(visible).click();
    }

    public void type(By locator, String text) {
        $(locator).shouldBe(visible).setValue(text);
    }

    public void waitForElementVisible(By locator) {
        $(locator).shouldBe(visible);
    }

    public void waitForElementClickable(By locator) {
        $(locator).shouldBe(visible, enabled);
    }

    public String getText(By locator) {
        return $(locator).shouldBe(visible).getText();
    }

    public void selectFromDropdown(By locator, String value) {
        $(locator).shouldBe(visible).selectOption(value);
    }

    public void takeScreenshot(String name) {
        screenshot(name);
    }

    public void hoverOver(By locator) {
        $(locator).shouldBe(visible).hover();
    }

    public void clearField(By locator) {
        $(locator).shouldBe(visible).clear();
    }

    public boolean isElementDisplayed(By locator) {
        return $(locator).is(visible);
    }

    public void waitForPageLoad() {
        Selenide.Wait().until(driver ->
            executeJavaScript("return document.readyState").equals("complete"));
    }
}
