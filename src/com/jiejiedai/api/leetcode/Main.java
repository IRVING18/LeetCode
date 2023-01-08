package com.jiejiedai.api.leetcode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static int i = 1;
    public static ReentrantLock monitor = new ReentrantLock(true);
    static Condition condition = monitor.newCondition();


    public static AtomicInteger integer = new AtomicInteger(0);

    //    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            monitor.lock();
//            if(i <= 100){
//                System.out.print(i ++);
//            }
//            monitor.unlock();
//        }
//    };
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println("Hello World!");
        MyThread thread1 = new MyThread(0);
        MyThread thread2 = new MyThread(1);
        MyThread thread3 = new MyThread(2);
        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class MyThread extends Thread {
        int num;
        MyThread(int num){
            this.num = num;
        }
        @Override
        public void run() {
            super.run();
            while (true) {
                monitor.lock();
                if (i > 100) {
                    monitor.unlock();
                    break;
                }
//                if (i % 3 == num) {
                    System.out.println(Thread.currentThread().getName() + "  " + i++);
//                } else {
//                    try {
//                        condition.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                condition.signalAll();
                monitor.unlock();
            }
//            while (integer.get() < 100) {
//                System.out.println(Thread.currentThread().getId() + "  "+ integer.getAndAdd(1));
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }


//     public static void print(int[][] arr){
//         if(arr == null){
//             return;
//         }
//         int s = arr.length;
//         int w = arr[0].length;

//         for(int i = 0; i < s; i++){
//             int iw = s - i;
//             for(int j = 0;j < w;j++){
//                 System.out.println(arr[i][j]);
//                 while(iw > i){
//                     System.out.println(arr[iw][j]);
//                     iw -- ;
//                 }
//             }
//         }
//     }
}