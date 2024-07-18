package samKong.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import samKong.abstractComponents.AbstractComponents;

public class Checkout extends AbstractComponents {

	WebDriver driver;

	public Checkout(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Checkout
//			Actions a = new Actions(driver);
//			a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build()
//					.perform();
//
//			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
//
//			driver.findElement(By.xpath("(//button[contains(@class,'ta-item')]) [2]")).click();
//			driver.findElement(By.cssSelector(".action__submit")).click();
//
//			String confirm = driver.findElement(By.cssSelector(".hero-primary")).getText();
//	

	@FindBy(css = "input[placeholder='Select Country']")
	private WebElement countryTxtBox;

	@FindBy(css = ".ta-results")
	private WebElement results;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')]) [2]")
	private WebElement entry;
	
	@FindBy(css = ".action__submit")
	private WebElement checkOutBtn;
	
	public void inputCountry(String country) {
		
		Actions a = new Actions(driver);
		a.sendKeys(countryTxtBox, country).build().perform();
		waitForElementToAppear(results);
		
	}
	
	public void clickCountry() {
		
		entry.click();
		
	}

	public ConfirmationPage checkOut() {
		
		checkOutBtn.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		
		return confirmationPage;
		
	}
}
