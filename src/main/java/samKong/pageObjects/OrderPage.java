package samKong.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import samKong.abstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;

	public OrderPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	//PageFactory
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> orderList;
	
	@FindBy(css=".totalRow button")
	private WebElement checkoutBtn;
	
	public boolean inOrderList(String productName) throws InterruptedException {
		
		Boolean inList = orderList.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return inList;
		
	}
	
}
