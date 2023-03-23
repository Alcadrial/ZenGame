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
	
	public static <G extends Game> G register(GameBuilder<G> gameBuilder)
	{
		G game = gameBuilder.build(GAMES.size());
		if (GAMES.putIfAbsent(game.getName(), game) != null) throw new IllegalStateException("The game \"" + game.getName() + "\" has been already registered");
		return game;
	}
	
	@Method
	public static CommandLineGame register(CommandLineGameBuilder gameBuilder)
	{
		return register((GameBuilder<CommandLineGame>) gameBuilder);
	}
	
	@Method
	public static SwingGraphicGame register(SwingGraphicGameBuilder gameBuilder)
	{
		return register((GameBuilder<SwingGraphicGame>) gameBuilder);
	}
}
