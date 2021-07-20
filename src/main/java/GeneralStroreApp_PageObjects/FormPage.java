package GeneralStroreApp_PageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	
	public FormPage(AndroidDriver<AndroidElement> driver) 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	//driver.findElementById("android:id/text1")
	@AndroidFindBy(id="android:id/text1")
	public AndroidElement CountryField;
	
	//driver.findElementByXPath("//*[@text='Australia']")
	@AndroidFindBy(xpath="//*[@text='Australia']")
	public AndroidElement CountryAustralia;
	
	
	@AndroidFindBy(className="android.widget.EditText")
	public AndroidElement NameField;
	
	@AndroidFindBy(xpath="//*[@text='Female']")
	public AndroidElement FemaleOption;
	
	@AndroidFindBy(className="android.widget.Button")
	public AndroidElement ShopButton;
	

}
