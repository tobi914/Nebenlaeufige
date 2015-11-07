
public class Runner extends Thread
{
	private int[] a;
	
	public Runner(int[] a)
	{
		this.a = a;
	}
	
	public void run()
	{
		synchronized(a)
		{
			while(true)
			{
				
			}
		}
	}
}
