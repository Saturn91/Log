package com.saturn91.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Debug mode output:
 * -1: nothing
 * 0: Normal Runtime Status
 * 1: Errors that are critical and can stop the Program from running
 * 2: "Errors" that can happen at runtime
 * 3: Status informations Detail 0
 * 4. Status informations Detail 1
 * 5. Status informations Detail 2
 * @author M.Geissbberger
 */
public abstract class Log {
	private static int debugMode;
	private static int allClassesDebugMode = -1;		//-1 means use normal debug mode
	private static int debugModelogFile = 10;
	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY-hh:mm:ss");
	private final static SimpleDateFormat dayMonthYearFormat = new SimpleDateFormat("dd.MM.YYYY");
	private final static String info = " [INFO]";
	private final static String error ="[ERROR]";

	private static StringBuilder sb = new StringBuilder();
	private static StringBuilder lastMessages = new StringBuilder();

	private static boolean printOnlyExact_DebugMode = false;

	/**
	 * Print the msg message on the debugMod Layer
	 * @param msg	Status Message
	 * @param debugMode
	 */
	public static void printLn(String msg, String className, int _debugMode){
		String msgLine = "[" + getDetailedDate() + "]: " + info + " " + className + ": " + msg;
		if(debugMode <= debugModelogFile) {
			sb.append(_debugMode + "| "+msgLine + "\n");
		}
		if(allClassesDebugMode == -1){
			if(printOnlyExact_DebugMode){
				if(debugMode == _debugMode){
					System.out.println(_debugMode + "| "+ msgLine);
					lastMessages.append(_debugMode + "| "+msgLine + "\n");
				}
			}else{
				if(debugMode >= _debugMode){
					System.out.println(_debugMode + "| "+msgLine);
					lastMessages.append(_debugMode + "| "+msgLine + "\n");
				}
			}
		}else{
			if(allClassesDebugMode >= _debugMode){
				System.out.println(_debugMode + "| "+msgLine);
				lastMessages.append(_debugMode + "| "+msgLine + "\n");
			}
		}
	}

	/**
	 * Print the msg error message on the debugMode Layer
	 * @param msg	Status Message
	 * @param debugMode
	 */
	public static void printErrorLn(String msg, String className, int _debugMode){
		String msgLine = "[" + getDetailedDate() + "]: " + error + " " + className + ": " + msg;
		if(debugMode <= debugModelogFile) {
			sb.append(_debugMode + "| "+msgLine + "\n");
		}
		if(allClassesDebugMode == -1){
			if(printOnlyExact_DebugMode){
				if(debugMode == _debugMode){
					System.err.println(_debugMode + "| "+msgLine);
					lastMessages.append(_debugMode + "| "+msgLine + "\n");
				}
			}else{
				if(debugMode >= _debugMode){
					System.err.println(_debugMode + "| "+msgLine);
					lastMessages.append(_debugMode + "| "+msgLine + "\n");
				}
			}
		}else{
			if(allClassesDebugMode >= _debugMode){
				System.err.println(_debugMode + "| "+msgLine);
				lastMessages.append(_debugMode + "| "+msgLine + "\n");
			}
		}
	}

	/**
	 * Change the debug Mod so that other deeper or less deep msg gets trough
	 * @param debugMode
	 */
	public static void setDebugMode(int _debugMode){
		debugMode = _debugMode;
	}

	/**
	 * If active, only the one set Layer will be printed
	 * @param printOnlyExact_Debugmode
	 */
	public void setOnlyThisLayer(boolean _printOnlyExact_Debugmode){
		printOnlyExact_DebugMode = _printOnlyExact_Debugmode;
	}

	/**
	 * This Method provides every text output with the last printed msg could be used for a log File
	 * @return
	 */
	public static StringBuilder getDebugStrings(){
		StringBuilder sb2 = new StringBuilder();
		sb2.append(sb.toString());
		return sb2;
	}

	/**
	 * This Method changes the debug mode for all classes
	 * @param debugmode
	 */
	public static void setDebugModeAllClasses(int debugmode){
		allClassesDebugMode = debugmode;
	}

	public static String getDetailedDate(){
		Date date = new Date(System.currentTimeMillis());
		return dateFormat.format(date).toString();
	}
	
	public static String getDayMonthYear() {
		Date date = new Date(System.currentTimeMillis());
		return dayMonthYearFormat.format(date);
	}

	public static int getDebugMode(){
		if(allClassesDebugMode == -1){
			return debugMode;
		}else{
			return allClassesDebugMode;
		}
	}
	
	public static void setLogFileDebugModus(int debugmodus) {
		debugModelogFile = debugmodus;
	}
	
	public static String getLastMessages() {
		String lastmsg = lastMessages.toString();
		lastMessages.setLength(0);
		return lastmsg;
	}
	
	/**
	 * Add a direction i.e. "../LogFiles/" to the path 
	 * printLogFile(LogFiles/, Log.txt); creates a Date_Log.txt in the given directory (from program)
	 * @param dir
	 * @param path
	 */
	public static void printLogFile(String dir, String path) {
		String dateString = getDetailedDate().replace(".", "_").replace("-", "_").replace(":", "_");
		try(  PrintWriter out = new PrintWriter(dir + dateString+"_"+path)  ){
		    out.println(getDebugStrings().toString());
		} catch (FileNotFoundException e) {
			Log.printErrorLn("not able to save File!" + dir + dateString+"_"+path, Log.class.getName(), 1);
			e.printStackTrace();
		}
	}
	
	/**
	 * printLogFile(Log.txt); creates a Date_Log.txt in the folder where the program runs
	 * @param path
	 */
	public static void printLogFile(String path) {
		String dateString = getDetailedDate().replace(".", "_").replace("-", "_").replace(":", "_");
		try(  PrintWriter out = new PrintWriter(dateString+"_"+path)  ){
		    out.println(getDebugStrings().toString());
		} catch (FileNotFoundException e) {
			Log.printErrorLn("not able to save File!" + dateString+"_"+path, Log.class.getName(), 1);
			e.printStackTrace();
		}
	}
}