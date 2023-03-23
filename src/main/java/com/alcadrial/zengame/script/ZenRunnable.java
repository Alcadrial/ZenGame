package com.alcadrial.zengame.script;

import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Name;

import com.alcadrial.zengame.ZenClass;

@ZenClass
@Name("Runnable")
public interface ZenRunnable extends Runnable {
	
	@Method
	@Override
	void run();
}
