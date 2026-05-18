package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.GlobalVariables;

public class LoginPage {
	By byUsername = By.id("userEmail");
	By byPassword = By.id("userPassword");
	By byLoginButton = By.id("login");
	WebDriver driver =  GlobalVariables.getInstance().getDriver();
	
	public void enterUserDetails(String username, String password) {
		driver.findElement(byUsername).sendKeys(username);
		driver.findElement(byPassword).sendKeys(password);
	}
	public void clickLogin() {
		driver.findElement(byLoginButton).click();
	}
	public String getTitle() {
		return driver.getTitle();
	}

}
