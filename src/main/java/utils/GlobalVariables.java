package utils;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class GlobalVariables {
	private static GlobalVariables globalVariables;
	
	public static GlobalVariables getInstance(){
		if(globalVariables==null){
			globalVariables=new GlobalVariables();
			return globalVariables;
		}
		return globalVariables;
		
	}
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private Properties prop = new Properties();

	
	public WebDriver getDriver() {
		return driver.get();
	}
	public void setDriver(WebDriver driver) {
		GlobalVariables.driver.set(driver);;
	}

	public Properties getProperties() {
		return prop;
	}
	public void setProperties(Properties prop) {
		this.prop = prop;
	}

}
