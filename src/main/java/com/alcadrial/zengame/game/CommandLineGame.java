package com.alcadrial.zengame.game;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.openzen.zencode.java.ZenCodeType.Method;

import com.alcadra.threads.TimeThread;
import com.alcadrial.zengame.ZenClass;

import nonapi.io.github.classgraph.utils.VersionFinder;
import nonapi.io.github.classgraph.utils.VersionFinder.OperatingSystem;

@ZenClass
public class CommandLineGame extends Game {
	
	@Method
	public static CommandLineGameBuilder create(String name)
	{
		return new CommandLineGameBuilder(name);
	}
	
	private Scanner in;
	private PrintStream out;
	private boolean living;
	private boolean running;
	
	public CommandLineGame(int id, CommandLineGameBuilder builder)
	{
		super(id, builder);
		in = new Scanner(builder.getInput());
		out = new PrintStream(builder.getOutput());
		living = false;
		running = false;
	}
	
	@Override
	public void start()
	{
		if (!living)
		{
			super.start();
		}
	}
	
	@Override
	public void pause()
	{
		if (running)
		{
			super.pause();
			running = false;
		}
	}
	
	@Override
	public void resume()
	{
		if (!running)
		{
			running = true;
			super.resume();
		}
	}
	
	@Override
	public void terminate()
	{
		if (living)
		{
			super.terminate();
		}
	}
	
	@Override
	protected void startSequence()
	{
		living = true;
		running = true;
		super.startSequence();
	}
	
	@Override
	protected boolean loopPredicate(TimeThread thread)
	{
		return super.loopPredicate(thread);
	}
	
	@Override
	protected void loopSequence(float partialSecond)
	{
		super.loopSequence(partialSecond);
	}
	
	@Override
	protected void terminateSequence()
	{
		super.terminateSequence();
		living = false;
		running = false;
	}
	
	@Method
	public boolean isLiving()
	{
		return living;
	}
	
	@Method
	public boolean isRunning()
	{
		return running;
	}
	
	@Method
	public void clear()
	{
		if (running)
		{
			if (VersionFinder.OS == OperatingSystem.Windows)
			{
				try
				{
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				}
				catch (InterruptedException | IOException e)
				{}
			}
			else System.out.print("\u001bc");
		}
	}
	
	@Method
	public void println()
	{
		if (running) out.println();
	}
	
	@Method
	public void println(String message)
	{
		if (running) out.println(message);
	}
	
	@Method
	public void print(String message)
	{
		if (running) out.print(message);
	}
	
	@Method
	public String readString(String message)
	{
		if (!running) return null;
		print(message);
		return readString();
	}
	
	@Method
	public String readString()
	{
		if (!running) return null;
		return in.next();
	}
}
