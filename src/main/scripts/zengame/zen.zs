import zengame.Action;
import zengame.GameRegistry;
import zengame.Game;
import zengame.keyboard.KeyboardListener;
import zengame.keyboard.KeyContext;
import zengame.keyboard.KeyPressType;

println("--------------------------------");
println("              ZEN               ");
println("--------------------------------");

public class IntegerPointer {
	
	private var i as int;
	
	public this(i as int)
	{
		this.i = i;
	}
	
	public next() as int
	{
		i += 1;
		return i;
	}
}

var i = new IntegerPointer(0);

var game = GameRegistry.registerCommandLineGame("zen");

println(game);

game.onStart = () => {
	game.addKeyListener(context => {
		if (context.virtualKeyCode == KeyContext.KEY_ESCAPE) game.terminate();
	});
	game.println("Press 'ESC' to exit in any moment");
};
game.onLoop = partial => {
	
};
game.onTerminate = () => {
	game.println();
	game.println("GAME OVER");
	game.println();
};
println(game.isRunning());


public class C {
	public static var i = 0;
	
	public static getInt() as int
	{
		i++;
		return i;
	}
	
	private var j as int;
	
	public this(k as int)
	{
		j = k;
	}
	
	public next() as int
	{
		j += 1;
		return j;
	}
}
println(C.getInt());
println(C.getInt());
println(C.getInt());
println(C.getInt());

var v0 = 3;
<action:moving>;
println(<action:sleeping>);
println(Action.WORKING);
var c = new C(3);
println(c.next());
println(c.next());

var arr = ["A", "B", "C"] as string[];
println(arr[0]);

var a = "alfa" as string;

println(a);

var aa = <action:working>;
println(aa);

println("--------------------------------");
println("              END               ");
println("--------------------------------");