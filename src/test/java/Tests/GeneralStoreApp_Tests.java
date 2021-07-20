package Tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GeneralStroreApp_PageObjects.CheckOutPage;
import GeneralStroreApp_PageObjects.FormPage;
import GeneralStroreApp_PageObjects.ProductsPage;

import java.net.MalformedURLException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import Reusable.baseAndroid;
import Utilities.AppiumSeverUtilities;
import Utilities.Scroll_Utilty;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

//Verify sum of amount of all the products on cart page, matches to the total amount to be paid.
public class GeneralStoreApp_Tests extends baseAndroid {

	//public static void main(String[] args) throws InterruptedException, IOException {
	
	
	@Test
	public static void GeneralStoreAppScenario() throws IOException, InterruptedException {	
		
		AppiumSeverUtilities.startAppiumServer();
		
		AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp");
		
		//Create an object of the FormPage
		FormPage FP = new FormPage(driver);
		
		
		//driver.findElementById("android:id/text1").click();
		FP.CountryField.click();
		
		//Select india from the dropdown. Here we need to scroll untill India displays into view.
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));"); 
		
		Scroll_Utilty SU = new Scroll_Utilty(driver);
		SU.scrollToText("Australia");
		
		//Some time above way doesn't work for scrolling an expected item into view, if so, the use below code:
		//driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + expectedText + "\").instance(0))")); 
		//driver.findElementByXPath("//*[@text='Australia']").click();
		FP.CountryAustralia.click();
		
		//driver.findElementByClassName("android.widget.EditText").sendKeys("Richa");
		FP.NameField.sendKeys("Richa");
		
		
		driver.hideKeyboard();
		
		//driver.findElementsByClassName("android.widget.RadioButton").get(1).click();
		FP.FemaleOption.click();
		
		//driver.findElementByClassName("android.widget.Button").click();
		FP.ShopButton.click();
		
		
		//Create an object of Products Page
		ProductsPage PP = new ProductsPage(driver);
		
		//Add first two products into the cart
		//driver.findElementsByXPath("//android.widget.TextView[@text='ADD TO CART']").get(0).click();
		PP.Products.get(0).click();
		
		//driver.findElementsByXPath("//android.widget.TextView[@text='ADD TO CART']").get(0).click();
		PP.Products.get(0).click();
		
		
		//click on cart button
		//driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		PP.GoToCartBtn.click();
		
		Thread.sleep(4000);
		
		//Create an object of CheckOutPage
		CheckOutPage CP = new CheckOutPage(driver);
		
		//Get the amount of products and add it.
		//int count = driver.findElementsById("com.androidsample.generalstore:id/productPrice").size();
		int count = CP.ProductsPrice.size();
		double sumOfProducts = 0;
		for(int i =0;i<count;i++) {
			String amount = CP.ProductsPrice.get(i).getText();
			double amountvalue = getAmount(amount);
			sumOfProducts = sumOfProducts + amountvalue;
		}
		/*
		//Simple way
		String amount1 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(0).getText();
		double amount1value = getAmount(amount1);
		
		String amount2 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(1).getText();
		double amount2value = getAmount(amount2);
		
		sumOfProducts = amount1value + amount2value;
		*/
		
		System.out.println("sum Of Products is: "+sumOfProducts);
		
		//Get the totalAmount to be paid.
		//String totalAmount = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
		String totalAmount = CP.TotalAmount.getText();
		totalAmount = totalAmount.substring(1);
		double totalAmountValue = Double.parseDouble(totalAmount);
		System.out.println("Total amount is: "+totalAmountValue);
		
		Assert.assertEquals(sumOfProducts, totalAmountValue);
		
		//On cart page, click on checkbox & term&conditions using gestures
		
		//AndroidElement chkbox = driver.findElementByClassName("android.widget.CheckBox");
		AndroidElement chkbox = CP.ChkBox;
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(chkbox))).perform();
		
		//AndroidElement terms = driver.findElementById("com.androidsample.generalstore:id/termsButton");
		AndroidElement terms = CP.termsButton;
		t.longPress(longPressOptions().withElement(element(terms)).withDuration(ofSeconds(1))).release().perform();
		//Assert.assertEquals(driver.findElementById("com.androidsample.generalstore:id/alertTitle").isDisplayed(), true);
		Assert.assertEquals(CP.alertTitle.isDisplayed(), true);		
		
		//if(driver.findElementById("com.androidsample.generalstore:id/alertTitle").isDisplayed()) 
		if(CP.alertTitle.isDisplayed()) 
		{
			//driver.findElementById("android:id/button1").click();		
			CP.CloseBtn.click();
		}
		
		//driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();
		CP.ProceedBtn.click();
		Thread.sleep(7000);
		
		//Handle Hybrid app having web context
		
		Set<String> contexts = driver.getContextHandles();
		
		for(String contextName : contexts) {
			System.out.println(contextName);
		}
		
		driver.context((String) contexts.toArray()[1]);
		
		System.out.println("Current context is : "+driver.getContext());
		
		//driver.findElementById("com.android.chrome:id/search_box_text").sendKeys("Hello");
		//driver.findElementById("com.android.chrome:id/search_box_text").sendKeys(Keys.ENTER);
		
		//Click on back button
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context((String) contexts.toArray()[0]);
		System.out.println("Current context is : "+driver.getContext());
		
		//Failing the test deliberately
		//Assert.assertEquals(false, true);
		
		//Close the emulator
		String[] args = new String[] {"/bin/bash", "-c", "/Users/pradeep/Library/Android/sdk/platform-tools/adb -s emulator-5554 emu kill", "with", "args"};
		Process proc = new ProcessBuilder(args).start();
		
		Thread.sleep(2000);
		
		//Stopping the Appium server
		AppiumSeverUtilities.stopAppiumServer();

	}

	
	
	//This method will remove the $ from Strings like "$120.0" and convert to double.
	public static double getAmount(String value) {		
		value = value.substring(1); 
		double amountvalue = Double.parseDouble(value);
		return amountvalue;
		
	}
}
