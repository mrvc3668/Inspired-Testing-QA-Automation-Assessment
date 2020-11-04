package pageobjects.transaction;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.XYZObject;

/**
 * 
 * @author vmngomezulu
 *
 */
public class Transaction extends XYZObject {
	
	public Transaction(WebDriverWait waitdriver) {
		super(waitdriver);
	}

	private By transactButton = By.xpath("/html/body/div[3]/div/div[2]/div/div[3]/button[1]");
	private By backButton = By.xpath("//button[text()='Back']");
	private By nextButton = By.xpath("/html/body/div[3]/div/div[2]/div/div[3]/button[3]");
	private By resetTransactions = By.xpath("//button[text()='Reset']");

	
	/**
	 * 
	 */
	public void ClickBackButton() {
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(backButton)).click();
	}
	
	/**
	 * 
	 */
	public void ClickNextButton() {
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
	}
	
	/**
	 * 
	 */
	public void ClickResetButton() {
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(resetTransactions)).click();
	}
	
	/**
	 * 
	 */
	public void ClickTransaction() {
		waitWebDriver.until(ExpectedConditions.elementToBeClickable(transactButton)).click();
	}
	
	/**
	 * Validates that the transaction occurred
	 * @param amount
	 * @return
	 */
	public String ValidateTransaction(String amount) {
		By transactionConfirm = By.xpath("//td[text()='" + amount + "']");
		String transaction = waitWebDriver.until(ExpectedConditions.visibilityOfElementLocated(transactionConfirm))
				.getText();

		return transaction;
	}


}
