
public class Waiter extends Thread
{
	public Waiter()
	{
		
	}
	
	public void run()
	{
		try 
		{
			synchronized(this)
			{
				wait();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
