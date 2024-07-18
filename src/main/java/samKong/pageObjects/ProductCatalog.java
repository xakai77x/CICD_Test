package samKong.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import samKong.abstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// PageFactory
	@FindBy(css = ".mb-3")
	private List<WebElement> catalog;
	
	@FindBy(css = ".ng-animating")
	private WebElement animation;
	
	

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By alert = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {

		waitForElementToAppear(productsBy);
		List<WebElement> catalog = driver.findElements(productsBy);
		return catalog;

	}

	public WebElement getProductByName(String productName) {

		WebElement product = getProductList().stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		return product;
	}

	public void addProduct(String productName) throws InterruptedException {
		
		WebElement productToAdd = getProductByName(productName);
		productToAdd.findElement(addToCart).click();
		waitForElementToAppear(alert);
		waitForElementToDisappear(animation);
	}
	
	
}
