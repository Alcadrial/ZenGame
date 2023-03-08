package com.alcadrial.zengame.script.keyboard;

import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Name;

import com.alcadrial.zengame.ZenClass;

@FunctionalInterface
@ZenClass
@Name(KeyboardPackage.PACKAGE + "KeyboardListener")
public interface ZenKeyboardListener {
	
	@Method
	void handle(KeyContext context);
}
