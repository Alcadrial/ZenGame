package com.alcadrial.zengame.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;

import org.openzen.zencode.java.ZenCodeType.Getter;
import org.openzen.zencode.java.ZenCodeType.Method;

import com.alcadra.threads.TimeThread;
import com.alcadrial.zengame.script.graphics.ZenColor;
import com.alcadrial.zengame.script.graphics.ZenImage;
import com.alcadrial.zengame.script.io.ImageLoader;
import com.alcadrial.zengame.script.mouse.ButtonContext;
import com.alcadrial.zengame.script.mouse.ZenMouseListener;

public class SwingGraphicGame extends Game implements WindowListener, MouseListener, WindowStateListener {
	
	@Method
	public static SwingGraphicGameBuilder create(String name)
	{
		return new SwingGraphicGameBuilder(name);
	}
	
	private JFrame window;
	private Runnable paintAction;
	private ZenMouseListener onClick;
	private boolean preserveFrame;
	private BufferedImage lastFrame;
	private Graphics windowGraphics;
	private Graphics imageGraphics;
	private boolean ignoreNextPaint;
	private BackgroundPanel backgroundPanel;
	
	public SwingGraphicGame(int id, SwingGraphicGameBuilder builder)
	{
		super(id, builder);
		paintAction = builder.getPaintAction();
		onClick = builder.getOnClick();
		window = new JFrame(builder.getName());
		window.setBounds(builder.getX(), builder.getY(), builder.getWidth(), builder.getHeight());
		if (builder.getIcon() != null) window.setIconImage(ImageLoader.getImage(builder.getIcon().getFile()));
		window.setUndecorated(!builder.isDecorated());
		window.setContentPane(backgroundPanel = new BackgroundPanel());
		backgroundPanel.setBackground(builder.getBackgroundColor());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addWindowStateListener(this);
		window.addWindowListener(this);
		backgroundPanel.addMouseListener(this);
		preserveFrame = true;
		ignoreNextPaint = false;
		if (preserveFrame) lastFrame = new BufferedImage(window.getWidth(), window.getHeight(), BufferedImage.TYPE_INT_RGB);
	}
	
	public void setColor(Graphics graphics, Color color)
	{
		graphics.setColor(color);
	}
	
	public void clear(Graphics graphics)
	{
		graphics.fillRect(1, 1, getWidth() - 1, getHeight() - 1);
	}
	
	public void setPixel(Graphics graphics, int x, int y)
	{
		graphics.fillRect(x, y, 1, 1);
	}
	
	public void fill(Graphics graphics, int x, int y, int width, int height)
	{
		graphics.fillRect(x, y, width, height);
	}
	
	public void drawImage(Graphics graphics, Image image)
	{
		graphics.drawImage(image, 0, 0, null);
	}
	
	public void drawImage(Graphics graphics, Image image, int x, int y)
	{
		graphics.drawImage(image, x, y, null);
	}
	
	public void drawImage(Graphics graphics, Image image, int x, int y, int width, int height)
	{
		graphics.drawImage(image, x, y, width, height, null);
	}
	
	@Method
	public void setColor(int r, int g, int b, int a)
	{
		setColor(windowGraphics, new Color(r, g, b, a));
		if (preserveFrame) setColor(imageGraphics, new Color(r, g, b, a));
	}
	
	@Method
	public void setColor(ZenColor color)
	{
		setColor(windowGraphics, color.getColor());
		if (preserveFrame) setColor(imageGraphics, color.getColor());
	}
	
	@Method
	public void clear()
	{
		clear(windowGraphics);
		if (preserveFrame) clear(imageGraphics);
	}
	
	@Method
	public void setPixel(int x, int y)
	{
		setPixel(windowGraphics, x, y);
		if (preserveFrame) setPixel(imageGraphics, x, y);
	}
	
	@Method
	public void fill(int x, int y, int width, int height)
	{
		fill(windowGraphics, x, y, width, height);
		if (preserveFrame) fill(imageGraphics, x, y, width, height);
	}
	
	@Method
	public void drawImage(ZenImage image)
	{
		drawImage(windowGraphics, image.getImage());
		if (preserveFrame) drawImage(imageGraphics, image.getImage());
	}
	
	@Method
	public void drawImage(ZenImage image, int x, int y)
	{
		drawImage(windowGraphics, image.getImage(), x, y);
		if (preserveFrame) drawImage(imageGraphics, image.getImage(), x, y);
	}
	
	@Method
	public void drawImage(ZenImage image, int x, int y, int width, int height)
	{
		drawImage(windowGraphics, image.getImage(), x, y, width, height);
		if (preserveFrame) drawImage(imageGraphics, image.getImage(), x, y, width, height);
	}
	
	@Override
	protected void startSequence()
	{
		window.setVisible(true);
		windowGraphics = backgroundPanel.getGraphics();
		if (preserveFrame) imageGraphics = lastFrame.getGraphics();
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
	
	@Getter("width")
	public int getWidth()
	{
		return backgroundPanel.getWidth();
	}
	
	@Getter("height")
	public int getHeight()
	{
		return backgroundPanel.getHeight();
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
	{}
	
	@Override
	public void windowDeiconified(WindowEvent e)
	{
		if (preserveFrame) ignoreNextPaint = true;
	}
	
	@Override
	public void windowActivated(WindowEvent e)
	{}
	
	@Override
	public void windowDeactivated(WindowEvent e)
	{}
	
	@Override
	public void windowStateChanged(WindowEvent e)
	{}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		onClick.handle(new ButtonContext(e));
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{}
	
	@Override
	public void mouseExited(MouseEvent e)
	{}
	
	@Override
	public void mousePressed(MouseEvent e)
	{}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{}
	
	private class BackgroundPanel extends Panel {
		
		@Override
		public void paint(Graphics g)
		{
			if (!ignoreNextPaint)
			{
				super.paint(g);
				windowGraphics = g;
				paintAction.run();
				windowGraphics = backgroundPanel.getGraphics();
			}
			else
			{
				Object lock = new Object();
				ImageObserver observer = (img, infoflags, x, y, width, height) -> {
					
					if ((infoflags & ImageObserver.ALLBITS) != 0)
					{
						synchronized (lock)
						{
							lock.notify();
						}
						return false;
					}
					return true;
				};
				if (!backgroundPanel.getGraphics().drawImage(lastFrame, 0, 0, observer))
				{
					synchronized (lock)
					{
						try
						{
							lock.wait();
						}
						catch (InterruptedException e0)
						{}
					}
				}
				ignoreNextPaint = false;
			}
		}
	}
	
	@Override
	public void close() throws Exception
	{
		if (preserveFrame) imageGraphics.dispose();
		windowGraphics.dispose();
		window.dispose();
	}
}
