package com.alcadrial.zengame.script.graphics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.openzen.zencode.java.ZenCodeType.Constructor;
import org.openzen.zencode.java.ZenCodeType.Getter;
import org.openzen.zencode.java.ZenCodeType.Name;
import org.openzen.zencode.shared.CodePosition;
import org.openzen.zenscript.lexer.ParseException;
import org.openzen.zenscript.lexer.ZSToken;
import org.openzen.zenscript.lexer.ZSTokenParser;
import org.openzen.zenscript.parser.expression.ParsedCallArguments;
import org.openzen.zenscript.parser.expression.ParsedExpression;
import org.openzen.zenscript.parser.expression.ParsedExpressionFloat;
import org.openzen.zenscript.parser.expression.ParsedExpressionInt;
import org.openzen.zenscript.parser.expression.ParsedExpressionVariable;
import org.openzen.zenscript.parser.expression.ParsedLocalVariableExpression;
import org.openzen.zenscript.parser.expression.ParsedNewExpression;
import org.openzen.zenscript.parser.type.ParsedNamedType;
import org.openzen.zenscript.parser.type.ParsedNamedType.ParsedNamePart;

import com.alcadrial.zengame.BracketParser;
import com.alcadrial.zengame.ZenClass;

@ZenClass
@Name(GraphicsPackage.PACKAGE + "Color")
public class ZenColor {
	
	@BracketParser(name = "color", format = "")
	public static ParsedNewExpression parse(CodePosition position, ZSTokenParser tokens) throws ParseException
	{
		return new ParsedNewExpression(position, new ParsedNamedType(position, List.of(ZenColor.class.getAnnotation(Name.class).value().split("\\.")).stream().map(s -> new ParsedNamePart(s, null)).toList()), new ParsedCallArguments(null, parseParameters(position, tokens)));
	}
	
	public static List<ParsedExpression> parseParameters(CodePosition position, ZSTokenParser tokens) throws ParseException
	{
		List<ParsedExpression> values = new ArrayList<>(4);
		boolean requiredColon = false;
		loop: while (true)
		{
			switch (tokens.peek().getType())
			{
				case T_LOCAL_IDENTIFIER ->
				{
					if (requiredColon) throw new ParseException(tokens.getPosition(), "Invalid sintax");
					ZSToken token = tokens.next();
					values.add(new ParsedLocalVariableExpression(position, token.getContent().substring(1)));
					requiredColon = true;
				}
				case T_IDENTIFIER ->
				{
					if (requiredColon) throw new ParseException(tokens.getPosition(), "Invalid sintax");
					ZSToken token = tokens.next();
					values.add(new ParsedExpressionVariable(position, token.getContent(), null));
					requiredColon = true;
				}
				case T_FLOAT ->
				{
					if (requiredColon) throw new ParseException(tokens.getPosition(), "Invalid sintax");
					ZSToken token = tokens.next();
					values.add(new ParsedExpressionFloat(position, token.getContent()));
					requiredColon = true;
				}
				case T_INT ->
				{
					if (requiredColon) throw new ParseException(tokens.getPosition(), "Invalid sintax");
					ZSToken token = tokens.next();
					values.add(new ParsedExpressionInt(position, token.getContent()));
					requiredColon = true;
				}
				case T_COLON ->
				{
					if (!requiredColon) throw new ParseException(tokens.getPosition(), "Invalid sintax");
					tokens.next();
					requiredColon = false;
				}
				case T_GREATER ->
				{
					tokens.next();
					break loop;
				}
				default -> throw new ParseException(tokens.getPosition(), "Invalid sintax");
			}
		}
		if (values.size() < 3) throw new ParseException(position, "Not enough colors");
		if (values.size() > 4) throw new ParseException(position, "Too much colors");
		return values;
	}
	
	private Color color;
	
	public ZenColor(Color color)
	{
		this.color = color;
	}
	
	@Constructor
	public ZenColor(float r, float g, float b, float a)
	{
		color = new Color(r / 255, g / 255, b / 255, a / 255);
	}
	
	@Getter("color")
	public Color getColor()
	{
		return color;
	}
}
