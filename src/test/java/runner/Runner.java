package runner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import driverFactory.DriverFactory;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.GlobalVariables;

@CucumberOptions(
		features="src/test/resources/features/",
		glue= {"hooks","definations"},
		plugin= {"pretty","html:target/cucumber-reports.html","json:target/cucumber-reports.json"},
		monochrome=true
		)


public class Runner extends AbstractTestNGCucumberTests {
	
	@Parameters({"cucumber.features","cucumber.filter.name","cucumber.filter.tags"})
	@BeforeSuite
	public void beforeSuite(@Optional String feature, @Optional String scenario, @Optional String tags) {
		System.out.println("Before suite is running");
			Properties prop = new Properties();
			try {
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//config//repo.properties");
				prop.load(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Unable to load properties file"+ e.getMessage());
				e.printStackTrace();
			}
			
			GlobalVariables.getInstance().setProperties(prop);
			System.out.println("properties->"+GlobalVariables.getInstance().getProperties());
	}
	
	@DataProvider(parallel=true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
	@BeforeMethod
	public void initDriver() {
//		System.out.println("Before is running");
//		DriverFactory driverFactory = new DriverFactory();
//		WebDriver driver = driverFactory.initilizeDriver();
//		System.out.println(GlobalVariables.getInstance().getProperties().getProperty("url"));
//		driver.get(GlobalVariables.getInstance().getProperties().getProperty("url"));
	}
	

}
