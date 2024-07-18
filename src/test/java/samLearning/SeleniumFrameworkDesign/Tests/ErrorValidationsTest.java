package samLearning.SeleniumFrameworkDesign.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import samKong.pageObjects.Cart;
import samKong.pageObjects.Checkout;
import samKong.pageObjects.ConfirmationPage;
import samKong.pageObjects.ProductCatalog;
import samLearning.SeleniumFrameworkDesign.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"}, retryAnalyzer = samLearning.SeleniumFrameworkDesign.TestComponents.Retry.class)
	public void errorValidation() throws IOException, InterruptedException {
		
		landingPage.loginApplication("binkon@gmail.com", "Binkong");
		Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
		
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("binkong@gmail.com", "Binkong1");

		// Product Catalog Page

		productCatalog.addProduct(productName);
		Cart cart = productCatalog.goToCart();

		// Shopping Cart

		Boolean match = cart.inList("ZARA COAT 33");
		Assert.assertFalse(match);

	}
}
