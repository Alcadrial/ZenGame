import zengame.Action;

println("--------------------------------");
println("              ZEN               ");
println("--------------------------------");

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