import zengame.Action;
import zengame.GameRegistry;
import zengame.Game;
import zengame.keyboard.KeyboardListener;
import zengame.keyboard.KeyContext;
import zengame.keyboard.KeyPressType;

println("--------------------------------");
println("              ZEN               ");
println("--------------------------------");

public class Vars {
	public static final var screen = [
			"/-----------------------------------\\",
			"|                                   |",
			"|                                   |",
			"|                                   |",
			"|                                   |",
			"|                                   |",
			"|                                   |",
			"|                                   |",
			"|                                   |",
			"|                                   |",
			"\\-----------------------------------/"
	    ] as char[][];
	public static final var minX = 0 as uint;
	public static final var maxX = 34 as uint;
	public static final var minY = 0 as uint;
	public static final var maxY = 8 as uint;
	public static var lastX = 0 as usize;
	public static var lastY = 0 as usize;
	public static var x = 17 as usize;
	public static var y = 3 as usize;
	public static var moved = true;
}

var game = GameRegistry.registerCommandLineGame("zen");

println(game);

game.onStart = () => {
	game.addKeyListener(context => {
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
				if (Vars.y < Vars.maxY) 
				{
					Vars.y++;
					Vars.moved = true;
				}
			}
			else if (context.virtualKeyCode == KeyContext.KEY_LEFT)
			{
				if (Vars.x > Vars.minX)
				{
					Vars.x--;
					Vars.moved = true;
				}
			}
			else if (context.virtualKeyCode == KeyContext.KEY_RIGHT) 
			{
				if (Vars.x < Vars.maxX)
				{
					Vars.x++;
					Vars.moved = true;
				}
			}
			else if (context.virtualKeyCode == KeyContext.KEY_DOWN)
			{
				if (Vars.y > Vars.minY)
				{
					Vars.y--;
					Vars.moved = true;
				}
			}
		}
	});
	game.println("Press 'ESC' to exit in any moment");
};
game.onLoop = partial => {
	
	if (Vars.moved)
	{
		game.clear();
		game.println("{" + Vars.x + ";" + Vars.y + "}");
		Vars.screen[11 - Vars.lastY - 2][Vars.lastX + 1] = ' ';
		Vars.screen[11 - Vars.y - 2][Vars.x + 1] = '*';
		Vars.lastX = Vars.x;
		Vars.lastY = Vars.y;
		
		for row in Vars.screen
		{
			for i in 0 .. row.length
			{
				game.print(row[i]);
			}
			game.println();
		}
		
		Vars.moved = false;
	}
	
};
game.onTerminate = () => {
	game.println();
	game.println("GAME OVER");
	game.println();
};

println("--------------------------------");
println("              END               ");
println("--------------------------------");