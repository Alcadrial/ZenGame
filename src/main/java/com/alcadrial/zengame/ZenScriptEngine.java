package com.alcadrial.zengame;

import org.openzen.zencode.java.ScriptingEngine;

public class ZenScriptEngine {
	
	private ScriptingEngine engine;
	
	public ZenScriptEngine()
	{
		engine = new ScriptingEngine();
	}
	
	public void run()
	{
		engine.run();
	}
}
