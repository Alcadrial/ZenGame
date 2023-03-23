package com.alcadrial.zengame.game;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyListener;

public class KeyboardManager {
	
	private static final GlobalKeyboardHook KEYBOARD_HOOK = new GlobalKeyboardHook(true);
	
	public static void addKeyListener(GlobalKeyListener listener)
	{
		KEYBOARD_HOOK.addKeyListener(listener);
	}
	
	public static void removeKeyListener(GlobalKeyListener listener)
	{
		KEYBOARD_HOOK.removeKeyListener(listener);
	}
	
	public static boolean isKeyHeldDown(int virtualKeyCode)
	{
		return KEYBOARD_HOOK.isKeyHeldDown(virtualKeyCode);
	}
	
	public static boolean areKeysHeldDown(int... virtualKeyCodes)
	{
		return KEYBOARD_HOOK.areKeysHeldDown(virtualKeyCodes);
	}
	
	public static void shutdownHook()
	{
		KEYBOARD_HOOK.shutdownHook();
	}
}
