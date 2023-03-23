package com.alcadrial.zengame.game;

import javax.swing.JFrame;

import org.openzen.zencode.java.ZenCodeType.Getter;
import org.openzen.zencode.java.ZenCodeType.Method;

public class SwingGraphicGame extends Game {
	
	@Method
	public static SwingGraphicGameBuilder create(String name)
	{
		return new SwingGraphicGameBuilder(name);
	}
	
	private JFrame window;
	
	public SwingGraphicGame(int id, SwingGraphicGameBuilder builder)
	{
		super(id, builder);
		window = new JFrame();
		window.setTitle(builder.getName());
	}
	
	@Override
	public void start()
	{
		super.start();
		window.setVisible(true);
	}
	
	@Override
	public void pause()
	{
		super.pause();
	}
	
	@Getter("window")
	public JFrame getWindow()
	{
		return window;
	}
}
