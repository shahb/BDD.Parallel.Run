package com.softcrylic.testautomation.step_definitions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.softcrylic.testautomation.pages.HomePage;
import com.softcrylic.testautomation.pages.LocationPage;
import com.softcrylic.testautomation.pages.SearchResultPage;

import org.junit.Assert;

public class SearchStepDefinitions {
    private WebDriver driver;
    private HomePage home;
    private SearchResultPage searchResult;

    @Before
    public void prepare() throws MalformedURLException {
    	 //For local machine
    	 String url = "http://localhost:4444/wd/hub";
    	 //For Saucelabs
    	 //replace above URL ("http://localhost:4444/wd/hub") with Saucelabs URL
    	 System.out.println("Running at: "+url);
    	 //Switch browsers
    	 //For chrome
    	 DesiredCapabilities capabillities = DesiredCapabilities.chrome();
    	 //For firefox
    	 //DesiredCapabilities capabillities = DesiredCapabilities.firefox();
    	 //if(url.contains("saucelabs")) 
         //capabillities.setCapability("version", "11");
    	 //else
    	 //capabillities.setCapability("version", "12.0"); //Change the browser version here if running on local machine
    	 //For Windows XP
         capabillities.setCapability("platform", Platform.XP);
         //For MAC
         //capabillities.setCapability("platform", Platform.MAC);
         
         capabillities.setCapability("name", "Running via Jenkins. Testing on Sauce");
         capabillities.setCapability("record-video", false);

         this.driver = new RemoteWebDriver(
        		 new URL(url),
            capabillities);
         driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void cleanUp() {
    	System.out.println("Count is: @cleanUp " + ++count);
        driver.close();
        try{
        	if(driver!=null) driver.quit();
        }catch(Exception ignore){}
    }

    @Given("^I want to know the weather forecast for coming days$")
    public void prepareHomePage() {
    	System.out.println("Count is: @prepareHomePage " + ++count);
        home = new HomePage(driver);
    }

    @When("^I search for (.*)$")
    public void search(String location) {
    	System.out.println("Count is: @search " + ++count);
        searchResult = home.searchFor(location);
    }

    @Then("^I should be able to get a weather forecast for (.*)$")
    public void assertTheSearchResult(String locationName) {
    	System.out.println("Count is: @assertTheSearchResult " + ++count);
        LocationPage location = searchResult.clickOnTopSearchResultLink();
        String actualHeadLine = location.getHeadLine();
        Assert.assertTrue(actualHeadLine.contains(locationName));
    }
    private static int count =0;
}