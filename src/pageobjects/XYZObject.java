package pageobjects;

import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Main class with the driver instance
 * @author vmngomezulu
 *
 */
public class XYZObject {

	protected WebDriverWait waitWebDriver;

	public XYZObject(WebDriverWait waitdriver) {
		this.waitWebDriver = waitdriver;
	}
}
