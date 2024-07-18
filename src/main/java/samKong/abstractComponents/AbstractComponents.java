package samKong.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import samKong.pageObjects.Cart;
import samKong.pageObjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement myOrdersBtn;
	
	
	public Cart goToCart() {

		cartBtn.click();
		Cart cart = new Cart(driver);
		return cart;

	}
	
	public OrderPage goToOrder() {
		
		myOrdersBtn.click();
		OrderPage orderPage = new OrderPage(driver);
		
		return orderPage;
		
	}
	
	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void waitForElementToDisappear(WebElement element) throws InterruptedException {

		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	

}
