package GeneralStroreApp_PageObjects;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckOutPage {
	
	public CheckOutPage(AndroidDriver<AndroidElement> driver) 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	//driver.findElementsById("com.androidsample.generalstore:id/productPrice")
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public List<AndroidElement> ProductsPrice;
	
	//driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl")
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public AndroidElement TotalAmount;
	
	//driver.findElementByClassName("android.widget.CheckBox")
	@AndroidFindBy(className="android.widget.CheckBox")
	public AndroidElement ChkBox;
	
	//driver.findElementById("com.androidsample.generalstore:id/termsButton");
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	public AndroidElement termsButton;
	
	//driver.findElementById("com.androidsample.generalstore:id/alertTitle")
	@AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle")
	public AndroidElement alertTitle;
	
	//driver.findElementById("android:id/button1")
	@AndroidFindBy(id="android:id/button1")
	public AndroidElement CloseBtn;
	
	//driver.findElementById("com.androidsample.generalstore:id/btnProceed")
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	public AndroidElement ProceedBtn;

}
