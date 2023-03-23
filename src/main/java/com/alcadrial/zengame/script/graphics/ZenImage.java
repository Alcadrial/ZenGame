package com.alcadrial.zengame.script.graphics;

import java.awt.Image;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

import org.openzen.zencode.java.ZenCodeType.Constructor;
import org.openzen.zencode.java.ZenCodeType.Getter;
import org.openzen.zencode.java.ZenCodeType.Name;
import org.openzen.zencode.shared.CodePosition;
import org.openzen.zenscript.lexer.ParseException;
import org.openzen.zenscript.lexer.ZSTokenParser;
import org.openzen.zenscript.lexer.ZSTokenType;
import org.openzen.zenscript.parser.expression.ParsedCallArguments;
import org.openzen.zenscript.parser.expression.ParsedExpressionString;
import org.openzen.zenscript.parser.expression.ParsedNewExpression;
import org.openzen.zenscript.parser.type.ParsedNamedType;
import org.openzen.zenscript.parser.type.ParsedNamedType.ParsedNamePart;

import com.alcadrial.zengame.BracketParser;
import com.alcadrial.zengame.ZenClass;
import com.alcadrial.zengame.script.io.ImageLoader;
import com.alcadrial.zengame.script.io.ZenFile;

@ZenClass
@Name(GraphicsPackage.PACKAGE + "Image")
public class ZenImage {
	
	private static final String[] KNOWN_FORMATS = new String[] {"png"};
	
	@BracketParser(name = "image", format = "<image:§ValidImageFilePath§>")
	public static ParsedNewExpression parse(CodePosition position, ZSTokenParser tokens) throws ParseException
	{
		return new ParsedNewExpression(position, new ParsedNamedType(position, List.of(ZenImage.class.getAnnotation(Name.class).value().split("\\.")).stream().map(s -> new ParsedNamePart(s, null)).toList()), new ParsedCallArguments(null, List.of(parsePath(position, tokens))));
	}
	
	public static ParsedExpressionString parsePath(CodePosition position, ZSTokenParser tokens) throws ParseException
	{
		String path = "";
		
		while (tokens.optional(ZSTokenType.T_GREATER) == null)
		{
			path += tokens.getLastWhitespace() + tokens.next().getContent();
		}
		boolean withKnownFormat = false;
		for (String format : KNOWN_FORMATS) if (path.substring(path.lastIndexOf('.') + 1).equals(format)) withKnownFormat = true;
		if (!withKnownFormat) path += ".png";
		try
		{
			Path.of(path);
		}
		catch (InvalidPathException e)
		{
			throw new ParseException(position, "Invalid path", e);
		}
		return new ParsedExpressionString(position, path, false);
	}
	
	private ZenFile file;
	private Image image;
	
	@Constructor
	public ZenImage(ZenFile file)
	{
		image = ImageLoader.getImage(file.getFile());
		this.file = file;
	}
	
	@Getter("file")
	public ZenFile getFile()
	{
		return file;
	}
	
	public Image getImage()
	{
		return image;
	}
}
