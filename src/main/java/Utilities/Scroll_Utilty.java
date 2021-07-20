package Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Scroll_Utilty {
	
	AndroidDriver<AndroidElement> driver;
	
	public Scroll_Utilty(AndroidDriver<AndroidElement>driver) {
		this.driver=driver;
	}
	
	
	public void scrollToText(String Text) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+Text+"\"));");
	}

}
