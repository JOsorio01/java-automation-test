package com.josorio.tests.vendorportal;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.josorio.pages.vendorportal.DashboardPage;
import com.josorio.pages.vendorportal.LoginPage;
import com.josorio.tests.AbstractTest;
import com.josorio.tests.vendorportal.model.VendorPortalTestData;
import com.josorio.utils.Config;
import com.josorio.utils.Constants;
import com.josorio.utils.JsonUtil;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void initialize(String testDataPath) {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest() {
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest() {
        Assert.assertTrue(dashboardPage.isAt());

        // Finance Metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarnings(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarnings(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        // Order history search
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest() {
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
