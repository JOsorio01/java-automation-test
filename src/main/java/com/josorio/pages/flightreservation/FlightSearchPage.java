package com.josorio.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.josorio.pages.AbstractPage;

public class FlightSearchPage extends AbstractPage {

    // @FindBy(id = "oneway")
    // private WebElement oneWayRadioButton;
    // @FindBy(id = "twoway")
    // private WebElement roundTripRadioButton;
    @FindBy(id = "passengers")
    private WebElement passengersDropdown;

    // @FindBy(id = "depart-from")
    // private WebElement departFromDropdown;
    // @FindBy(id = "arrive-in")
    // private WebElement arriveInDropdown;
    // @FindBy(id = "service-class1")
    // private WebElement economicRadioButton;
    // @FindBy(id = "service-class2")
    // private WebElement firstClassRadioButton;
    // @FindBy(id = "service-class3")
    // private WebElement businessRadioButton;
    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengersDropdown));
        return this.passengersDropdown.isDisplayed();
    }

    public void selectPassenger(String noOfPassengers) {
        Select passengers = new Select(this.passengersDropdown);
        passengers.selectByValue(noOfPassengers);
    }

    public void searchFlights() {
        this.searchFlightsButton.click();
    }
}
