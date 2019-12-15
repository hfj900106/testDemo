package test.src.main.java.com.example.demo.test;

import java.util.concurrent.CountDownLatch;

/**
 * 多线程，每次完成减一，到了0 才可以执行await之后的代码，不然一致阻塞
 * @author hfj
 * @date 2019\12\15 0015
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws Exception {
        CountDownLatch downLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "被秦国灭");
                downLatch.countDown();
            }, CountryEnum.getName(i)).start();
        }
        downLatch.await();
        System.out.println("六国统一");
    }
}
