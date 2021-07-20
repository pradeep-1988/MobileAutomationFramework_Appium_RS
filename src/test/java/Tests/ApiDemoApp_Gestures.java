package Tests;

import org.testng.annotations.Test;

import APIDemoApp_PageObjects.HomePage;
import APIDemoApp_PageObjects.ViewsPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Reusable.baseAndroid;
import Utilities.AppiumSeverUtilities;
import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ApiDemoApp_Gestures extends baseAndroid {

	//public static void main(String[] args) throws InterruptedException, IOException {

	@Test
	public static void ApiDemoAppGesture_Scenario() throws IOException, InterruptedException {	
		
		AppiumSeverUtilities.startAppiumServer();
		AndroidDriver<AndroidElement> driver = Capabilities("ApiDemoApp");
		
		//Create an object of the HopePage
		HomePage HP = new HomePage(driver);
		
		//driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		HP.ViewsField.click();
		
		//In Appium, for performing gestures on mobile device, we need to use TouchActions class
		
		//Tap gesture
		TouchAction t = new TouchAction(driver);
		
		//Create an object of the ViewsPage
		ViewsPage VP = new ViewsPage(driver);
				
		//AndroidElement expList = driver.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");
		AndroidElement expList = VP.Expandable_Lists;
		t.tap(tapOptions().withElement(element(expList))).perform();
		
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='1. Custom Adapter']")).click();
		
		
		//Long Pres gesture
		AndroidElement pn = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));		
		t.longPress(longPressOptions().withElement(element(pn)).withDuration(ofSeconds(2))).release().perform();
		System.out.println("If output is true, then longPress worked");
		System.out.println("And output is: "+ driver.findElement(By.xpath("//android.widget.TextView[@text='Sample menu']")).isDisplayed());
		
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		
		driver.findElementByAndroidUIAutomator("text(\"Date Widgets\")").click();
		driver.findElementByAndroidUIAutomator("text(\"2. Inline\")").click();
		
		driver.findElement(By.xpath("//*[@content-desc='9']")).click();
		
		//Swipe gesture
		AndroidElement ele1 = driver.findElement(By.xpath("//*[@content-desc='15']"));	
		AndroidElement ele2 = driver.findElement(By.xpath("//*[@content-desc='30']"));
		AndroidElement ele3 = driver.findElement(By.xpath("//*[@content-desc='45']"));
		t.longPress(longPressOptions().withElement(element(ele1)).withDuration(ofSeconds(1)))
		.moveTo(element(ele2))
		.moveTo(element(ele3))
		.release().perform();
		
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		
		//Scroll: Scroll towards up/down until the expected element scrolls into view
		//In below statement, we are executing android API code
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Picker\"));");
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Drag and Drop\"));");
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='Drag and Drop']")).click();
		
		//Drag & Drop
		AndroidElement sourceElement = driver.findElements(By.className("android.view.View")).get(0);	
		AndroidElement destElement1 = driver.findElements(By.className("android.view.View")).get(1);
		AndroidElement destElement2 = driver.findElements(By.className("android.view.View")).get(2);
		
		t.longPress(longPressOptions().withElement(element(sourceElement)).withDuration(ofSeconds(1)))
		.moveTo(element(destElement1))
		.release().perform();
		
		Thread.sleep(1000);
		
		//Another way
		t.longPress(element(sourceElement))
		.moveTo(element(destElement2))
		.release().perform();
		
		
		//Close the emulator
		String[] args = new String[] {"/bin/bash", "-c", "/Users/pradeep/Library/Android/sdk/platform-tools/adb -s emulator-5554 emu kill", "with", "args"};
		Process proc = new ProcessBuilder(args).start();		
		Thread.sleep(2000);
		
		//Stopping the Appium server
		AppiumSeverUtilities.stopAppiumServer();
		
		
		
		
		

	}

}
