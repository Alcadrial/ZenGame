package com.alcadrial.zengame.game;

import org.openzen.zencode.java.ZenCodeType.Caster;
import org.openzen.zencode.java.ZenCodeType.Getter;
import org.openzen.zencode.java.ZenCodeType.Method;

import com.alcadra.threads.TimeThread;
import com.alcadrial.zengame.FloatConsumer;
import com.alcadrial.zengame.ZenClass;

@ZenClass
public abstract class Game implements AutoCloseable {
	
	private int id;
	private String name;
	private int tps;
	private Runnable onStartAction;
	private FloatConsumer onLoopAction;
	private Runnable onPauseAction;
	private Runnable onResumeAction;
	private Runnable onTerminateAction;
	protected TimeThread executionThread;
	private long startTimestamp;
	private long currentTimestamp;
	private long startNanoTimestamp;
	private long currentNanoTimestamp;
	
	public Game(int id, GameBuilder<? extends Game> builder)
	{
		this.id = id;
		name = builder.getName();
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
	
	public void join()
	{
		try
		{
			executionThread.join();
		}
		catch (InterruptedException e)
		{}
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
		currentTimestamp = startTimestamp = System.currentTimeMillis();
		currentNanoTimestamp = startNanoTimestamp = System.nanoTime();
		onStartAction.run();
	}
	
	protected boolean loopPredicate(TimeThread thread)
	{
		return !executionThread.isInterrupted();
	}
	
	protected void loopSequence(float partialSecond)
	{
		currentTimestamp = System.currentTimeMillis();
		currentNanoTimestamp = System.nanoTime();
		onLoopAction.accept(partialSecond);
	}
	
	protected void terminateSequence()
	{
		onTerminateAction.run();
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
	
	@Getter("startTimestamp")
	public long getStartTimestamp()
	{
		return startTimestamp;
	}
	
	@Getter("currentTimestamp")
	public long getCurrentTimestamp()
	{
		return currentTimestamp;
	}
	
	@Getter("enlapsedTime")
	public long getEnlapsedTime()
	{
		return currentTimestamp - startTimestamp;
	}
	
	@Getter("startNanoTimestamp")
	public long getStartNanoTimestamp()
	{
		return startNanoTimestamp;
	}
	
	@Getter("currentNanoTimestamp")
	public long getCurrentNanoTimestamp()
	{
		return currentNanoTimestamp;
	}
	
	@Getter("nanoEnlapsedTime")
	public long getNanoEnlapsedTime()
	{
		return currentNanoTimestamp - startNanoTimestamp;
	}
	
	@Caster
	@Override
	public String toString()
	{
		return "Game{id=" + id + ", name=" + name + "}";
	}
}
