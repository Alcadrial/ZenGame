package com.alcadrial.zengame.game;

import javax.swing.JFrame;

import org.openzen.zencode.java.ZenCodeType.Getter;

public class GraphicGame extends Game {
	
	private JFrame window;
	
	public GraphicGame(String name, int id)
	{
		super(name, id);
		window = new JFrame();
		window.setTitle(name);
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
