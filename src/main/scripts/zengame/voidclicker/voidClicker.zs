import zengame.graphics.Color;
import zengame.graphics.Image;
import zengame.io.File;
import zengame.io.ImageLoader;
import zengame.keyboard.KeyContext;
import zengame.mouse.ButtonContext;

import javax.naming.Context;

import zengame.GameRegistry;
import zengame.SwingGraphicGame;

public class VoidClicker {

	public static final var RED_COLOR = <color:255:0:0:255>;
	public static final var GREEN_COLOR = <color:0:255:0:255>;
	public static final var BLUE_COLOR = <color:0:0:255:255>;
	public static final var YELLOW_COLOR = <color:127:127:0:255>;
	public static final var CYAN_COLOR = <color:0:127:127:255>;
	public static final var MAGENTA_COLOR = <color:127:0:127:255>;
	public static final var BLACK_COLOR = <color:0:0:0:255>;
	public static final var TRANSPARENT_COLOR = <color:0:0:0:0>;
	public static var game as SwingGraphicGame;
	public static var i as double;
	public static var drawed as int[];
	public static var backgroundColor as Color;
	public static var voidImage as Image;
	public static var voidX as int;
	public static var voidY as int;
	public static var voidWidth as int;
	public static var voidHeight as int;
	
	public static register() as void
	{
		var builder = SwingGraphicGame.create("voidClicker");
		
		builder.onStart = () => VoidClicker.start();
		builder.onLoop = partial => VoidClicker.loop(partial);
		builder.onTerminate = () => VoidClicker.terminate();
		builder.width = 540;
		builder.height = 360;
		builder.tps = 60;
		builder.icon = <file:assets/zengame/void.png>;
		builder.backgroundColor = backgroundColor = <color:0:0:0:255>;
		builder.paintAction = () => VoidClicker.paint();
		builder.onClick = c => VoidClicker.onClick(c);
		game = GameRegistry.register(builder);
		
		println(game);
	}
	
	public static start() as void
	{
		game.setColor(backgroundColor);
		game.clear();
		game.setColor(<color:0:255:0:255>);
		voidImage = ImageLoader.getImage(<file:assets/zengame/void.png>);
		
		i = 0;
		drawed = new int[](1000);
		voidWidth = 128;
		voidHeight = 128;
		voidX = (game.width - voidWidth) / 2;
		voidY = (game.height - voidHeight) / 2;
	}
	
	public static loop(partial as float) as void
	{
		i = game.enlapsedTime;
		
	}
	
	public static onClick(context as ButtonContext) as void
	{
		if (context.x >= voidX && context.x < voidX + voidWidth && context.y >= voidY && context.y < voidY + voidHeight)
		{
			println("+1");
		}
	}
	
	public static paint() as void
	{
		game.setColor(BLACK_COLOR);
		game.drawImage(voidImage, voidX, voidY, voidWidth, voidHeight);
		println("PAINT");
	}
	
	public static terminate() as void
	{
		
	}
}

println("--------------------------------");
println("              ZEN               ");
println("--------------------------------");

VoidClicker.register();

println("--------------------------------");
println("              END               ");
println("--------------------------------");