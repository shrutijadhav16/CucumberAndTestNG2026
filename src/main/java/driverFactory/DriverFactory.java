package driverFactory;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.GlobalVariables;

public class DriverFactory {

	public WebDriver initilizeDriver() {
		WebDriver driver = null;
		Properties prop = GlobalVariables.getInstance().getProperties();
		System.out.println("initDriver is running"+ GlobalVariables.getInstance().getProperties().getProperty("browser"));
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Please provide valid browser name");
		}
		GlobalVariables.getInstance().setDriver(driver);
		return driver;
		
	}
	
}
