package com.example.demo.test;

/**
 * 利用wait()和notify()方法实现多线程的“有序”执行
 *
 * @author hfj
 * @date 2019-09-03
 */
public class ThreadOrderPrint {

    static class OrderedThread implements Runnable {
        private Object current;
        private Object next;
        private String printStr;

        public OrderedThread(Object current, Object next, String printStr) {
            this.current = current;
            this.next = next;
            this.printStr = printStr;
        }

        @Override
        public void run() {
            boolean currentLock = Thread.holdsLock(current);
            System.out.println("是否持有currentLock锁：" + currentLock);
            synchronized (current) {
                boolean nextLock = Thread.holdsLock(next);
                System.out.println("是否持有nextLock锁：" + nextLock);

                synchronized (next) {
                    System.out.println(Thread.currentThread().getName() + ": " + printStr);
                    // 在当前线程打印完之后，唤醒下一个线程：Wakes up a single thread that is waiting on this object's monitor
                    next.notify();
                }
                try {
                    // 当前线程打印完，就先阻塞起来，等待一轮打印完成后，才进而二轮循环
                    current.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new OrderedThread("A", "B", "aaa"), "A线程").start();
        Thread.sleep(100);
        new Thread(new OrderedThread("B", "C", "bbb"), "B线程").start();
        Thread.sleep(100);
        new Thread(new OrderedThread("C", "A", "ccc"), "C线程").start();
    }
}
