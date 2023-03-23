package com.alcadrial.zengame;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.openzen.zencode.java.ScriptingEngine;
import org.openzen.zencode.java.module.JavaNativeModule;
import org.openzen.zencode.shared.FileSourceFile;
import org.openzen.zencode.shared.SourceFile;
import org.openzen.zenscript.codemodel.FunctionParameter;
import org.openzen.zenscript.codemodel.HighLevelDefinition;
import org.openzen.zenscript.codemodel.SemanticModule;
import org.openzen.zenscript.lexer.ZSToken;
import org.openzen.zenscript.lexer.ZSTokenType;
import org.openzen.zenscript.parser.PrefixedBracketParser;
import org.openzen.zenscript.parser.expression.ParsedCallArguments;
import org.openzen.zenscript.parser.expression.ParsedExpressionCall;
import org.openzen.zenscript.parser.expression.ParsedExpressionMember;
import org.openzen.zenscript.parser.expression.ParsedExpressionVariable;
import org.openzen.zenscript.parser.expression.ParsedTypeExpression;
import org.openzen.zenscript.parser.type.ParsedNamedType;
import org.openzen.zenscript.parser.type.ParsedNamedType.ParsedNamePart;

import com.alcadrial.kroperties.Property;
import com.alcadrial.zengame.script.GlobalZenUtils;
import com.alcadrial.zengame.script.extra.ZenAction;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import sun.misc.Unsafe;

public class ZenGame {
	
	private static final Unsafe UNSAFE;
	static
	{
		Class<Unsafe> klass = Unsafe.class;
		try
		{
			Field field = klass.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			UNSAFE = (Unsafe) field.get(null);
		}
		catch (Exception e)
		{
			throw new IllegalStateException("Cannot obtain the unsafe");
		}
	}
	
	public static void main(String[] args)
	{
		Property.parseProperties(args, Properties.PROPERTIES);
		
		System.out.println("Zen Game Z");
		try
		{
			ScriptingEngine engine = new ScriptingEngine();
			
			JavaNativeModule zengame = engine.createNativeModule("zengame", GlobalZenUtils.ZEN_PACKAGE);
			
			zengame.addGlobals(GlobalZenUtils.class);
			
			ClassGraph classGraph = new ClassGraph();
			ScanResult result = classGraph.acceptPackages("com.alcadrial").enableClassInfo().enableAnnotationInfo().scan();
			ClassInfoList zenClasses = result.getClassesWithAnnotation(ZenClass.class);
			
			for (ClassInfo c : zenClasses)
			{
				Class<?> klass = ZenGame.class.getClassLoader().loadClass(c.getName());
				System.out.println(klass);
				if (klass.isEnum())
				{
					System.out.println(Arrays.toString(klass.getEnumConstants()));
					System.out.println(Arrays.toString(klass.getFields()));
				}
				HighLevelDefinition def = zengame.addClass(klass);
				
				System.out.println(def.getFullName());
				
				zengame.addGlobals(klass);
			}
			
			engine.registerNativeProvided(zengame);
			
			File[] scriptFiles = Files.walk(new File(Properties.SCRIPTS_PATH.getValue()).toPath()).filter(p -> p.toString().endsWith(".zs")).map(Path::toFile).toArray(File[]::new);
			
			SourceFile[] sourceFiles = new SourceFile[scriptFiles.length];
			for (int i = 0; i < scriptFiles.length; i++) sourceFiles[i] = new FileSourceFile(scriptFiles[i].getName(), scriptFiles[i]);
			
			PrefixedBracketParser bracketParser = new PrefixedBracketParser(null);
			
			bracketParser.register("action", new EnumBracketParser<>(zengame, zengame.addClass(ZenAction.class)));
			
			bracketParser.register("actione", (position, tokens) -> {
				ZSToken token = tokens.required(ZSTokenType.T_IDENTIFIER, "Unknown identifier");
				tokens.required(ZSTokenType.T_GREATER, "Invalid Bracket Expression");
				
				ParsedTypeExpression type = new ParsedTypeExpression(position, new ParsedNamedType(position, List.of(new ParsedNamePart("Action", null))));
				
				// return new ParsedExpressionVariable(position, "WORKING", null);
				// return new ParsedExpressionMember(position, new ParsedExpressionVariable(position, "Action", null), "WORKING", null);
				return new ParsedExpressionCall(position, new ParsedExpressionMember(position, new ParsedExpressionVariable(position, "Action", null), token.content, null), new ParsedCallArguments(null, List.of()));
				// return new ParsedTypeExpression(position, new ParsedNamedType(position, List.of(new ParsedNamePart("Action", null)), null));
				
				// if (type.name.size() != 1) throw new ParseException(position, "Invalid action " + type);
				// if (type.name.get(0).name.toLowerCase().equals(type.name.get(0).name))
				// {
				// ZenAction action = ZenAction.valueOf(type.name.get(0).name.toUpperCase());
				// if (action != null)
				// return new ParsedExpressionMember(position, new ParsedTypeExpression(position, type), /* action.name() */type.name.get(0).name, null);
				// }
				// throw new ParseException(position, "Unknown action");
			});
			
			SemanticModule scripts = engine.createScriptedModule("scripts", sourceFiles, bracketParser, FunctionParameter.NONE, "zengame");
			if (!scripts.isValid()) return;
			
			engine.registerCompiled(scripts);
			engine.run();
		}
		catch (Exception e)
		{
			e.printStackTrace(System.out);
		}
	}
}
