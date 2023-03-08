package com.alcadrial.zengame.script;

import org.openzen.zencode.java.ZenCodeGlobals.Global;

public class GlobalZenUtils {
	
	public static final String ZEN_PACKAGE = "com.alcadrial.zengame";
	
	@Global
	public static void println(Object object)
	{
		System.out.println(object);
	}
	
	@Global
	public static void println(String message)
	{
		System.out.println(message);
	}
}
