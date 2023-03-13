package com.alcadrial.zengame.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import org.openzen.zencode.java.ZenCodeType.Setter;

import com.alcadrial.zengame.script.graphics.ZenColor;
import com.alcadrial.zengame.script.io.ZenFile;

public class SwingGraphicGameBuilder extends GameBuilder<SwingGraphicGame> {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private ZenFile icon;
	private Color backgroundColor;
	private Runnable paintAction;
	
	public SwingGraphicGameBuilder(String name)
	{
		super(name);
		x = -1;
		y = -1;
		width = 200;
		height = 200;
		backgroundColor = Color.BLACK;
		paintAction = () -> {};
	}
	
	@Setter("x")
	public void setX(int x)
	{
		this.x = x;
	}
	
	@Setter("y")
	public void setY(int y)
	{
		this.y = y;
	}
	
	@Setter("width")
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	@Setter("height")
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	@Setter("icon")
	public void setIcon(ZenFile icon)
	{
		this.icon = icon;
	}
	
	@Setter("backgroundColor")
	public void setBackgroundColor(ZenColor color)
	{
		backgroundColor = color.getColor();
	}
	
	@Setter("paintAction")
	public void setPaintAction(Runnable paintAction)
	{
		this.paintAction = paintAction;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public ZenFile getIcon()
	{
		return icon;
	}
	
	public Color getBackgroundColor()
	{
		return backgroundColor;
	}
	
	public Runnable getPaintAction()
	{
		return paintAction;
	}
	
	@Override
	public SwingGraphicGame build(int id)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (x == -1) x = (int) ((screenSize.getWidth() - width) / 2);
		if (y == -1) y = (int) ((screenSize.getHeight() - height) / 2);
		return new SwingGraphicGame(id, this);
	}
}
