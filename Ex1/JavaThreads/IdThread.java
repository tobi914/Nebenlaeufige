package u1.a1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IdThread implements Runnable
{
    private final int id;

    public IdThread(int id)
    {
        this.id = id;
    }

    @Override
    public void run()
    {
        System.out.println(this.id);
    }


    public static void main(String[] args)
    {
        int numThreads = 0;
        if (args.length < 1)
        {
            System.err.println("Usage: " + IdThread.class.getName() + " <numThreads>");
            System.exit(1);
        }
        else
        {
            numThreads = Integer.valueOf(args[0]);
            if (numThreads <= 0)
            {
                System.err.println("<numThreads> must be positive");
                System.exit(1);
            }
        }

        final ExecutorService es = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; ++i)
        {
            es.execute(new IdThread(i + 1));
        }
        es.shutdown();
        System.exit(0);
    }

}
