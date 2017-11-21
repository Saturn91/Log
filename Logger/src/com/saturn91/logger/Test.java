package com.saturn91.logger;

public class Test {

	public static void main(String[] args) {
		Log.setDebugMode(2);
		
		for(int i = 0; i < 10; i++) {
			Log.printLn("text"+i, Test.class.getName(), i);
		}
		
		System.out.println("----test recording: ----");
		System.out.println(Log.getLastMessages());
		System.out.println("----/test recording: ----");
		System.out.println("----test recording: ----");
		System.out.println(Log.getLastMessages());
		System.out.println("----/test recording: ----");
		
		String text = Log.getDebugStrings().toString();
		System.out.println("File: \n" + text);
		
		Log.printLogFile("test.txt");
		
	}
}
