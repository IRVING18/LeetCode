package com.jiejiedai.api;

import com.sun.istack.internal.Nullable;

import java.util.concurrent.*;

public class ThreadPoolTest {
    private static @Nullable
    ExecutorService executorService;

    public static synchronized ExecutorService executorService() {
        if (executorService == null) {
            //任务阻塞队列
            BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(100);
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), threadFactory("OkHttp Dispatcher", false));
        }
        return executorService;
    }


    public static ThreadFactory threadFactory(final String name, final boolean daemon) {
        return new ThreadFactory() {
            @Override public Thread newThread(Runnable runnable) {
                Thread result = new Thread(runnable, name);
                result.setDaemon(daemon);
                return result;
            }
        };
    }
//
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//
//        executorService().execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getName()+" - "+Thread.currentThread().getId() + " start ");
//                    Thread.sleep(3000);
//                    System.out.println(Thread.currentThread().getName()+" - "+Thread.currentThread().getId() + " end ");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        }
//    }



    public static void main(String[] args) {
        SynchronousQueue<Runnable> qu = new SynchronousQueue<>();

//        for (int i = 0; i < 19; i++) {
//            boolean offer = qu.offer(new Runnable() {
//                @Override
//                public void run() {
//                    Thread thread = new Thread();
//                    thread.join();
//                }
//            });
//            System.out.println(i+ " " +offer);
//        }
//
//
//        //核心线程数
//        int corePoolSize = 0;
//        //最大线程数
//        int maximumPoolSize = 10;
//        //线程存活时间
//        long keepAliveTime = 20;
//        //存活时间单位
//        TimeUnit unit = TimeUnit.SECONDS;
//        //任务阻塞队列
//        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(5);
//        //线程工厂
//        ThreadFactory threadFactory = Executors.defaultThreadFactory();
//        //拒绝处理器
//        RejectedExecutionHandler handler = new myRejectedExecutionHanlder();
//
//        //创建线程池
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
//
//        //设置核心线程是否可以退出。默认是false。
//        //如果设置true，那么keepAliveTime存活时间将在核心线程上同样生效。
////        executor.allowCoreThreadTimeOut(true);
//
//        for (int i = 0; i < 10; i++) {
//            executor.execute(new MyTask(i));
//            System.out.println("线程池中线程数：" + executor.getPoolSize()
//                    + "  ，队列中等待执行的任务数：" + executor.getQueue().size()
//                    + "  ，已执行完成的任务数：" + executor.getCompletedTaskCount()
//                    + "  ，待执行和执行中的任务数：" + executor.getTaskCount()
//            );
//        }

    }

    static class MyTask implements Runnable {
        private int taskId;

        public MyTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println("正在执行taskId  " + taskId);
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("执行完毕taskId  " + taskId);
        }
    }

    /**
     * 自定义任务拒绝处理器
     * 这里只做演示，和系统AbortPolicy实现一致。
     */
    static class myRejectedExecutionHanlder implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    executor.toString());
        }
    }
}
