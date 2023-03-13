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

import com.alcadrial.zengame.Properties;
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
		for (int i = 0; i < 16; i++)
		{
			g.fillRect(i, i, 1, 1);
			g.fillRect(i, 16 - i - 1, 1, 1);
		}
	}
	
	@Method
	public static Image getImage(ZenFile file)
	{
		return LOADED_IMAGES.computeIfAbsent(new File(Properties.ASSETS_PATH.getValue(), file.getFile().getPath()), file0 -> {
			try
			{
				return ImageIO.read(file0);
			}
			catch (IOException e)
			{
				System.out.println("Image not found at " + file0);
			}
			return INVALID_IMAGE;
		});
	}
	
	private ImageLoader()
	{}
}
