
public class Monitor extends Thread
{
	private Thread[] t;
	
	public Monitor(Thread[] t)
	{
		this.t = t;
	}
	
	public void run()
	{
		startThreads();
		while(true)
		{
			for(int i = 0; i < t.length; ++i)
			{
				System.out.println(t[i].getState());
			}
			System.out.println();
			try 
			{
				sleep(3000);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void startThreads()
	{
		for(int i = 1; i < t.length; ++i)
		{
			t[i].start();
			try 
			{
				sleep(500);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
