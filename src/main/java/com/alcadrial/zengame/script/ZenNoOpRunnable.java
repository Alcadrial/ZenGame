package com.alcadrial.zengame.script;

import org.openzen.zencode.java.ZenCodeType.Constructor;
import org.openzen.zencode.java.ZenCodeType.Name;

import com.alcadrial.zengame.ZenClass;

@ZenClass
@Name("NoOpRunnable")
public class ZenNoOpRunnable implements ZenRunnable {
	
	@Constructor
	public ZenNoOpRunnable()
	{}
	
	@Override
	public void run()
	{}
	
}
