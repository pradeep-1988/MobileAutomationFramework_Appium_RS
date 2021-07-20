package Utilities;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import Reusable.baseAndroid;

public class Listeners implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {

		//Take the screnshot of screen
		try {
			baseAndroid.getScreenshot(result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
