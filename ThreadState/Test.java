
public class Test 
{
	public static void main (String[] args)
	{
		Thread[] t = new Thread[6];
		int[] a = new int[1];
		t[0] = new Thread();
		t[1] = new Runner(a);
		t[2] = new Sleeper();
		t[3] = new Waiter();
		t[4] = new Blocked(a);
		t[5] = new Terminator();
		Monitor m = new Monitor(t);
		m.start();
	}
}
