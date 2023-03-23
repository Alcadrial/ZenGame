package com.alcadrial.zengame.script.io;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Name;

import com.alcadrial.zengame.ZenClass;

@ZenClass
@Name(IoPackage.PACKAGE + "ImageLoader")
public class ImageLoader {
	
	public static final Map<File, Image> LOADED_IMAGES = new HashMap<>();
	public static final Image INVALID_IMAGE;
	
	static
	{
		INVALID_IMAGE = new BufferedImage(16, 16, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = INVALID_IMAGE.getGraphics();
		g.setColor(Color.RED);
		g.fillRect(0, 0, 1, 1);
		g.fillRect(0, 0, 1, 1);
		g.fillRect(0, 0, 1, 1);
		g.fillRect(0, 0, 1, 1);
		g.fillRect(0, 0, 1, 1);
		g.fillRect(0, 0, 1, 1);
		g.fillRect(0, 0, 1, 1);
	}
	
	@Method
	public static Image getImage(String pathname)
	{
		return LOADED_IMAGES.computeIfAbsent(new File(pathname), file -> {
			try
			{
				return ImageIO.read(file);
			}
			catch (IOException e)
			{}
			return INVALID_IMAGE;
		});
	}
	
	private ImageLoader()
	{}
}
