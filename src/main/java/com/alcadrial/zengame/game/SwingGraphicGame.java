package com.alcadrial.zengame.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import org.openzen.zencode.java.ZenCodeType.Getter;
import org.openzen.zencode.java.ZenCodeType.Method;

import com.alcadra.threads.TimeThread;
import com.alcadrial.zengame.script.io.ImageLoader;

public class SwingGraphicGame extends Game implements WindowListener {
	
	@Method
	public static SwingGraphicGameBuilder create(String name)
	{
		return new SwingGraphicGameBuilder(name);
	}
	
	private JFrame window;
	private Runnable paintAction;
	
	public SwingGraphicGame(int id, SwingGraphicGameBuilder builder)
	{
		super(id, builder);
		paintAction = builder.getPaintAction();
		window = new JFrame(builder.getName());
		window.setBounds(builder.getX(), builder.getY(), builder.getWidth(), builder.getHeight());
		if (builder.getIcon() != null) window.setIconImage(ImageLoader.getImage(builder.getIcon()));
		window.setUndecorated(true);
		// window.setContentPane(new BackgroundPanel());
		window.getContentPane().setBackground(builder.getBackgroundColor());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addWindowListener(this);
	}
	
	@Method
	public void clearColor(int r, int g, int b, int a)
	{
		Graphics graphics = window.getGraphics();
		graphics.setColor(new Color(r, g, b, a));
		graphics.fillRect(1, 1, window.getWidth() - 1, window.getHeight() - 1);
	}
	
	@Method
	public void setColor(int x, int y, int r, int g, int b, int a)
	{
		Graphics graphics = window.getGraphics();
		graphics.setColor(new Color(r, g, b, a));
		graphics.fillRect(x, y, 1, 1);
	}
	
	@Method
	public void fillColor(int x, int y, int width, int height, int r, int g, int b, int a)
	{
		Graphics graphics = window.getGraphics();
		graphics.setColor(new Color(r, g, b, a));
		graphics.fillRect(x, y, width, height);
	}
	
	@Override
	protected void startSequence()
	{
		window.setVisible(true);
		super.startSequence();
	}
	
	@Override
	protected boolean loopPredicate(TimeThread thread)
	{
		return super.loopPredicate(thread) && window.isValid();
	}
	
	@Override
	protected void terminateSequence()
	{
		super.terminateSequence();
		window.dispose();
	}
	
	@Getter("window")
	public JFrame getWindow()
	{
		return window;
	}
	
	@Override
	public void windowOpened(WindowEvent e)
	{}
	
	@Override
	public void windowClosing(WindowEvent e)
	{}
	
	@Override
	public void windowClosed(WindowEvent e)
	{}
	
	@Override
	public void windowIconified(WindowEvent e)
	{
		System.out.println("Iconi " + window.getGraphics().hashCode());
		System.out.println("Iconi " + window.getContentPane().getGraphics().hashCode());
	}
	
	@Override
	public void windowDeiconified(WindowEvent e)
	{
		System.out.println("Deico " + window.getGraphics().hashCode());
		System.out.println("Deico " + window.getContentPane().getGraphics().hashCode());
	}
	
	@Override
	public void windowActivated(WindowEvent e)
	{}
	
	@Override
	public void windowDeactivated(WindowEvent e)
	{}
	
	private class BackgroundPanel extends Panel {
		
		@Override
		public void paint(Graphics g)
		{
			super.paint(g);
			paintAction.run();
		}
	}
	
	@Override
	public void close() throws Exception
	{
		window.dispose();
	}
}
