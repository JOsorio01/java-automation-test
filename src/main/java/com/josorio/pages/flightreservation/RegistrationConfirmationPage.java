package com.josorio.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.josorio.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightSearchButton;

    @FindBy(css = "#registration-confirmation-section b")
    private WebElement firstName;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchButton));
        return this.goToFlightSearchButton.isDisplayed();
    }

    public String getFirstName() {
        return this.firstName.getText();
    }

    public void goToFlightSearch() {
        this.goToFlightSearchButton.click();
    }
}
