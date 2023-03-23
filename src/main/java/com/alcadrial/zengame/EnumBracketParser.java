package com.alcadrial.zengame;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openzen.zencode.java.module.JavaNativeModule;
import org.openzen.zencode.shared.CodePosition;
import org.openzen.zenscript.codemodel.HighLevelDefinition;
import org.openzen.zenscript.codemodel.definition.EnumDefinition;
import org.openzen.zenscript.codemodel.member.EnumConstantMember;
import org.openzen.zenscript.lexer.ParseException;
import org.openzen.zenscript.lexer.ZSToken;
import org.openzen.zenscript.lexer.ZSTokenParser;
import org.openzen.zenscript.lexer.ZSTokenType;
import org.openzen.zenscript.parser.BracketExpressionParser;
import org.openzen.zenscript.parser.expression.ParsedExpression;
import org.openzen.zenscript.parser.expression.ParsedExpressionMember;
import org.openzen.zenscript.parser.expression.ParsedExpressionVariable;

public class EnumBracketParser<E extends Enum<E>> implements BracketExpressionParser {
	
	private List<String> fieldNames;
	private EnumDefinition classDefinition;
	
	public EnumBracketParser(JavaNativeModule module, HighLevelDefinition classDefinition)
	{
		if (!(classDefinition instanceof EnumDefinition)) throw new IllegalArgumentException("Non Enum definition");
		this.classDefinition = (EnumDefinition) classDefinition;
		
		List<EnumConstantMember> fields = this.classDefinition.enumConstants;
		System.out.println(fields);
		
		fieldNames = new ArrayList<>(fields.size());
		if (fields.size() > 0)
		{
			for (EnumConstantMember f : fields)
			{
				fieldNames.add(f.name.toLowerCase(Locale.ENGLISH));
			}
		}
		
	}
	
	@Override
	public ParsedExpression parse(CodePosition position, ZSTokenParser tokens) throws ParseException
	{
		ZSToken token = tokens.required(ZSTokenType.T_IDENTIFIER, "Invalid Bracket Expression (required identifier)");
		tokens.required(ZSTokenType.T_GREATER, "Invalid Bracket Expression (never closed)");
		// if (!fieldNames.contains(token.getContent())) throw new ParseException(position.withLength(token.getContent().length()), "Unknown " + classDefinition.name + " \"" + token.getContent() + "\", valids
		// are " + fieldNames.toString());
		
		return new ParsedExpressionMember(position, new ParsedExpressionVariable(position, classDefinition.name, null), token.getContent().toUpperCase(), null);
	}
}
