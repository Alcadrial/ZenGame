package com.alcadrial.zengame.script.expansion;

import org.openzen.zencode.java.ZenCodeType.Caster;
import org.openzen.zencode.java.ZenCodeType.Expansion;

import com.alcadrial.zengame.ZenClass;

@ZenClass
@Expansion(value = "string")
public class ZenString {
	
	@Caster(implicit = true)
	public static char[] cast(String s)
	{
		return s.toCharArray();
	}
}
