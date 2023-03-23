package com.alcadrial.zengame.script.mouse;

import java.awt.event.MouseEvent;

import org.openzen.zencode.java.ZenCodeType.Field;
import org.openzen.zencode.java.ZenCodeType.Getter;
import org.openzen.zencode.java.ZenCodeType.Name;

import com.alcadrial.zengame.ZenClass;

@ZenClass
@Name(MousePackage.PACKAGE + "ButtonContext")
public class ButtonContext {
	
	@Field
	public static final int BUTTON1 = MouseEvent.BUTTON1,
			BUTTON2 = MouseEvent.BUTTON2,
			BUTTON3 = MouseEvent.BUTTON3,
			BUTTON4 = 4,
			BUTTON5 = 5,
			BUTTON6 = 6,
			BUTTON7 = 7;
	
	@Field
	public static final int CLICKED = MouseEvent.MOUSE_CLICKED,
			PRESSED = MouseEvent.MOUSE_PRESSED,
			RELEASED = MouseEvent.MOUSE_RELEASED,
			MOVED = MouseEvent.MOUSE_MOVED,
			ENTERED = MouseEvent.MOUSE_ENTERED,
			EXITED = MouseEvent.MOUSE_EXITED,
			DRAGGED = MouseEvent.MOUSE_DRAGGED,
			WHEEL = MouseEvent.MOUSE_WHEEL;
	
	@Field
	public static final int SHIFT = MouseEvent.SHIFT_DOWN_MASK,
			CONTROL = MouseEvent.CTRL_DOWN_MASK,
			META = MouseEvent.META_DOWN_MASK,
			ALT = MouseEvent.ALT_DOWN_MASK,
			ALT_GRAPH = MouseEvent.ALT_GRAPH_DOWN_MASK;
	
	private int type;
	private int button;
	private int clicks;
	private int modifiers;
	private int x;
	private int y;
	
	public ButtonContext(MouseEvent event)
	{
		this(event.getID(), event.getButton(), event.getClickCount(), event.getModifiersEx(), event.getX(), event.getY());
	}
	
	public ButtonContext(int type, int button, int clicks, int modifiers, int x, int y)
	{
		this.type = type;
		this.button = button;
		this.clicks = clicks;
		this.modifiers = modifiers;
		this.x = x;
		this.y = y;
	}
	
	@Getter("type")
	public int getType()
	{
		return type;
	}
	
	@Getter("button")
	public int getButton()
	{
		return button;
	}
	
	@Getter("clicks")
	public int getClicks()
	{
		return clicks;
	}
	
	@Getter("modifiers")
	public int getModifiers()
	{
		return modifiers;
	}
	
	@Getter("x")
	public int getX()
	{
		return x;
	}
	
	@Getter("y")
	public int getY()
	{
		return y;
	}
}
