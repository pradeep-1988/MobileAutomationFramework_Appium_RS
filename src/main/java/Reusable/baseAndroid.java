package Reusable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;


public class baseAndroid {
	
	public static AndroidDriver<AndroidElement> driver;

	public static AndroidDriver<AndroidElement> Capabilities(String testApp) throws IOException, InterruptedException {

		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/resources/global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		File appDir = new File("src/resources");
		File app = new File(appDir, (String) prop.get(testApp));
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		//String device = (String) prop.get("deviceName");
		
		//Get the deviceName at run-time using Maven command
		String device = System.getProperty("deviceName");
		
		if(device.contains("Emulator")) {
			String[] args = new String[] {"/bin/bash", "-c", "/Users/pradeep/Library/Android/sdk/emulator/emulator -avd "+device, "with", "args"};
			Process proc = new ProcessBuilder(args).start();
			Thread.sleep(10000);
		}

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,10);
		
		URL url = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver<>(url,cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}
	
	public static void getScreenshot(String name) throws IOException {
		File srcFile = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/FailureScreenShots/"+name+".png"));
	}

}
