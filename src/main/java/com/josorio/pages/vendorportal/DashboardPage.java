package com.josorio.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.josorio.pages.AbstractPage;

public class DashboardPage extends AbstractPage {

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginElement;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(css = "#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement searchResultCountElement;

    @FindBy(id = "userDropdown")
    private WebElement userProfileDropdown;

    // Example purposes, preferable using id / name / css
    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement modalLogoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarningElement));
        return this.monthlyEarningElement.isDisplayed();
    }

    public String getMonthlyEarnings() {
        return this.monthlyEarningElement.getText();
    }

    public String getAnnualEarnings() {
        return this.annualEarningElement.getText();
    }

    public String getProfitMargin() {
        return this.profitMarginElement.getText();
    }

    public String getAvailableInventory() {
        return this.availableInventoryElement.getText();
    }

    public void searchOrderHistoryBy(String keyword) {
        this.searchInput.sendKeys(keyword);
    }

    public int getSearchResultsCount() {
        String resultText = this.searchResultCountElement.getText();
        String[] arr = resultText.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Number of results found: {}", count);
        return count;
    }

    public void logout() {
        this.userProfileDropdown.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
        this.modalLogoutButton.click();
    }
}
