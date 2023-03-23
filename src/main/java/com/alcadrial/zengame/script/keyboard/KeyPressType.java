package com.alcadrial.zengame.script.keyboard;

import org.openzen.zencode.java.ZenCodeType.Caster;
import org.openzen.zencode.java.ZenCodeType.Name;

import com.alcadrial.zengame.ZenClass;

@ZenClass
@Name(KeyboardPackage.PACKAGE + "KeyPressType")
public enum KeyPressType {
	
	PRESS,
	RELEASE,
	TYPE;
	
	@Caster
	@Override
	public String toString()
	{
		return super.toString();
	}
}
