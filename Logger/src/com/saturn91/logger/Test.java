package com.saturn91.logger;

public class Test {

	public static void main(String[] args) {
		Log.setDebugMode(10);
		Log.printLn("text", Test.class.getName(), 1);
		String text = Log.getDebugStrings().toString();
		System.out.println("logFile: " + text);
	}
}
