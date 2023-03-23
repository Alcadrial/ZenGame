package com.alcadrial.zengame;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import org.openzen.zencode.java.ScriptingEngine;
import org.openzen.zencode.java.logger.ScriptingEngineStreamLogger;
import org.openzen.zencode.java.module.JavaNativeModule;
import org.openzen.zencode.shared.CodePosition;
import org.openzen.zencode.shared.FileSourceFile;
import org.openzen.zencode.shared.SourceFile;
import org.openzen.zenscript.codemodel.FunctionParameter;
import org.openzen.zenscript.codemodel.SemanticModule;
import org.openzen.zenscript.lexer.ZSTokenParser;
import org.openzen.zenscript.lexer.ZSTokenType;
import org.openzen.zenscript.parser.PrefixedBracketParser;
import org.openzen.zenscript.parser.expression.ParsedExpression;
import org.openzen.zenscript.parser.statements.ParsedLambdaFunctionBody;

import com.alcadrial.kroperties.Property;
import com.alcadrial.zengame.game.Game;
import com.alcadrial.zengame.game.GameRegistry;
import com.alcadrial.zengame.script.GlobalZenUtils;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.MethodInfo;
import io.github.classgraph.ScanResult;

public class ZenGame {
	
	public static final String ZENGAME_MODULE = "zengame";
	public static final String ZENGAME_CLASS_PACKAGE = "zengame.";
	
	public static void main(String[] args)
	{
		Property.parseProperties(args, Properties.PROPERTIES);
		try
		{
			File logFile = new File("zenscriptLog.txt");
			PrintStream logStream = new PrintStream(logFile);
			ScriptingEngine engine = new ScriptingEngine(new ScriptingEngineStreamLogger(logStream, logStream));
			
			JavaNativeModule zengame = engine.createNativeModule(ZENGAME_MODULE, GlobalZenUtils.ZEN_PACKAGE);
			
			zengame.addGlobals(GlobalZenUtils.class);
			
			ClassGraph classGraph = new ClassGraph();
			ScanResult result = classGraph.acceptPackages("com.alcadrial").enableClassInfo().enableMethodInfo().enableAnnotationInfo().scan();
			ClassInfoList zenClasses = result.getClassesWithAnnotation(ZenClass.class);
			PrefixedBracketParser bracketParser = new PrefixedBracketParser(null);
			
			for (ClassInfo c : zenClasses)
			{
				Class<?> klass = c.loadClass();
				if (klass.getAnnotation(ZenClass.class).value()) zengame.addClass(klass);
				zengame.addGlobals(klass);
				for (MethodInfo m : c.getMethodInfo()) if (m.hasAnnotation(BracketParser.class))
				{
					Method method = m.loadClassAndGetMethod();
					if (Modifier.isStatic(method.getModifiers()) && ParsedExpression.class.isAssignableFrom(method.getReturnType()))
					{
						Class<?>[] parameters = method.getParameterTypes();
						if (parameters.length == 2 && parameters[0].isAssignableFrom(CodePosition.class) && parameters[1].isAssignableFrom(ZSTokenParser.class))
						{
							bracketParser.register(method.getAnnotation(BracketParser.class).value(), (position, parser) -> {
								try
								{
									return (ParsedExpression) method.invoke(null, position, parser);
								}
								catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
								{}
								return null;
							});
							continue;
						}
					}
					throw new IllegalStateException("Invalid BracketParser (" + method + ") in class " + klass);
				}
			}
			
			engine.registerNativeProvided(zengame);
			
			File[] scriptFiles = Files.walk(new File(Properties.SCRIPTS_PATH.getValue()).toPath()).filter(p -> p.toString().endsWith(".zs")).map(Path::toFile).toArray(File[]::new);
			
			SourceFile[] sourceFiles = new SourceFile[scriptFiles.length];
			for (int i = 0; i < scriptFiles.length; i++) sourceFiles[i] = new FileSourceFile(scriptFiles[i].getName(), scriptFiles[i]);
			
			// bracketParser.register("action", new EnumBracketParser<>(zengame, zengame.addClass(ZenAction.class)));
			
			SemanticModule scripts = engine.createScriptedModule("zengame", sourceFiles, bracketParser, FunctionParameter.NONE, ZENGAME_MODULE);
			if (!scripts.isValid()) return;
			
			engine.registerCompiled(scripts);
			engine.run();
			
			List<Game> games = GameRegistry.getGames();
			
			if (games.size() == 0) return;
			
			List<String> names = games.stream().map(Game::getName).toList();
			
			String gameName = Properties.GAME.getValue();
			
			if (gameName == null || !names.contains(gameName))
			{
				System.out.print("Available Games: ");
				for (String name : names.subList(0, names.size() - 1))
				{
					System.out.print(name + " - ");
				}
				System.out.println(names.get(names.size() - 1));
				System.out.println();
				System.out.print("Select the game: ");
				try (Scanner input = new Scanner(System.in))
				{
					gameName = input.next();
					while (!names.contains(gameName))
					{
						System.out.print("Unknown game, reselect it: ");
						gameName = input.next();
					}
				}
			}
			GameRegistry.getGame(gameName).start();
		}
		catch (Exception e)
		{
			e.printStackTrace(System.out);
		}
	}
	
	/**
	 * {@link ZSTokenType}
	 * <p>
	 * {@link ParsedLambdaFunctionBody}
	 * {@link ParsedExpression}
	 */
	@SuppressWarnings("unused")
	private static final Object INTERNAL_INFO = null;
}
