package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils {
	
	public void waitUntilVisible(int Dura, WebDriver driver, By element) {
		Wait wait = new WebDriverWait(driver, Duration.ofSeconds(Dura));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public void waitUntilClickable(int Dura, WebDriver driver, By element) {
		Wait wait = new WebDriverWait(driver, Duration.ofSeconds(Dura));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void clickUsingActions(WebDriver driver , By element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(element)).click().build().perform();
	}

}
