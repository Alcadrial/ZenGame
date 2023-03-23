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

	public static move(x as int, y as int) as void
	{
		Vars.screen[Vars.y][Vars.x] = ' ';
		Vars.screen[y][x] = 'C';
		Vars.y = y;
		Vars.x = x;
		Vars.moved = true;
	}
}

var game = GameRegistry.registerCommandLineGame("pacman");

println(game);

game.onStart = () => {
	game.addKeyListener(context => {
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
			
			if (context.virtualKeyCode == KeyContext.KEY_UP) Vars.direction = Vars.UP;
			else if (context.virtualKeyCode == KeyContext.KEY_LEFT) Vars.direction = Vars.LEFT;
			else if (context.virtualKeyCode == KeyContext.KEY_DOWN) Vars.direction = Vars.DOWN;
			else if (context.virtualKeyCode == KeyContext.KEY_RIGHT) Vars.direction = Vars.RIGHT;
		}
	});
	game.println("Press 'ESC' to exit in any moment");
	Vars.screen[Vars.y][Vars.x] = "C";
};
game.onLoop = partial => {

	Vars.clock++;
	//	Vars.screen[11 - Vars.lastY - 2][Vars.lastX + 1] = ' ';
	//	Vars.screen[11 - Vars.y - 2][Vars.x + 1] = '*';
	//	Vars.lastX = Vars.x;
	//	Vars.lastY = Vars.y;
	switch Vars.direction
	{
		case 1:
			var next = Vars.screen[Vars.y - 1][Vars.x];
			if (next == ' ')
			{
				Vars.move(Vars.x, Vars.y - 1);
			}
			else if (next == '·')
			{
				Vars.move(Vars.x, Vars.y - 1);
				Vars.remaining--;
			}
			break;
		case 2:
			var next = Vars.screen[Vars.y][Vars.x - 1];
			if (next == ' ')
			{
				if (Vars.x == 1) Vars.move(26, Vars.y);
				else Vars.move(Vars.x - 1, Vars.y);
			}
			else if (next == '·')
			{
				Vars.move(Vars.x - 1, Vars.y);
				Vars.remaining--;
			}
			break;
		case 3:
			var next = Vars.screen[Vars.y + 1][Vars.x];
			if (next == ' ')
			{
				Vars.move(Vars.x, Vars.y + 1);
			}
			else if (next == '·')
			{
				Vars.move(Vars.x, Vars.y + 1);
				Vars.remaining--;
			}
			break;
		case 4:
			var next = Vars.screen[Vars.y][Vars.x + 1];
			if (next == ' ')
			{
				if (Vars.x == 26) Vars.move(1, Vars.y);
				else Vars.move(Vars.x + 1, Vars.y);
			}
			else if (next == '·')
			{
				Vars.move(Vars.x + 1, Vars.y);
				Vars.remaining--;
			}
			break;
	}
	if (Vars.moved || Vars.clock == 5)
	{
		if (Vars.clock == 5) Vars.clock = 0;
		game.clear();
		for row in Vars.screen
		{
			for i in 0 .. row.length
			{
				game.print(row[i]);
			}
			game.println();
		}
	}
};
game.onTerminate = () => {
	
	var row = Vars.screen[16];
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
	for row in Vars.screen
	{
		for i in 0 .. row.length
		{
			game.print(row[i]);
		}
		game.println();
	}
};

game.tps = 4;

println("--------------------------------");
println("              END               ");
println("--------------------------------");