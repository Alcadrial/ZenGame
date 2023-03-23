package com.alcadrial.zengame.script;

import org.openzen.zencode.java.ZenCodeType.Constructor;
import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Name;

import com.alcadrial.zengame.ZenClass;

@ZenClass
@Name(".Thread")
public class ZenThread extends Thread {
	
	@Constructor
	public ZenThread(ZenRunnable target)
	{
		super(target);
	}
	
	@Constructor
	public ZenThread(String group, ZenRunnable target)
	{
		super(new ThreadGroup(group), target);
	}
	
	@Constructor
	public ZenThread(ZenRunnable target, String name)
	{
		super(target, name);
	}
	
	@Constructor
	public ZenThread(String group, ZenRunnable target, String name)
	{
		super(new ThreadGroup(group), target, name);
	}
	
	@Method
	@Override
	public void run()
	{
		super.run();
	}
}
