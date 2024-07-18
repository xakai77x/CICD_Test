package samLearning.SeleniumFrameworkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import samKong.pageObjects.Cart;
import samKong.pageObjects.Checkout;
import samKong.pageObjects.ConfirmationPage;
import samKong.pageObjects.OrderPage;
import samKong.pageObjects.ProductCatalog;
import samLearning.SeleniumFrameworkDesign.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		// Product Catalog Page

		productCatalog.addProduct(input.get("productName"));
		Cart cart = productCatalog.goToCart();

		// Shopping Cart

		Boolean match = cart.inList(input.get("productName"));
		Assert.assertTrue(match);

		Checkout checkout = cart.goToCheckout();

		// Checkout

		String country = "India";

		checkout.inputCountry(country);
		checkout.clickCountry();
		ConfirmationPage confirmation = checkout.checkOut();

		// Confirmation

		String confirm = confirmation.getConfirmMessage();
		Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() throws InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication("binkong@gmail.com","Binkong1");
		OrderPage order = productCatalog.goToOrder();
		Boolean match = order.inOrderList(productName);
		Assert.assertTrue(match);
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJasonDataToMap(System.getProperty("user.dir")
				+ ("//src//test//java//samLearning//SeleniumFrameworkDesign//Data//PurchaseOrder.json"));
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
}
