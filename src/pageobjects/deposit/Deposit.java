package pageobjects.deposit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.XYZObject;


/**
 * 
 * @author vmngomezulu
 *
 */
public class Deposit extends XYZObject {


	public Deposit(WebDriverWait waitdriver) {
		super(waitdriver);
	}

	private By depositButton = By.xpath("/html/body/div[3]/div/div[2]/div/div[3]/button[2]");
	private By depositTextBox = By.xpath("//input[@placeholder='amount']");
	private By confirmDepositButton = By.xpath("//button[text()='Deposit']");
	private By depositSuccessful = By.xpath("//span[text()='Deposit Successful']");

	
	/**
	 * Deposits the specified amount
	 * @param amount
	 */
	public void DepositAmount(String amount) {
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(depositButton)).click();
		waitWebDriver.until(ExpectedConditions.visibilityOfElementLocated(depositTextBox)).sendKeys(amount);
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(confirmDepositButton)).click();
	}
	
	/**
	 * Verifies that the deposit was successful
	 * @return
	 */
	public boolean depositSuccessful() {
		boolean success = waitWebDriver.until(ExpectedConditions.visibilityOfElementLocated(depositSuccessful))
				.isDisplayed();
		return success;
	}
	
}
