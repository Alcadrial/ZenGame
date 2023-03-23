import zengame.CommandLineGame;
import zengame.GameRegistry;
import zengame.keyboard.KeyContext;

public class Pacman {
	
	public static final var screen = [
	        "                            ",
	        "                            ",
			"╔════════════╦╦════════════╗",
			"║············││············║",
			"║·┌──┐·┌───┐·││·┌───┐·┌──┐·║",
			"║·│  │·│   │·││·│   │·│  │·║",
			"║·└──┘·└───┘·└┘·└───┘·└──┘·║",
			"║··························║",
			"║·┌──┐·┌┐·┌──────┐·┌┐·┌──┐·║",
			"║·└──┘·││·└──┐┌──┘·││·└──┘·║",
			"║······││····││····││······║",
			"╚════╗·│└──┐ ││ ┌──┘│·╔════╝",
			"     ║·│┌──┘ └┘ └──┐│·║     ",
			"     ║·││          ││·║     ",
			"     ║·││ ╔══──══╗ ││·║     ",
			"═════╝·└┘ ║      ║ └┘·╚═════",
			"      ·   ║      ║   ·      ",
			"═════╗·┌┐ ║      ║ ┌┐·╔═════",
			"     ║·││ ╚══════╝ ││·║     ",
			"     ║·││          ││·║     ",
			"     ║·││ ┌──────┐ ││·║     ",
			"╔════╝·└┘ └──┐┌──┘ └┘·╚════╗",
			"║············││············║",
			"║·┌──┐·┌───┐·││·┌───┐·┌──┐·║",
			"║·└─┐│·└───┘·└┘·└───┘·│┌─┘·║",
			"║···││················││···║",
			"╠─┐·││·┌┐·┌──────┐·┌┐·││·┌─╣",
			"╠─┘·└┘·││·└──┐┌──┘·││·└┘·└─╣",
			"║······││····││····││······║",
			"║·┌────┘└──┐·││·┌──┘└────┐·║",
			"║·└────────┘·└┘·└────────┘·║",
			"║··························║",
			"╚══════════════════════════╝",
	        "                            ",
	    ] as char[][];
	public static final var UP = 1;
	public static final var LEFT = 2;
	public static final var DOWN = 3;
	public static final var RIGHT = 4;
	public static var x = 14;
	public static var y = 25;
	public static var moved = true;
	public static var direction = 0;
	public static var clock = 0;
	public static var remaining = 245;
	public static var game as CommandLineGame;

	public static move(x as int, y as int) as void
	{
		screen[Pacman.y][Pacman.x] = ' ';
		screen[y][x] = 'C';
		Pacman.y = y;
		Pacman.x = x;
		moved = true;
	}
	
	public static register() as void
	{
		var builder = CommandLineGame.create("pacman");
		
		builder.onStart = () => Pacman.start();
		builder.onLoop = partial => Pacman.loop(partial);
		builder.onTerminate = () => Pacman.terminate();
		builder.addKeyListener(context => Pacman.keyListener(context));
		builder.tps = 4;
		
		game = GameRegistry.register(builder);
		
		println(game);
	}
	
	public static start() as void
	{
		game.println("Press 'ESC' to exit in any moment");
		screen[y][x] = "C";
	}
	
	public static keyListener(context as KeyContext) as void
	{
		if (context.virtualKeyCode == KeyContext.KEY_ESCAPE) game.terminate();
		if (context.pressed)
		{
			/*
			var code = context.virtualKeyCode;
			switch code
			{
				case KeyContext.KEY_UP = code:
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
			}*/
			
			if (context.virtualKeyCode == KeyContext.KEY_UP) direction = UP;
			else if (context.virtualKeyCode == KeyContext.KEY_LEFT) direction = LEFT;
			else if (context.virtualKeyCode == KeyContext.KEY_DOWN) direction = DOWN;
			else if (context.virtualKeyCode == KeyContext.KEY_RIGHT) direction = RIGHT;
		}
	}
	
	public static loop(partial as float) as void
	{
		clock++;
		var next as char;
		switch direction
		{
			case 1:
				next = screen[y - 1][x];
				if (next == ' ')
				{
					move(x, y - 1);
				}
				else if (next == '·')
				{
					move(x, y - 1);
					remaining--;
				}
				break;
			case 2:
				next = screen[y][x - 1];
				if (next == ' ')
				{
					if (x == 1) move(26, y);
					else move(x - 1, y);
				}
				else if (next == '·')
				{
					move(x - 1, y);
					remaining--;
				}
				break;
			case 3:
				next = screen[y + 1][x];
				if (next == ' ')
				{
					move(x, y + 1);
				}
				else if (next == '·')
				{
					move(x, y + 1);
					remaining--;
				}
				break;
			case 4:
				next = screen[y][x + 1];
				if (next == ' ')
				{
					if (x == 26) move(1, y);
					else move(x + 1, y);
				}
				else if (next == '·')
				{
					move(x + 1, y);
					remaining--;
				}
				break;
		}
		if (moved || clock == 5)
		{
			if (clock == 5) clock = 0;
			game.clear();
			for row in screen
			{
				for i in 0 .. row.length
				{
					game.print(row[i]);
				}
				game.println();
			}
		}
	}
	
	public static terminate() as void
	{
		var row = screen[16];
		row[9] = 'G';
		row[10] = 'A';
		row[11] = 'M';
		row[12] = 'E';
		row[13] = ' ';
		row[14] = ' ';
		row[15] = 'O';
		row[16] = 'V';
		row[17] = 'E';
		row[18] = 'R';
		game.clear();
		for row0 in screen
		{
			for c in row0 game.print(c);
			game.println();
		}
	}
}

println("--------------------------------");
println("              ZEN               ");
println("--------------------------------");

Pacman.register();

println("--------------------------------");
println("              END               ");
println("--------------------------------");