package com.alcadrial.zengame.game;

import com.alcadrial.zengame.script.keyboard.KeyContext;
import com.alcadrial.zengame.script.keyboard.KeyPressType;
import com.alcadrial.zengame.script.keyboard.ZenKeyboardListener;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

class KeyboardListenerWrapper implements NativeKeyListener {
	
	private ZenKeyboardListener listener;
	
	public KeyboardListenerWrapper(ZenKeyboardListener listener)
	{
		this.listener = listener;
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e)
	{
		listener.handle(new KeyContext(e, KeyPressType.PRESS));
	}
	
	@Override
	public void nativeKeyReleased(NativeKeyEvent e)
	{
		listener.handle(new KeyContext(e, KeyPressType.RELEASE));
	}
	
	@Override
	public void nativeKeyTyped(NativeKeyEvent e)
	{
		listener.handle(new KeyContext(e, KeyPressType.TYPE));
	}
}