package samKong.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import samKong.abstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	//Confirmation
//		String confirm = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	@FindBy(css = ".hero-primary")
	private WebElement confirmationMessage;
	
	public String getConfirmMessage() throws InterruptedException {
		
		String confirmMessage = confirmationMessage.getText();
		return confirmMessage;
	}
	
}
