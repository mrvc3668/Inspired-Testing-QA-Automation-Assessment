package pageobjects.account;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.XYZObject;
import pageobjects.Balance.Balance;
import pageobjects.deposit.Deposit;
import pageobjects.transaction.Transaction;
import pageobjects.withdrawal.Withdrawal;

/**
 * 
 * @author vmngomezulu
 *
 */
public class Account extends XYZObject {
	
	public Deposit accountDeposit;
	public Withdrawal accountWithdrawal;
	public Balance accountBalance;
	public Transaction accountTransaction;
	
	private By selectAccount = By.id("accountSelect");
	private By firstAccountNumber = By.xpath("//*[@id=\"accountSelect\"]/option[1]");
	private By secondAccountNumber = By.xpath("//*[@id=\"accountSelect\"]/option[2]");
	private By thirdAccountNumber = By.xpath("//*[@id=\"accountSelect\"]/option[3]");
	
	public Account(WebDriverWait waitdriver) {
		super(waitdriver);
		accountDeposit = new Deposit(waitdriver);
		accountWithdrawal = new Withdrawal(waitdriver);
		accountBalance = new Balance(waitdriver);
		accountTransaction = new Transaction(waitdriver);
	}

	/**
	 * 
	 * @param account
	 */
	public void SelectAccount(char account) {

		waitWebDriver.until(ExpectedConditions.elementToBeClickable(selectAccount)).click();

		switch (account) {
		case 'A': {
			waitWebDriver.until(ExpectedConditions.elementToBeClickable(firstAccountNumber)).click();
		}
			break;
		case 'B': {
			waitWebDriver.until(ExpectedConditions.elementToBeClickable(secondAccountNumber)).click();
		}
			break;
		case 'C': {
			waitWebDriver.until(ExpectedConditions.elementToBeClickable(thirdAccountNumber)).click();
		}
			break;
		}
	}

}
