package com.alcadrial.zengame.game;

public class SwingGraphicGameBuilder extends GameBuilder<SwingGraphicGame> {
	
	public SwingGraphicGameBuilder(String name)
	{
		super(name);
	}
	
	@Override
	public SwingGraphicGame build(int id)
	{
		return new SwingGraphicGame(id, this);
	}
}
