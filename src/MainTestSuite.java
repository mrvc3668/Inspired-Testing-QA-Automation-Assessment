import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import pageobjects.account.Account;
import pageobjects.customer.Customer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import java.io.FileNotFoundException;
import java.io.FileReader;


/**
 * Main test suite
 * @author vmngomezulu
 *
 */
public class MainTestSuite {
	private WebDriver driver;
	private WebDriverWait waitWebDriver;	
	private Customer maincustomer;
	private Account mainAccount;
	
	@BeforeSuite
	public void beforeTestExecute() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		waitWebDriver = new WebDriverWait(driver, 20);
		maincustomer = new Customer(waitWebDriver);
		mainAccount = new Account(waitWebDriver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.way2automation.com/angularjs-protractor/banking/#/login");
	}	
	
	/**
	 * Uses GSon library example referenced from: 
	 * https://stackoverflow.com/questions/45146523/reading-data-from-json-file-for-data-provider-in-testng
	 * @return
	 */
	@DataProvider(name="ConsumerProvider")
    public Object[][] getDataFromDataprovider(){
	    JsonElement jsonData = null;
		try {
			jsonData = new JsonParser().parse(new FileReader("./json/JsonData.json"));
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        JsonElement dataSet = jsonData.getAsJsonObject().get("ConsumerData");
        List<Consumer> testData = new Gson().fromJson(dataSet, new TypeToken<List<Consumer>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
		
    }
	
	@Test(description="Validate the Deposit Was successful")
	public void Test1() {
		maincustomer.LoginAsCustomer();
		maincustomer.customerAccount.SelectAccount('A');
		mainAccount.accountDeposit.DepositAmount("1500");
		Assert.assertTrue(mainAccount.accountDeposit.depositSuccessful());
		maincustomer.LogOut();
	}
	
	@Test(description="On each account, Validate the Deposit Was successful")
	public void Test2() {
		maincustomer.LoginAsCustomer();
		maincustomer.customerAccount.SelectAccount('A');
		mainAccount.accountDeposit.DepositAmount("1500");
		Assert.assertTrue(mainAccount.accountDeposit.depositSuccessful());
		
		maincustomer.customerAccount.SelectAccount('B');
		mainAccount.accountDeposit.DepositAmount("1500");
		Assert.assertTrue(mainAccount.accountDeposit.depositSuccessful());
		
		maincustomer.customerAccount.SelectAccount('C');
		mainAccount.accountDeposit.DepositAmount("1500");
		Assert.assertTrue(mainAccount.accountDeposit.depositSuccessful());
		
		maincustomer.LogOut();
	}

	@Test(description="Validate the transactions Were successful")
	public void Test3() {
		maincustomer.LoginAsCustomer();
		maincustomer.customerAccount.SelectAccount('A');
		String initialBalance = mainAccount.accountBalance.GetBalance();
		mainAccount.accountDeposit.DepositAmount("31459");
		Assert.assertTrue(mainAccount.accountDeposit.depositSuccessful());
		
		mainAccount.accountTransaction.ClickTransaction();
		driver.navigate().refresh();

		Assert.assertEquals(mainAccount.accountTransaction.ValidateTransaction("31459"), "31459");
		
		mainAccount.accountTransaction.ClickBackButton();
		
		mainAccount.accountWithdrawal.WithdrawAmount("31459");
				
		Assert.assertEquals(initialBalance, "0");
		
		mainAccount.accountTransaction.ClickTransaction();
		driver.navigate().refresh();

		Assert.assertEquals(mainAccount.accountTransaction.ValidateTransaction("31459"), "31459");
		
		maincustomer.LogOut();
	}
	
	@Test(dataProvider = "ConsumerProvider")
	public void Test4(Consumer data) {
		maincustomer.LoginAsCustomer();
		maincustomer.customerAccount.SelectAccount(data.getAccount());
		String initialBalance = mainAccount.accountBalance.GetBalance();
		mainAccount.accountDeposit.DepositAmount(data.getDepositAmount());
		Assert.assertTrue(mainAccount.accountDeposit.depositSuccessful());
		
		mainAccount.accountTransaction.ClickTransaction();
		driver.navigate().refresh();

		Assert.assertEquals(mainAccount.accountTransaction.ValidateTransaction(data.getDepositAmount()), "31459");
		
		mainAccount.accountTransaction.ClickBackButton();
		
		mainAccount.accountWithdrawal.WithdrawAmount(data.getWithdrawalAmount());
				
		Assert.assertEquals(initialBalance, "0");
		
		mainAccount.accountTransaction.ClickTransaction();
		driver.navigate().refresh();

		Assert.assertEquals(mainAccount.accountTransaction.ValidateTransaction(data.getWithdrawalAmount()), "31459");
		
		maincustomer.LogOut();	
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
