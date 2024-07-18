package samKong.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import samKong.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	// PageFactory
	@FindBy(id="userEmail")
    private WebElement userEmail;
	
	@FindBy(id="userPassword")
	private WebElement userPassword;
	
	@FindBy(id="login")
	private WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	private WebElement errorMessage;
	
	public ProductCatalog loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client/");
		
	}
	
	public String getErrorMessage() {
		
		waitForElementToAppear(errorMessage);
		String error = errorMessage.getText();
		
		return error;
	}
	
}
