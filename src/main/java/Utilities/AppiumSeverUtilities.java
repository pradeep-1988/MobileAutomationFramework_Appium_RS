package Utilities;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumSeverUtilities {
	
	//To kill any service running on specific port:
	/*
	 * First get the PID using below command
	 * - 'lsof -n -i4TCP:PORT_Number'
	 * PID is the second field. Then, kill that process using below command:
	 * - 'kill -9 PID'
	 */
	
	public static AppiumDriverLocalService service;
	
	public static void startAppiumServer() throws InterruptedException {
			
			int port = 4723;
			
			System.out.println("Starting Appium Server.");
			
			AppiumServiceBuilder builder = new AppiumServiceBuilder();
			builder.usingDriverExecutable(new File("/usr/local/bin/node"));
			builder.usingPort(port);
			builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));
			
			HashMap<String, String> environment = new HashMap<String, String>();
	        environment.put("ANDROID_HOME", "/Users/pradeep/Library/Android/sdk");
	        environment.put("JAVA_HOME", "/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home");
	        builder.withEnvironment(environment);
	        
			builder.withLogFile(new File(System.getProperty("user.dir") + "/Appium.log"));
			//builder.withLogFile(new File("/Users/pradeep/Documents/New_Learning_Workspace/MobileAutomationFramework_Appium_RS/Appium.log"));
			
			
			
			service = AppiumDriverLocalService.buildService(builder);
	        service.start();
	        Thread.sleep(5000);
	        System.out.println("Welcome to Appium!! Server has started Successfully");			
			}
	
	
	public static void stopAppiumServer() {
		service.stop();
		System.out.println("Appium server stopped.");
		
	}
	
	public static boolean checkIfServerIsRunnning(int port) {
		boolean isServerRunning = false;
		System.out.println("Inside checkIfServerIsRunnning function. Port is: "+port);
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();

		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

}
