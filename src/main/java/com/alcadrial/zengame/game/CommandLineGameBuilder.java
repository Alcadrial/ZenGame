package com.alcadrial.zengame.game;

import java.io.InputStream;
import java.io.OutputStream;

import org.openzen.zencode.java.ZenCodeType.Setter;

import com.alcadrial.zengame.ZenClass;

@ZenClass
public class CommandLineGameBuilder extends GameBuilder<CommandLineGame> {
	
	private InputStream input;
	private OutputStream output;
	
	public CommandLineGameBuilder(String name)
	{
		super(name);
		input = System.in;
		output = System.out;
	}
	
	@Setter("input")
	public void setInput(InputStream input)
	{
		this.input = input;
	}
	
	@Setter("output")
	public void setOutput(OutputStream output)
	{
		this.output = output;
	}
	
	public InputStream getInput()
	{
		return input;
	}
	
	public OutputStream getOutput()
	{
		return output;
	}
	
	@Override
	public CommandLineGame build(int id)
	{
		return new CommandLineGame(id, this);
	}
}
