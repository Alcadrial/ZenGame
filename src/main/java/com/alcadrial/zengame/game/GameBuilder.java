package com.alcadrial.zengame.game;

import java.util.function.Consumer;

import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Setter;

import com.alcadrial.zengame.ZenClass;

@ZenClass
public abstract class GameBuilder<G extends Game> {
	
	private String name;
	private int tps;
	private Runnable onStartAction;
	private Consumer<Float> onLoopAction;
	private Runnable onPauseAction;
	private Runnable onResumeAction;
	private Runnable onTerminateAction;
	
	public GameBuilder(String name)
	{
		this.name = name;
		tps = 20;
	}
	
	@Setter("tps")
	public void setTps(int tps)
	{
		if (tps <= 0) this.tps = 1;
		else this.tps = tps;
	}
	
	@Setter
	public void onStart(Runnable action)
	{
		onStartAction = action;
	}
	
	@Setter
	public void onLoop(Consumer<Float> action)
	{
		onLoopAction = action;
	}
	
	@Setter
	public void onPause(Runnable action)
	{
		onPauseAction = action;
	}
	
	@Setter
	public void onResume(Runnable action)
	{
		onResumeAction = action;
	}
	
	@Setter
	public void onTerminate(Runnable action)
	{
		onTerminateAction = action;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getTps()
	{
		return tps;
	}
	
	public Runnable getOnStartAction()
	{
		return onStartAction;
	}
	
	public Consumer<Float> getOnLoopAction()
	{
		return onLoopAction;
	}
	
	public Runnable getOnPauseAction()
	{
		return onPauseAction;
	}
	
	public Runnable getOnResumeAction()
	{
		return onResumeAction;
	}
	
	public Runnable getOnTerminateAction()
	{
		return onTerminateAction;
	}
	
	@Method
	public abstract G build(int id);
}
