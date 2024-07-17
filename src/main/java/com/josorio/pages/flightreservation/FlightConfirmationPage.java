package com.josorio.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.josorio.pages.AbstractPage;

public class FlightConfirmationPage extends AbstractPage {

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) p.fw-bold")
    private WebElement flightConfirmationElement;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) p.fw-bold")
    private WebElement totalPriceElement;

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationElement));
        return this.flightConfirmationElement.isDisplayed();
    }

    public String getPrice() {
        String confirmation = this.flightConfirmationElement.getText();
        String price = this.totalPriceElement.getText();
        log.info("Flight confirmation #{}", confirmation);
        log.info("Total Price: {}", price);
        return price;
    }

}
