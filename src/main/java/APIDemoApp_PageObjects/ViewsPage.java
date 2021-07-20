package APIDemoApp_PageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ViewsPage {
	
	public ViewsPage(AndroidDriver<AndroidElement> driver) 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	//driver.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Expandable Lists']")
	public AndroidElement Expandable_Lists;

}
