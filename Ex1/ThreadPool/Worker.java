package u1.a2;

import java.util.concurrent.BlockingQueue;

public class Worker extends Thread
{
    private final BlockingQueue<Runnable> workerQueue;

    public Worker(String name, BlockingQueue<Runnable> workerQueue)
    {
        super(name);
        this.workerQueue = workerQueue;
    }

    public void run()
    {
        while (!interrupted())
        {
            try
            {
                // each thread wait for next runnable and executes it's run method
                Runnable r = workerQueue.take();
                r.run();
            }
            catch (InterruptedException e)
            {
                interrupt();
            }
        }
    }
}