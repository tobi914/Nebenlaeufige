
public class Consumer extends Thread
{
	private Buffer b;
	
	public Consumer(Buffer b)
	{
		this.b = b;
	}
	
	public void run()
	{
		int num = -1;
		while(num != 0)
		{
			if(!b.isEmpty())
			{
				synchronized(b)
				{
					num = b.pop();
				}
				System.out.println("Consumed " + num + " from buffer.");
			}
			else
			{
				System.out.println("Buffer empty, sleeping for 2 seconds.");
				try 
				{
					sleep(2000);
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
					return;
				}
			}
		}
		System.out.println("Consumed 0 from buffer, finishing.");
		return;
	}
}
