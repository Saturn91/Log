package com.saturn91.logger;

public class Test {

	public static void main(String[] args) {
		Log.setDebugMode(10);
		for(int i = 0; i < 10; i++) {
			Log.printLn("text"+i, Test.class.getName(), 1);
		}
		
		System.out.println("----test recording: ----");
		System.out.println(Log.getLastMessages());
		System.out.println("----/test recording: ----");
		System.out.println("----test recording: ----");
		System.out.println(Log.getLastMessages());
		System.out.print("----/test recording: ----");
		
		String text = Log.getDebugStrings().toString();
		System.out.println("File: \n" + text);
		
	}
}
