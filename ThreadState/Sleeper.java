
public class Sleeper extends Thread
{
	public Sleeper()
	{
		
	}
	
	public void run()
	{
		try {
			sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
