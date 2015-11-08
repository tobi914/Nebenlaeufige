package u1.a3;

import java.util.LinkedList;
import java.util.List;

public class Test
{
    public static void main(String[] args)
    {
        final Object o = new Object();

        final Thread threadNew = new Thread("Thread new");

        final Thread threadRunnable = new Thread(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        while (!Thread.currentThread().isInterrupted())
                        {
                        }
                    }
                }
                , "Thread runnable"
        );
        threadRunnable.start();

        final Thread threadTimedWaiting = new Thread(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        while (!Thread.currentThread().isInterrupted())
                        {
                            try
                            {
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e)
                            {
                                System.out.println("sleep() was interrupted");
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
                , "Thread timed waiting"
        );
        threadTimedWaiting.start();

        final Thread threadWaiting = new Thread(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        while (!Thread.currentThread().isInterrupted())
                        {
                            try
                            {
                                threadRunnable.join();
                            }
                            catch (InterruptedException e)
                            {
                                System.out.println("join() was interrupted");
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
                , "Thread waiting"
        );
        threadWaiting.start();

        final Thread threadBlockHelper = new Thread(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        synchronized (o)
                        {
                            while (!Thread.currentThread().isInterrupted())
                            {
                            }
                        }
                    }
                }
                , "Thread block helper"
        );
        threadBlockHelper.start();

        final Thread threadBlocked = new Thread(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        synchronized (o)
                        {
                            while (!Thread.currentThread().isInterrupted())
                            {
                            }
                        }
                    }
                }
                , "Thread blocked"
        );
        threadBlocked.start();

        final Thread threadTerminated = new Thread(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                    }
                }
                , "Thread terminated"
        );
        threadTerminated.start();

        final List<String> names = new LinkedList<String>();
        names.add(threadNew.getName());
        names.add(threadRunnable.getName());
        names.add(threadTimedWaiting.getName());
        names.add(threadWaiting.getName());
        names.add(threadBlocked.getName());
        names.add(threadTerminated.getName());

        final Thread monitor = new Thread(
                new Runnable()
                {
                    private void printState(Thread t)
                    {
                        System.out.println(t.getName() + ": " + t.getState());
                    }

                    @Override
                    public void run()
                    {
                        while (!Thread.currentThread().isInterrupted())
                        {
                            printState(threadNew);
                            for (Thread t : Thread.getAllStackTraces().keySet())
                            {
                                if (names.contains(t.getName()))
                                {
                                    printState(t);
                                }
                            }
                            printState(threadTerminated);
                            System.out.println();
                            try
                            {
                                Thread.sleep(3000);
                            }
                            catch (InterruptedException e)
                            {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
                , "Thread monitor"
        );
        monitor.start();

        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                System.out.println("shut down");
                                monitor.interrupt();
                                threadRunnable.interrupt();
                                threadTimedWaiting.interrupt();
                                threadWaiting.interrupt();
                                threadBlockHelper.interrupt();
                                threadBlocked.interrupt();

                                try
                                {
                                    monitor.join(3000);
                                    threadRunnable.join(3000);
                                    threadTimedWaiting.join(3000);
                                    threadWaiting.join(3000);
                                    threadBlockHelper.join(3000);
                                    threadBlocked.join(3000);
                                    System.out.println("exit");
                                    System.exit(0);
                                }
                                catch (InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                        , "Thread shutdown"
                )
        );
    }
}
