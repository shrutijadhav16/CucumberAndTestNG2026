package driverFactory;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
			ChromeOptions options = new ChromeOptions();
			// Enable headless mode for CI/CD environments
			if (isRunningInCI()) {
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");
			}
			driver = new ChromeDriver(options);
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
	
	// Helper method to detect if running in CI/CD environment
	private boolean isRunningInCI() {
		return System.getenv("GITHUB_ACTIONS") != null || 
		       System.getenv("CI") != null ||
		       System.getenv("BUILD_ID") != null;
	}
	
}
