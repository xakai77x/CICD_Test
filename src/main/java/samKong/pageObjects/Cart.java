package samKong.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import samKong.abstractComponents.AbstractComponents;

public class Cart extends AbstractComponents {

	WebDriver driver;

	public Cart(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	//PageFactory
	@FindBy(css=".cartSection h3")
	private List<WebElement> cart;
	
	@FindBy(css=".totalRow button")
	private WebElement checkoutBtn;
	
	public boolean inList(String productName) throws InterruptedException {
		
		Boolean inList = cart.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return inList;
		
	}
	
	public Checkout goToCheckout() {
		
		checkoutBtn.click();
		Checkout checkout = new Checkout(driver);
		return checkout;
		
		
	}
	
}
