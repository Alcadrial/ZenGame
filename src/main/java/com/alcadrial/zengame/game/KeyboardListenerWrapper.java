package com.alcadrial.zengame.game;

import com.alcadrial.zengame.script.keyboard.KeyContext;
import com.alcadrial.zengame.script.keyboard.KeyPressType;
import com.alcadrial.zengame.script.keyboard.ZenKeyboardListener;

import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;

class KeyboardListenerWrapper implements GlobalKeyListener {
	
	private ZenKeyboardListener listener;
	
	public KeyboardListenerWrapper(ZenKeyboardListener listener)
	{
		this.listener = listener;
	}
	
	@Override
	public void keyPressed(GlobalKeyEvent event)
	{
		try
		{
			listener.handle(new KeyContext(event, KeyPressType.PRESS));
		}
		catch (Throwable e)
		{
			e.printStackTrace(System.out);
		}
	}
	
	@Override
	public void keyReleased(GlobalKeyEvent event)
	{
		listener.handle(new KeyContext(event, KeyPressType.RELEASE));
	}
}