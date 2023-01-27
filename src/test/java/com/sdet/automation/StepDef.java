package com.sdet.automation;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

import com.sdet.core.BaseClass;
import com.sdet.pages.Homepage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDef extends BaseClass {

	Homepage homepage = null;
	FileReader reader = null;
	Properties prop = null;

	@Before
	public void setup() throws IOException {

//		Enable below code for headless browser and comment Line 34 and 35.
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("headless");
//		driver = new ChromeDriver(options);

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		homepage = new Homepage(driver);
		reader = new FileReader("application.properties");
		prop = new Properties();
		prop.load(reader);
	}

	@Given("^Application is opened and on Home Page$")
	public void openHomePage() {
		driver.get(prop.getProperty("appName"));
	}

	@When("^User perform search for a \"([^\"]*)\"$")
	public void searchAndAdd(String arg1) throws InterruptedException {
		homepage.searchAndAddGelPen(arg1);
	}

	@Then("^goto Cart and empty cart$")
	public void addToCartAndEmptyCart() throws InterruptedException {
		homepage.verifyCart();
	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
