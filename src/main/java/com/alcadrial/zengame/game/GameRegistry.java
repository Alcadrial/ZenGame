package com.alcadrial.zengame.game;

import java.util.LinkedHashMap;
import java.util.List;

import org.openzen.zencode.java.ZenCodeType.Method;

import com.alcadrial.zengame.ZenClass;

@ZenClass
public class GameRegistry {
	
	private static final LinkedHashMap<String, Game> GAMES = new LinkedHashMap<>();
	
	public static Game getGame(String name)
	{
		return GAMES.get(name);
	}
	
	public static List<Game> getGames()
	{
		return List.copyOf(GAMES.values());
	}
	
	@Method
	public static GraphicGame registerGraphicGame(String name)
	{
		Game g = GAMES.computeIfAbsent(name, n -> new GraphicGame(n, GAMES.size()));
		if (g instanceof GraphicGame gg) return gg;
		throw new IllegalStateException("The game \"" + name + "\" has been already registered as a Command Line Game");
	}
	
	@Method
	public static CommandLineGame registerCommandLineGame(String name)
	{
		Game g = GAMES.computeIfAbsent(name, n -> new CommandLineGame(n, GAMES.size()));
		if (g instanceof CommandLineGame gg) return gg;
		throw new IllegalStateException("The game \"" + name + "\" has been already registered as a Graphic Game");
	}
	
}
