package pageobjects.Balance;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.XYZObject;

/**
 * 
 * @author vmngomezulu
 *
 */
public class Balance extends XYZObject{
	
	public Balance(WebDriverWait waitdriver) {
		super(waitdriver);
	}


	private By originalBalance = By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/strong[2]");

	/**
	 * Returns the balance
	 * @return
	 */
	public String GetBalance() {
		String balance = waitWebDriver.until(ExpectedConditions.elementToBeClickable(originalBalance)).getText();
		return balance;
	}
}
