package com.saturn91.logger;

public class Test2 {

	public static void main(String[] args) {
		Log.setDebugMode(1);
		Log.setDontWasteDataSpace(true);
		Log.printLn("Start", Test2.class.getSimpleName(), 0);
		for(int i = 0; i < 9000; i++) {
			Log.printLn("Hello Logfile" + i, Test2.class.getSimpleName(), 1);
		}
		Log.printLn("End", Test2.class.getSimpleName(), 0);
		Log.printLogFile("TestLog");
	}

}
