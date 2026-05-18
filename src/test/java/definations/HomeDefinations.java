package definations;

import io.cucumber.java.en.Then;
import pages.HomePage;

public class HomeDefinations {
	
	HomePage homePage = new HomePage();
	
	@Then("user encounter error")
	public void user_encounters_error() {
		homePage.getErrorPopuMessage();
		
	}

	@Then("user adds {string} product to cart")
	public void user_adds_product_to_cart(String productName) {
		homePage.addProductToCart(productName);
		homePage.clickCartLink();
	}
}
