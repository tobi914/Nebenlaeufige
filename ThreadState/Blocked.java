
public class Blocked extends Thread 
{
	private int[] a;
	
	public Blocked(int[] a)
	{
		this.a = a;
	}
	
	public void run()
	{
		synchronized(a)
		{
			a[0] = 0;
		}
	}
}
