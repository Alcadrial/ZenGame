package com.alcadrial.zengame;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openzen.zencode.java.ScriptingEngine;
import org.openzen.zencode.java.logger.ScriptingEngineStreamLogger;
import org.openzen.zencode.java.module.JavaNativeModule;
import org.openzen.zencode.shared.FileSourceFile;
import org.openzen.zencode.shared.SourceFile;
import org.openzen.zenscript.codemodel.FunctionParameter;
import org.openzen.zenscript.codemodel.SemanticModule;
import org.openzen.zenscript.parser.PrefixedBracketParser;

import com.alcadrial.kroperties.Property;
import com.alcadrial.zengame.game.Game;
import com.alcadrial.zengame.game.GameRegistry;
import com.alcadrial.zengame.script.GlobalZenUtils;
import com.alcadrial.zengame.script.extra.ZenAction;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

public class ZenGame {
	
	public static final String ZENGAME_MODULE = "zengame";
	public static final String ZENGAME_CLASS_PACKAGE = "zengame.";
	
	public static void main(String[] args)
	{
		Property.parseProperties(args, Properties.PROPERTIES);
		
		System.out.println("Zen Game Z");
		try
		{
			File logFile = new File("zenscriptLog.txt");
			PrintStream logStream = new PrintStream(logFile);
			ScriptingEngine engine = new ScriptingEngine(new ScriptingEngineStreamLogger(logStream, logStream));
			
			JavaNativeModule zengame = engine.createNativeModule(ZENGAME_MODULE, GlobalZenUtils.ZEN_PACKAGE);
			
			zengame.addGlobals(GlobalZenUtils.class);
			
			ClassGraph classGraph = new ClassGraph();
			ScanResult result = classGraph.acceptPackages("com.alcadrial").enableClassInfo().enableAnnotationInfo().scan();
			ClassInfoList zenClasses = result.getClassesWithAnnotation(ZenClass.class);
			
			for (ClassInfo c : zenClasses)
			{
				Class<?> klass = ZenGame.class.getClassLoader().loadClass(c.getName());
				zengame.addClass(klass);
				zengame.addGlobals(klass);
			}
			
			engine.registerNativeProvided(zengame);
			
			File[] scriptFiles = Files.walk(new File(Properties.SCRIPTS_PATH.getValue()).toPath()).filter(p -> p.toString().endsWith(".zs")).map(Path::toFile).toArray(File[]::new);
			
			SourceFile[] sourceFiles = new SourceFile[scriptFiles.length];
			for (int i = 0; i < scriptFiles.length; i++) sourceFiles[i] = new FileSourceFile(scriptFiles[i].getName(), scriptFiles[i]);
			
			PrefixedBracketParser bracketParser = new PrefixedBracketParser(null);
			
			bracketParser.register("action", new EnumBracketParser<>(zengame, zengame.addClass(ZenAction.class)));
			
			SemanticModule scripts = engine.createScriptedModule("scripts", sourceFiles, bracketParser, FunctionParameter.NONE, ZENGAME_MODULE);
			if (!scripts.isValid()) return;
			
			engine.registerCompiled(scripts);
			engine.run();
			
			String gameName = Properties.GAME.getValue();
			if (gameName != null)
			{
				Game game = GameRegistry.getGame(gameName);
				if (game != null)
				{
					game.start();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace(System.out);
		}
	}
}