package appiumtests;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertTrue;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class AddProductToCartTest {

	static AppiumDriver<MobileElement> driver;

	public static void main(String[] args) {
		try {
			openBreadfast();
		}catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
	}

	public static void openBreadfast() throws Exception {
		DesiredCapabilities cap = new DesiredCapabilities();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		cap.setCapability("deviceName", "OPPO Reno4");
		cap.setCapability("udid", "fd540204");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11");
		cap.setCapability("appPackage", "com.breadfast");
		cap.setCapability("appActivity", "com.breadfast.MainActivity");
		cap.setCapability("autoGrantPermissions", true);
		cap.setCapability("ignoreHiddenApiPolicyError", true);
		cap.setCapability("noReset", true);
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AppiumDriver<MobileElement>(url, cap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Application Started...");

		addProductToCart();

	}
	public static void addProductToCart() throws Exception {
		driver.findElementByAccessibilityId("Tomorrow_btn").click();
		driver.findElementByAccessibilityId("start_btn").click();
		Thread.sleep(500);
		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));

		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[286,1312][537,1676]']")).click();
		MobileElement productName = driver.findElement(By.xpath("//android.widget.TextView[@text='Roto Liquid Ball Ballpoint Black (Box of 12 Pens)']"));
		System.out.println(productName);
		driver.findElementByAccessibilityId("AddToCart_btn").click();
		driver.findElement(By.xpath("//android.widget.ImageView[@bounds='[942,294][1008,360]']")).click();

		assertTrue(productName.isDisplayed());
		Thread.sleep(1000);
		driver.quit();


	}



}
