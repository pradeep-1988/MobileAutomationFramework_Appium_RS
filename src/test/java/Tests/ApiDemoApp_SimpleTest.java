package Tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Reusable.TestData;
import Reusable.baseAndroid;
import Utilities.AppiumSeverUtilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ApiDemoApp_SimpleTest extends baseAndroid {

	@BeforeTest
	public static void KillAppiumServer() throws IOException {
		String[] args = new String[] {"/bin/bash", "-c", "killall node", "with", "args"};
		Process proc = new ProcessBuilder(args).start();
		
	}
	
	@Test(dataProvider="InputData",dataProviderClass=TestData.class)
	public static void ApiDemoAppSimpleTest_Scenario(String input) throws IOException, InterruptedException {	

		AppiumSeverUtilities.startAppiumServer();
		AndroidDriver<AndroidElement> driver = Capabilities("ApiDemoApp");
		
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='Preference']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		driver.findElement(By.className("android.widget.EditText")).sendKeys(input);
		
		//Find all the elements with same className and click on 2nd element.
		driver.findElements(By.className("android.widget.Button")).get(1).click();
		
		//Find element using AndroidUIAutomator
		driver.findElementByAndroidUIAutomator("className(\"android.widget.CheckBox\")").click();
		
		driver.navigate().back();
		
		//Find how many elements on the page are enabled/clickable/checkable etc
		int count = driver.findElementsByAndroidUIAutomator("new UiSelector().enabled(true)").size();
		System.out.println(count);
		
		//Close the emulator
		String[] args = new String[] {"/bin/bash", "-c", "/Users/pradeep/Library/Android/sdk/platform-tools/adb -s emulator-5554 emu kill", "with", "args"};
		Process proc = new ProcessBuilder(args).start();		
		Thread.sleep(2000);
		
		//Stopping the Appium server
		AppiumSeverUtilities.stopAppiumServer();


	}
	

}
