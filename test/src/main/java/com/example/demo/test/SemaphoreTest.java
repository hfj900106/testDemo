package test.src.main.java.com.example.demo.test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 *
 * @author hfj
 * @date 2019\12\15 0015
 */
public class SemaphoreTest {


    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5,true);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    if (semaphore.tryAcquire(1,10, TimeUnit.SECONDS)) {
                        System.out.println(Thread.currentThread().getName() + "被秦国灭");
                    }else {
                        System.out.println(Thread.currentThread().getName() + "拿不到锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }, test.src.main.java.com.example.demo.test.CountryEnum.getName(i)).start();
        }
    }


}




