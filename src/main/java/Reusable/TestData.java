package Reusable;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="InputData")
	public Object[][] getDataForEditField() {
		//Two sets of Data i.e. "MobileAutomation" & "Mobile!@#$Automation"
		Object[][] obj = new Object[][] {{"MobileAutomation"},{"Mobile!@#$Automation"}};
		return obj;
	}

}
