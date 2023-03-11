import zengame.CommandLineGame;
import zengame.GameRegistry;
import zengame.keyboard.KeyContext;

public class Zen {
	public static final var screen = [
			"╔═══════════════════════════════════╗",
			"║                                   ║",
			"║                                   ║",
			"║                                   ║",
			"║                                   ║",
			"║                                   ║",
			"║                                   ║",
			"║                                   ║",
			"║                                   ║",
			"║                                   ║",
			"╚═══════════════════════════════════╝"
	    ] as char[][];
	private static final var minX = 0 as uint;
	private static final var maxX = 34 as uint;
	private static final var minY = 0 as uint;
	private static final var maxY = 8 as uint;
	private static var lastX = 0 as usize;
	private static var lastY = 0 as usize;
	private static var x = 17 as usize;
	private static var y = 3 as usize;
	private static var moved = true;
	private static var game as CommandLineGame;
	
	public static register() as void
	{
		var builder = CommandLineGame.create("zen");
		
		builder.onStart = () => Zen.start();
		builder.onLoop = partial => Zen.loop(partial);
		builder.onTerminate = () => Zen.terminate();
		builder.addKeyListener(context => Zen.keyListener(context));
		
		game = GameRegistry.register(builder);
		
		println(game);
	}
	
	public static start() as void
	{
		game.println("Press 'ESC' to exit in any moment");
	}
	
	public static keyListener(context as KeyContext) as void
	{
		if (context.virtualKeyCode == KeyContext.KEY_ESCAPE) game.terminate();
		if (context.pressed)
		{
			/*
			switch(context.virtualKeyCode)
			{
				case KeyContext.KEY_UP:
					if (Vars.y < 10) Vars.y++;
					break;
				case KeyContext.KEY_LEFT:
					if (Vars.x > 0) Vars.x--;
					break;
				case KeyContext.KEY_RIGHT:
					if (Vars.x < 10) Vars.x++;
					break;
				case KeyContext.KEY_DOWN:
					if (Vars.y > 3) Vars.y--;
					break;
			}
			*/
			
			if (context.virtualKeyCode == KeyContext.KEY_UP)
			{
				if (y < maxY) 
				{
					y++;
					moved = true;
				}
			}
			else if (context.virtualKeyCode == KeyContext.KEY_LEFT)
			{
				if (x > minX)
				{
					x--;
					moved = true;
				}
			}
			else if (context.virtualKeyCode == KeyContext.KEY_RIGHT) 
			{
				if (x < maxX)
				{
					x++;
					moved = true;
				}
			}
			else if (context.virtualKeyCode == KeyContext.KEY_DOWN)
			{
				if (y > minY)
				{
					y--;
					moved = true;
				}
			}
		}
	}
	
	public static loop(partial as float?) as void
	{
		if (moved)
		{
			game.clear();
			game.println("{" + x + ";" + y + "}");
			screen[11 - lastY - 2][lastX + 1] = ' ';
			screen[11 - y - 2][x + 1] = '*';
			lastX = x;
			lastY = y;
			
			for row in screen
			{
				for c in row game.print(c);
				game.println();
			}
			
			moved = false;
		}
	}
	
	public static terminate() as void
	{
		game.println();
		game.println("GAME OVER");
		game.println();
	}
}

println("--------------------------------");
println("              ZEN               ");
println("--------------------------------");

Zen.register();

println("--------------------------------");
println("              END               ");
println("--------------------------------");