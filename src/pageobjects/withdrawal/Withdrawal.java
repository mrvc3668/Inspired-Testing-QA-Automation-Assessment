package pageobjects.withdrawal;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.XYZObject;


/**
 * 
 * @author vmngomezulu
 *
 */
public class Withdrawal extends XYZObject {

	public Withdrawal(WebDriverWait waitdriver) {
		super(waitdriver);
	}

	private By withdrawalButton = By.xpath("/html/body/div[3]/div/div[2]/div/div[3]/button[3]");
	private By withrdrawalTextBox = By.xpath("/html/body/div[3]/div/div[2]/div/div[4]/div/form/div/input");
	private By confirmWithdrawalButton = By.xpath("//button[text()='Withdraw']");
	
	/**
	 * Withdraws specified amount
	 * @param amount
	 */
	public void WithdrawAmount(String amount) {
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(withdrawalButton)).click();
		waitWebDriver.until(ExpectedConditions.visibilityOfElementLocated(withrdrawalTextBox)).sendKeys(amount);
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(confirmWithdrawalButton)).click();
	}

}
