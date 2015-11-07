import java.util.Random;


public class Producer extends Thread 
{
	private Buffer b;
	private int max, min, minTime, maxTime;
	
	public Producer (Buffer b, int min, int max, int minTime, int maxTime)
	{
		this.b = b;
		this.max = max;
		this.min = min;
		this.minTime = minTime;
		this.maxTime = maxTime;
	}
	
	public void run()
	{
		int num = -1;
		int st;
		while(num != 0)
		{
			num = generateNumber();
			synchronized(b)
			{
				b.push(num);
			}
			System.out.println("Pushed " + num + " into buffer.");
			st = sleepTime();
			System.out.println("Producer sleeping for " + st + "ms.");
			try 
			{
				sleep(st);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
				return;
			}
		}
		System.out.println("Generated 0, finishing.");
		return;
	}
	
	private int generateNumber()
	{
		Random rn = new Random();
		return rn.nextInt(max + 1) + min;
	}
	
	private int sleepTime()
	{
		Random rn = new Random();
		return rn.nextInt(maxTime + 1) + minTime;
	}
}
