package com.example.demo.test;

/**
 * 1.Lock前缀的指令会引起处理器缓存写回内存；
 * 2.一个处理器的缓存回写到内存会导致其他处理器的缓存失效；
 * 3.当处理器发现本地缓存失效后，就会从内存中重读该变量数据，即可以获取当前最新值。
 * 这样针对volatile变量通过这样的机制就使得每个线程都能获得该变量的最新值。
 *
 * @author hfj
 * @date 2018/10/23
 */
public class VolatileTest {
    private static volatile boolean initFlag = false;

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            System.out.println("线程1： running");
            while (!initFlag) {
                // 一直进不来，直到initFlag的值被修改
            }
            System.out.println("线程1： done");
        }, "线程1").start();

        Thread.sleep(1000);

        new Thread(() -> {
            System.out.println("线程2：want to change initFlag value ");
            initFlag = true;
            try {
                // 说明initFlag被修改之后就立刻同步会主内存，并没有等线程结束才同步
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2：done ");
        }, "线程2").start();
    }

}
