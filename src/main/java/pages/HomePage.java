package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.messages.types.Duration;
import utils.CommonUtils;
import utils.GlobalVariables;

public class HomePage {
	WebDriver driver =  GlobalVariables.getInstance().getDriver();
	CommonUtils commonUtils = new CommonUtils();
	By byHeading = By.xpath("//p[contains(text(),'Automation Practice')]");
	By byErrorPopUp = By.xpath("//div[contains(text(),' Incorrect email or password. ')]");
	By cartLink = By.xpath("//button[@routerlink='/dashboard/cart']");
	
	 public void clickCartLink() {
		 commonUtils.waitUntilVisible(10, driver, cartLink);
		 commonUtils.waitUntilClickable(10, driver, cartLink);
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 commonUtils.clickUsingActions(driver, cartLink);
//		 driver.findElement(cartLink).click();
	 }
	
	
	
	public String getHomePageHeading() {
		commonUtils.waitUntilVisible(10, driver, byHeading);
		 return driver.findElement(byHeading).getText();
	 }
	 
	 public String getErrorPopuMessage() {
			commonUtils.waitUntilVisible(10, driver, byErrorPopUp);
		 return driver.findElement(byErrorPopUp).getText();
	 }
	 
	 public void addProductToCart(String productName) {
		 By byProduct = By.xpath("//b[contains(text(),'"+productName+"')]/parent::h5/following-sibling::button[contains(text(),' Add To Cart')]");
		 commonUtils.waitUntilVisible(10, driver, byProduct);
		 driver.findElement(byProduct).click();
	 }

}
