package com.josorio.tests.flightreservation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.josorio.pages.flightreservation.FlightConfirmationPage;
import com.josorio.pages.flightreservation.FlightSearchPage;
import com.josorio.pages.flightreservation.FlightSelectionPage;
import com.josorio.pages.flightreservation.RegistrationConfirmationPage;
import com.josorio.pages.flightreservation.RegistrationPage;
import com.josorio.tests.AbstractTest;
import com.josorio.tests.flightreservation.model.FlightReservationTestData;
import com.josorio.utils.Config;
import com.josorio.utils.Constants;
import com.josorio.utils.JsonUtil;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void initialize(String testDataPath) {
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserData(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterAdress(testData.street(), testData.city(), testData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
        registrationConfirmationPage.goToFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest() {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());

        flightSearchPage.selectPassenger(testData.passengerCount());
        flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest() {
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());

        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmFlight();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());

        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
    }
}
