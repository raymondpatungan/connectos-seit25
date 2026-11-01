package io.cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class BasicAuth extends Page {

    public BasicAuth(ChromeDriver driver) {
        super(driver);
    }

    public void authenticate(String username, String password) {
        String url = "https://" + username + ":" + password
                + "@the-internet.herokuapp.com/basic_auth";

        driver.get(url);
    }
    @FindBy(css = "div.example p")
    private WebElement successMessage;

    public String getSuccessMessage() {
        return successMessage.getText();
    }
}