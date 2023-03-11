package com.alcadrial.zengame.game;

import java.util.Map;
import java.util.function.Consumer;

import org.openzen.zencode.java.ZenCodeType.Caster;
import org.openzen.zencode.java.ZenCodeType.Getter;
import org.openzen.zencode.java.ZenCodeType.Method;

import com.alcadra.threads.TimeThread;
import com.alcadrial.zengame.ZenClass;
import com.alcadrial.zengame.script.keyboard.ZenKeyboardListener;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyListener;

@ZenClass
public abstract class Game {
	
	private int id;
	private String name;
	private GlobalKeyboardHook keyboardHook;
	private Map<ZenKeyboardListener, GlobalKeyListener> listenerMap;
	private int tps;
	private Runnable onStartAction;
	private Consumer<Float> onLoopAction;
	private Runnable onPauseAction;
	private Runnable onResumeAction;
	private Runnable onTerminateAction;
	protected TimeThread executionThread;
	
	public Game(int id, GameBuilder<? extends Game> builder)
	{
		this.id = id;
		name = builder.getName();
		listenerMap = builder.getListenerMap();
		tps = builder.getTps();
		onStartAction = builder.getOnStartAction();
		onLoopAction = builder.getOnLoopAction();
		onPauseAction = builder.getOnPauseAction();
		onResumeAction = builder.getOnResumeAction();
		onTerminateAction = builder.getOnTerminateAction();
	}
	
	public void start()
	{
		executionThread = new TimeThread(name, tps, this::startSequence, this::loopPredicate, this::loopSequence, this::terminateSequence) {
			
			boolean off = false;
			
			@Override
			public void interrupt()
			{
				super.interrupt();
				off = true;
			}
			
			@Override
			public boolean isInterrupted()
			{
				return super.isInterrupted() || off;
			}
		};
		executionThread.start();
	}
	
	@Method
	public void pause()
	{
		onPauseAction.run();
	}
	
	@Method
	public void resume()
	{
		onResumeAction.run();
	}
	
	@Method
	public void terminate()
	{
		executionThread.interrupt();
	}
	
	protected void startSequence()
	{
		keyboardHook = new GlobalKeyboardHook(true);
		for (GlobalKeyListener listener : listenerMap.values()) keyboardHook.addKeyListener(listener);
		onStartAction.run();
	}
	
	protected boolean loopPredicate(TimeThread thread)
	{
		return !executionThread.isInterrupted();
	}
	
	protected void loopSequence(float partialSecond)
	{
		onLoopAction.accept(partialSecond);
	}
	
	protected void terminateSequence()
	{
		onTerminateAction.run();
		keyboardHook.shutdownHook();
	}
	
	@Method
	public boolean isKeyHeldDown(int virtualKeyCode)
	{
		return keyboardHook.isKeyHeldDown(virtualKeyCode);
	}
	
	@Method
	public boolean areKeysHeldDown(int... virtualKeyCodes)
	{
		return keyboardHook.areKeysHeldDown(virtualKeyCodes);
	}
	
	@Getter("id")
	public int getId()
	{
		return id;
	}
	
	@Getter("name")
	public String getName()
	{
		return name;
	}
	
	@Getter("tps")
	public int getTps()
	{
		return tps;
	}
	
	@Caster
	@Override
	public String toString()
	{
		return "Game{id=" + id + ", name=" + name + "}";
	}
}
