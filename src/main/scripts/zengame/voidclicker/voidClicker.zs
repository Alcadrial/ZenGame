import zengame.SwingGraphicGame;
import zengame.GameRegistry;
import zengame.keyboard.KeyContext;
import zengame.io.File;

public class VoidClicker {
	
	public static var game as SwingGraphicGame;
	public static var i = 0 as float;
	public static var drawed = new int[](1000);
	
	public static register() as void
	{
		var builder = SwingGraphicGame.create("voidClicker");
		
		builder.onStart = () => VoidClicker.start();
		builder.onLoop = partial => VoidClicker.loop(partial);
		builder.onTerminate = () => VoidClicker.terminate();
		builder.width = 1080;
		builder.height = 720;
		builder.icon = <file:assets/zengame/void.png>;
		builder.backgroundColor = <color:0:0:0:255>;
		builder.paintAction = () => VoidClicker.paint();
		game = GameRegistry.register(builder);
		
		println(game);
	}
	
	public static start() as void
	{
		game.clearColor(0, 0, 0, 0);
	}
	
	public static loop(partial as float) as void
	{
		i = game.enlapsedTime;
		i = i as int % 1000;
		game.fillColor(i as int, 10, 1, 5, 0, 255, 0, 255);
	}
	
	public static paint() as void
	{
		game.fillColor(0, 0, 200, 200, 255, 255, 0, 255);
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