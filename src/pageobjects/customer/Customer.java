package pageobjects.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.XYZObject;
import pageobjects.account.Account;

/**
 * 
 * @author vmngomezulu
 *
 */
public class Customer extends XYZObject{
	
	public Account customerAccount;	 
	
	private By customerLoginButton = By.xpath("//button[text()='Customer Login']");
	private By userSelect = By.id("userSelect");
	private By loginName = By.xpath("//option[text()='Hermoine Granger']");
	private By loginButton = By.xpath("//button[text()='Login']");
	private By logoutButton = By.xpath("//button[text()='Logout']");

	public Customer(WebDriverWait waitdriver) {
		super(waitdriver);
		customerAccount = new Account(waitdriver);
	}

	
	/**
	 * 
	 */
	public void LoginAsCustomer() {
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(customerLoginButton)).click();
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(userSelect)).click();
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(loginName)).click();
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}

	/**
	 * 	
	 */
	public void LogOut() {
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
	}
}
