package com.alcadrial.zengame.script;

import org.openzen.zencode.java.ZenCodeType.Method;

import com.alcadrial.zengame.ZenClass;

@ZenClass
public class ZenPrint {
	
	@Method
	public static void run()
	{
		System.out.println("zen");
	}
}
