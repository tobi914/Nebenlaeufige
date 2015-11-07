
public class Buffer 
{
	private int[] a;
	private int count;
	
	public Buffer(int size)
	{
		a = new int[size];
		count = 0;
	}
	
	public boolean isEmpty()
	{
		if(count == 0)
			return true;
		else
			return false;
	}
	
	public void push(int x)
	{
		a[count] = x;
		++count;
	}
	
	public int pop ()
	{
		if(isEmpty() == false)
		{
			--count;
			return a[count];
		}
		else
			System.out.println("Buffer is empty");
			return -1;
	}
}
