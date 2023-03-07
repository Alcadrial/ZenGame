package com.alcadrial.zengame.script.extra;

import org.openzen.zencode.java.ZenCodeType.Caster;
import org.openzen.zencode.java.ZenCodeType.Name;

import com.alcadrial.zengame.ZenClass;

@ZenClass
@Name("zengame.Action")
public enum ZenAction {
	
	MOVING,
	SLEEPING,
	WORKING;
	
	@Caster
	@Override
	public String toString()
	{
		return super.toString();
	}
}
