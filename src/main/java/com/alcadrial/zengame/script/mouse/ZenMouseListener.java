package com.alcadrial.zengame.script.mouse;

import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Name;

import com.alcadrial.zengame.ZenClass;

@FunctionalInterface
@ZenClass
@Name(MousePackage.PACKAGE + "MouseListener")
public interface ZenMouseListener {
	
	@Method
	void handle(ButtonContext context);
}
