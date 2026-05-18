package hooks;

import org.openqa.selenium.WebDriver;

import driverFactory.DriverFactory;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.GlobalVariables;

public class Hooks {
	@BeforeAll
	public static void beforeAll() {
		// BeforeAll - runs once before all scenarios
	}
	
	@Before
	public void before() {
		System.out.println("Before is running");
		DriverFactory driverFactory = new DriverFactory();
		WebDriver driver = driverFactory.initilizeDriver();
		System.out.println(GlobalVariables.getInstance().getProperties().getProperty("url"));
		driver.get(GlobalVariables.getInstance().getProperties().getProperty("url"));
	}
	
	@After
	public void after() {
		GlobalVariables.getInstance().getDriver().quit();
	}
	
	@AfterAll
	public static void afterAll() {
		// AfterAll - runs once after all scenarios
	}
	
}
