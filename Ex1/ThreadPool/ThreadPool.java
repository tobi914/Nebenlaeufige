package u1.a2;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool
{
    private final BlockingQueue<Runnable> workerQueue;
    private final Thread[] workerThreads;

    public ThreadPool(int N)
    {
        workerQueue = new LinkedBlockingQueue<Runnable>();
        workerThreads = new Thread[N];

        //Start N Threads and keep them running
        for (int i = 0; i < N; ++i)
        {
            workerThreads[i] = new Worker("Pool Thread " + i, workerQueue);
            workerThreads[i].start();
        }
    }

    public void addTask(Runnable r)
    {
        try
        {
            workerQueue.put(r);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() throws InterruptedException
    {
        while (!workerQueue.isEmpty())
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        for (Thread workerThread : workerThreads)
        {
            workerThread.interrupt();
            workerThread.join();
        }
    }

    public static void main(String[] args)
    {
        int numWorkers = 0;
        int accuracy = 0;
        if (args.length < 2)
        {
            System.err.println("Usage: " + ThreadPool.class.getName() + " <numWorkers> <accuracy>");
            System.exit(1);
        }
        else
        {
            numWorkers = Integer.valueOf(args[0]);
            accuracy = Integer.valueOf(args[1]);
            if (numWorkers <= 0 || accuracy <= 0)
            {
                System.err.println("Must be positive Numbers");
                System.exit(1);
            }
        }

        ThreadPool pool = new ThreadPool(numWorkers);
        LinkedList<Job> jobs = new LinkedList<Job>();

        int sizeChunk = accuracy / numWorkers; // number of jobs per worker
        int sizeRest = accuracy % numWorkers;
        if (sizeChunk == 0)
        {
            numWorkers = accuracy;
            sizeChunk = 1;
            sizeRest = 0;
        }

        for (int i = 0; i < numWorkers; ++i)
        {
            jobs.add(new Job(i * sizeChunk, (i + 1) * (sizeChunk - 1) + i));
        }
        if (sizeRest != 0)
        {
            jobs.add(new Job(numWorkers * (sizeChunk - 1) + numWorkers, accuracy - 1));
        }

        for (Job j : jobs)
        {
            pool.addTask(j);
        }
        try
        {
            pool.shutdown();
            BigDecimal result = BigDecimal.ZERO;
            for (Job j : jobs)
            {
                result = result.add(j.getResult());
            }
            result = result.multiply(BigDecimal.valueOf(4));

            System.out.println("Pi is " + result.toString());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.exit(0);
    }
}