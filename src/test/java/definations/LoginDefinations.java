package definations;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;

public class LoginDefinations {
	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();

@Given("user is already on Login page")
public void user_is_already_on_login_page() {
	Assert.assertTrue("Let's Shop".equalsIgnoreCase(loginPage.getTitle()));
	
}
@When("user enters {string} and {string}")
public void user_enters_and(String username, String password) {
	loginPage.enterUserDetails(username, password);
}

@And("user clicks on login button")
public void user_clicks_on_login_button() {
	loginPage.clickLogin();
	
}
@Then("user is on home page")
public void user_is_on_home_page() {
	Assert.assertTrue("Automation Practice".equalsIgnoreCase(homePage.getHomePageHeading()));

}
}
