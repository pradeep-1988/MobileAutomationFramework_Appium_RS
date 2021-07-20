package APIDemoApp_PageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
	
	public HomePage(AndroidDriver<AndroidElement> driver) 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	//driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"))
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Views']")
	public AndroidElement ViewsField;

}
