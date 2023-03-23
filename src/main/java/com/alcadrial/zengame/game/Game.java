package com.alcadrial.zengame.game;

import java.util.Objects;
import java.util.function.Consumer;

import org.openzen.zencode.java.ZenCodeType.Caster;
import org.openzen.zencode.java.ZenCodeType.Getter;
import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Setter;

import com.alcadra.threads.TimeThread;
import com.alcadrial.zengame.ZenClass;

@ZenClass
public abstract class Game {
	
	private String name;
	private int id;
	private int tps;
	private Runnable onStartAction;
	private Consumer<Float> onLoopAction;
	private Runnable onPauseAction;
	private Runnable onResumeAction;
	private Runnable onTerminateAction;
	protected TimeThread executionThread;
	
	public Game(String name, int id)
	{
		this.name = Objects.requireNonNull(name);
		this.id = id;
		tps = 60;
		onStartAction = () -> {};
		onLoopAction = p -> {};
		onPauseAction = () -> {};
		onResumeAction = () -> {};
		onTerminateAction = () -> {};
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
	}
	
	@Getter("name")
	public String getName()
	{
		return name;
	}
	
	@Getter("id")
	public int getId()
	{
		return id;
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
	
	@Caster
	@Override
	public String toString()
	{
		return "Game{name=" + name + ", id=" + id + "}";
	}
}