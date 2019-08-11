package com.stepdef;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NationWideScreen {

	WebDriver driver=null;

	@Given("^user launch the browser$")
	public void userLaunchBrowser()
		{
		System.setProperty("webdriver.chrome.driver","C:\\Prabha\\chromedriver.exe");

		driver=new ChromeDriver();
		driver.manage().window().maximize();
		}

	@When("^user enter the url$")
	public void userEnteredUrl() throws InterruptedException
		{
		driver.get("https://nationwide.co.uk");
		}

	@And("^user should see Nationwide screen$")
	public void nationwideScreenDisplayed()
		{
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.id("content_1_header_0_primaryheader_1_logonavigation_0_NationwideHomeLogo")));
		driver.findElement(By.id("content_1_header_0_primaryheader_1_logonavigation_0_NationwideHomeLogo")).isDisplayed();
		}

	@And("^user to move to Mortgages option$")
	public void selectMortgage()
		{
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='MortgagesNavItem']/a")));
		Actions actions = new Actions(driver);  
		WebElement menuOption = driver.findElement(By.xpath("//li[@id='MortgagesNavItem']/a"));
		actions.moveToElement(menuOption).build().perform();
		}

	@And("^navigate to New Mortgage customer and click on Mortgage Rates$")
	public void selectMortgageRates()
		{
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='Mortgage rates (New mortgage customers)']")));
		driver.findElement(By.xpath("//a[@aria-label='Mortgage rates (New mortgage customers)']")).click();
		}

	@And("^select option NO for Nationwide Mortgage$")
	public void selectNationwideMortOption()
		{
		driver.findElement(By.xpath("//*[@id='selectorItemHaveNationwideMortgage1']/span")).click();
		}

	@And("^select CHANGING LENDER as Mortgage type$")
	public void selectNationwideMortType()
		{
		driver.findElement(By.xpath(".//*[@id='selectorItemNationwideMortgageTypeNo2']/span")).click();
		}

	@And("^user to enter property value$")
	public void enterPropertyValue()
		{
		driver.findElement(By.xpath(".//*[@id='SearchPropertyValue']")).sendKeys("300,000");
		}

	@And("^user to enter Mortgage Amount$")
	public void enterMortgageAmount()
		{
		driver.findElement(By.xpath(".//*[@id='SearchMortgageAmount']")).sendKeys("150,000");
		}

	@And("^user to enter Term details$")
	public void enterTerm()
		{
		driver.findElement(By.xpath(".//*[@id='SearchMortgageTerm']")).sendKeys("30");
		}

	@And("^user to click Find Mortgage Rate$")
	public void mortgageRates()
		{
		driver.findElement(By.xpath(".//*[@id='myButton']")).click();
		}

	@And("^user to select Mortgage Type$")
	public void mortgageType() throws InterruptedException
		{
		WebElement mortgageType=driver.findElement(By.xpath("//*[@id='fixed']/span"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", mortgageType );
		}

	@And("^user to select Product fee$")
	public void productFee() throws InterruptedException
		{
		WebElement productFee=driver.findElement(By.xpath("//*[@id='product-fee-fee']/span"));
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", productFee );
		Thread.sleep(3000);
		}

	@And("^user to check the details displayed in the screen$")
	public void screenValidation()
		{
new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='NewMortgageRateTables']//th[@class='notOnMobile productHeading ']//h3[starts-with(@id,'table')]")));
List<WebElement> elements=driver.findElements(By.xpath("//div[@id='NewMortgageRateTables']//th[@class='notOnMobile productHeading ']//h3[starts-with(@id,'table')]"));

		Set<String> actualData=new TreeSet();

		for(int i=0;i<elements.size();i++) 
		{
		actualData.add(elements.get(i).getText());
		}

		Set<String> expectedData=new TreeSet();
		expectedData.add("2 yr Fixed");
		expectedData.add("3 yr Fixed");
		expectedData.add("5 yr Fixed");
		expectedData.add("10 yr Fixed");
		System.out.println("Expected Data "+expectedData);
		Assert.assertTrue(actualData.containsAll(expectedData));
		}

	@And("^user to click on MORE INFO and APPLY for 5 Yr Fixed$")
	public void fiveYrFixed() throws InterruptedException
		{
new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),' for 5 yr  Fixed')]/following-sibling::span[@class='iconText closed']")));
driver.findElement(By.xpath("//span[contains(text(),' for 5 yr  Fixed')]/following-sibling::span[@class='iconText closed']")).click();
		}

	@And("^user to click APPLY button$")
	public void applyButton()
		{
new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-productname='5 yr Fixed ']")));
driver.findElement(By.xpath("//a[@data-productname='5 yr Fixed ']")).click();
		}

	@And("^user to validate the text START YOUR REMORTGAGE APPLICATION$")
	public void validateText()
		{
		driver.findElement(By.xpath("//h1[contains(.,'Start your Remortgage application')]")).isDisplayed();
		}

	@Then("^user to close the screen$")
	public void closeScreen() throws Throwable
		{
		driver.close();
		}

	public void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor)driver;

		//Initially below given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete"))
		{
			System.out.println("Page Is loaded.");
			return;
		}

		//This loop will rotate for 25 times to check If page Is ready after every 1 second.
		//You can replace your value with 25 If you wants to Increase or decrease wait time.
		for (int i=0; i<25; i++){
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {}
			//To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")){
				break;
			}
 
		}
	}
}