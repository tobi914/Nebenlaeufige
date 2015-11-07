
public class MainTest 
{
	public static void main(String[] args)
	{
		Buffer b = new Buffer(100);
		Producer p = new Producer(b, 0, 100, 0, 3000);
		Consumer c = new Consumer(b);
		p.start();
		c.start();
	}
}
