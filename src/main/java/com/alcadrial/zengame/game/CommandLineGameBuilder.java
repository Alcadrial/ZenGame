package com.alcadrial.zengame.game;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Setter;

import com.alcadrial.zengame.ZenClass;
import com.alcadrial.zengame.script.keyboard.ZenKeyboardListener;

import lc.kra.system.keyboard.event.GlobalKeyListener;

@ZenClass
public class CommandLineGameBuilder extends GameBuilder<CommandLineGame> {
	
	private Map<ZenKeyboardListener, GlobalKeyListener> listenerMap;
	private InputStream input;
	private OutputStream output;
	
	public CommandLineGameBuilder(String name)
	{
		super(name);
		input = System.in;
		output = System.out;
	}
	
	@Method
	public void addKeyListener(ZenKeyboardListener listener)
	{
		if (listenerMap == null) listenerMap = new HashMap<>();
		KeyboardListenerWrapper wrapper = new KeyboardListenerWrapper(listener);
		listenerMap.put(listener, wrapper);
	}
	
	@Method
	public void removeKeyListener(ZenKeyboardListener listener)
	{
		if (listenerMap != null) listenerMap.remove(listener);
	}
	
	@Setter("input")
	public void setInput(InputStream input)
	{
		this.input = input;
	}
	
	@Setter("output")
	public void setOutput(OutputStream output)
	{
		this.output = output;
	}
	
	public Map<ZenKeyboardListener, GlobalKeyListener> getListenerMap()
	{
		return listenerMap;
	}
	
	public InputStream getInput()
	{
		return input;
	}
	
	public OutputStream getOutput()
	{
		return output;
	}
	
	@Override
	public CommandLineGame build(int id)
	{
		return new CommandLineGame(id, this);
	}
}
