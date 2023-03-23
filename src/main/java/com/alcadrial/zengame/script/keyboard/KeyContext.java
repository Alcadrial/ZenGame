package com.alcadrial.zengame.script.keyboard;

import org.openzen.zencode.java.ZenCodeType.Field;
import org.openzen.zencode.java.ZenCodeType.Getter;
import org.openzen.zencode.java.ZenCodeType.Name;

import com.alcadrial.zengame.ZenClass;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

@ZenClass
@Name(KeyboardPackage.PACKAGE + "KeyContext")
public class KeyContext {
	
	@Field
	public static final int KEY_ESCAPE = 0x0001,
			KEY_F1 = 0x003B,
			KEY_F2 = 0x003C,
			KEY_F3 = 0x003D,
			KEY_F4 = 0x003E,
			KEY_F5 = 0x003F,
			KEY_F6 = 0x0040,
			KEY_F7 = 0x0041,
			KEY_F8 = 0x0042,
			KEY_F9 = 0x0043,
			KEY_F10 = 0x0044,
			KEY_F11 = 0x0057,
			KEY_F12 = 0x0058,
			KEY_F13 = 0x005B,
			KEY_F14 = 0x005C,
			KEY_F15 = 0x005D,
			KEY_F16 = 0x0063,
			KEY_F17 = 0x0064,
			KEY_F18 = 0x0065,
			KEY_F19 = 0x0066,
			KEY_F20 = 0x0067,
			KEY_F21 = 0x0068,
			KEY_F22 = 0x0069,
			KEY_F23 = 0x006A,
			KEY_F24 = 0x006B,
			KEY_BACKQUOTE = 0x0029,
			KEY_1 = 0x0002,
			KEY_2 = 0x0003,
			KEY_3 = 0x0004,
			KEY_4 = 0x0005,
			KEY_5 = 0x0006,
			KEY_6 = 0x0007,
			KEY_7 = 0x0008,
			KEY_8 = 0x0009,
			KEY_9 = 0x000A,
			KEY_0 = 0x000B,
			KEY_MINUS = 0x000C, // '-'
			KEY_EQUALS = 0x000D, // '='
			KEY_BACKSPACE = 0x000E,
			KEY_TAB = 0x000F,
			KEY_CAPS_LOCK = 0x003A,
			KEY_A = 0x001E,
			KEY_B = 0x0030,
			KEY_C = 0x002E,
			KEY_D = 0x0020,
			KEY_E = 0x0012,
			KEY_F = 0x0021,
			KEY_G = 0x0022,
			KEY_H = 0x0023,
			KEY_I = 0x0017,
			KEY_J = 0x0024,
			KEY_K = 0x0025,
			KEY_L = 0x0026,
			KEY_M = 0x0032,
			KEY_N = 0x0031,
			KEY_O = 0x0018,
			KEY_P = 0x0019,
			KEY_Q = 0x0010,
			KEY_R = 0x0013,
			KEY_S = 0x001F,
			KEY_T = 0x0014,
			KEY_U = 0x0016,
			KEY_V = 0x002F,
			KEY_W = 0x0011,
			KEY_X = 0x002D,
			KEY_Y = 0x0015,
			KEY_Z = 0x002C,
			KEY_OPEN_BRACKET = 0x001A, // '['
			KEY_CLOSE_BRACKET = 0x001B, // ']'
			KEY_BACK_SLASH = 0x002B, // '\'
			KEY_SEMICOLON = 0x0027, // ';'
			KEY_QUOTE = 0x0028,
			KEY_ENTER = 0x001C,
			KEY_COMMA = 0x0033, // ','
			KEY_PERIOD = 0x0034, // '.'
			KEY_SLASH = 0x0035, // '/'
			KEY_SPACE = 0x0039,
			KEY_PRINTSCREEN = 0x0E37,
			KEY_SCROLL_LOCK = 0x0046,
			KEY_PAUSE = 0x0E45,
			KEY_INSERT = 0x0E52,
			KEY_DELETE = 0x0E53,
			KEY_HOME = 0x0E47,
			KEY_END = 0x0E4F,
			KEY_PAGE_UP = 0x0E49,
			KEY_PAGE_DOWN = 0x0E51,
			KEY_UP = 0xE048,
			KEY_LEFT = 0xE04B,
			KEY_CLEAR = 0xE04C,
			KEY_RIGHT = 0xE04D,
			KEY_DOWN = 0xE050,
			KEY_NUM_LOCK = 0x0045,
			KEY_SEPARATOR = 0x0053,
			KEY_SHIFT = 0x002A,
			KEY_CONTROL = 0x001D,
			KEY_ALT = 0x0038, // Option or Alt Key
			KEY_META = 0x0E5B, // Windows or Command Key
			KEY_CONTEXT_MENU = 0x0E5D,
			KEY_POWER = 0xE05E,
			KEY_SLEEP = 0xE05F,
			KEY_WAKE = 0xE063,
			KEY_MEDIA_PLAY = 0xE022,
			KEY_MEDIA_STOP = 0xE024,
			KEY_MEDIA_PREVIOUS = 0xE010,
			KEY_MEDIA_NEXT = 0xE019,
			KEY_MEDIA_SELECT = 0xE06D,
			KEY_MEDIA_EJECT = 0xE02C,
			KEY_VOLUME_MUTE = 0xE020,
			KEY_VOLUME_UP = 0xE030,
			KEY_VOLUME_DOWN = 0xE02E,
			KEY_APP_MAIL = 0xE06C,
			KEY_APP_CALCULATOR = 0xE021,
			KEY_APP_MUSIC = 0xE03C,
			KEY_APP_PICTURES = 0xE064,
			KEY_BROWSER_SEARCH = 0xE065,
			KEY_BROWSER_HOME = 0xE032,
			KEY_BROWSER_BACK = 0xE06A,
			KEY_BROWSER_FORWARD = 0xE069,
			KEY_BROWSER_STOP = 0xE068,
			KEY_BROWSER_REFRESH = 0xE067,
			KEY_BROWSER_FAVORITES = 0xE066,
			KEY_KATAKANA = 0x0070,
			KEY_UNDERSCORE = 0x0073,
			KEY_FURIGANA = 0x0077,
			KEY_KANJI = 0x0079,
			KEY_HIRAGANA = 0x007B,
			KEY_YEN = 0x007D,
			KEY_SUN_HELP = 0xFF75,
			KEY_SUN_STOP = 0xFF78,
			KEY_SUN_PROPS = 0xFF76,
			KEY_SUN_FRONT = 0xFF77,
			KEY_SUN_OPEN = 0xFF74,
			KEY_SUN_FIND = 0xFF7E,
			KEY_SUN_AGAIN = 0xFF79,
			KEY_SUN_UNDO = 0xFF7A,
			KEY_SUN_COPY = 0xFF7C,
			KEY_SUN_INSERT = 0xFF7D,
			KEY_SUN_CUT = 0xFF7B,
			KEY_UNDEFINED = 0x0000;
	
	private KeyPressType type;
	private int keyCode;
	private char keyChar;
	private int modifiers;
	
	public KeyContext(NativeKeyEvent event, KeyPressType type)
	{
		this(type, event.getKeyCode(), event.getKeyChar(), event.getModifiers());
	}
	
	public KeyContext(KeyPressType type, int keyCode, char keyChar, int modifiers)
	{
		this.type = type;
		this.keyCode = keyCode;
		this.keyChar = keyChar;
		this.modifiers = modifiers;
	}
	
	@Getter("type")
	public KeyPressType type()
	{
		return type;
	}
	
	@Getter("pressed")
	public boolean isPress()
	{
		return type == KeyPressType.PRESS;
	}
	
	@Getter("release")
	public boolean isRelease()
	{
		return type == KeyPressType.RELEASE;
	}
	
	@Getter("keyCode")
	public int getKeyCode()
	{
		return keyCode;
	}
	
	@Getter("keyChar")
	public char getKeyChar()
	{
		return keyChar;
	}
	
	@Getter("modifiers")
	public int getModifiers()
	{
		return modifiers;
	}
}
