package com.alcadrial.zengame.script;

import java.io.PrintStream;
import java.util.Scanner;

import org.openzen.zencode.java.ZenCodeGlobals.Global;

public class GlobalZenUtils {
	
	public static final String ZEN_PACKAGE = "com.alcadrial.zengame";
	private static final Scanner IN = new Scanner(System.in);
	private static final PrintStream OUT = new PrintStream(System.out);
	
	@Global
	public static void println(Object object)
	{
		OUT.println(object);
	}
	
	@Global
	public static void println(String message)
	{
		OUT.println(message);
	}
	
	@Global
	public static String readString(String message)
	{
		println(message);
		return readString();
	}
	
	@Global
	public static String readString()
	{
		return IN.next();
	}
}
