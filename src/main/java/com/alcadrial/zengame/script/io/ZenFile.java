package com.alcadrial.zengame.script.io;

import java.io.File;
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

@ZenClass
@Name(IoPackage.PACKAGE + "File")
public class ZenFile {
	
	@BracketParser("file")
	public static ParsedNewExpression parse(CodePosition position, ZSTokenParser tokens) throws ParseException
	{
		return new ParsedNewExpression(position, new ParsedNamedType(position, List.of(ZenFile.class.getAnnotation(Name.class).value().split("\\.")).stream().map(s -> new ParsedNamePart(s, null)).toList()), new ParsedCallArguments(null, List.of(parsePath(position, tokens))));
	}
	
	public static ParsedExpressionString parsePath(CodePosition position, ZSTokenParser tokens) throws ParseException
	{
		String path = "";
		
		while (tokens.optional(ZSTokenType.T_GREATER) == null)
		{
			path += tokens.getLastWhitespace() + tokens.next().getContent();
		}
		
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
	
	private File file;
	
	@Constructor
	public ZenFile(String path)
	{
		file = new File(path);
	}
	
	@Getter("path")
	public String getPath()
	{
		return file.getAbsolutePath();
	}
	
	public File getFile()
	{
		return file;
	}
}
