package com.example.demo.test;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.*;

/**
 * @author hfj
 * @date 2018/12/13
 */
public class ThreadPoolTest {


    public static void main(String[] args) {
        /**
         * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略，通常有以下四种策略：
         * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
         * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
         * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
         */
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
        /**
         * 　　workQueue来存放等待执行的任务,类型为BlockingQueue<Runnable>，通常可以取下面三种类型：
         * 　　1）ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；
         * 　　2）LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；
         * 　　3）synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5), handler);

        for (int i = 0; i < 1; i++) {
            MyTask myTask = new MyTask(i);

            Future<String> future = executor.submit(myTask);
            try {
                String s = future.get();
                System.out.println("get的结果："+s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(JSON.toJSONString(future));

            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdownNow();
    }

}

class MyTask implements Callable<String> {
    private int taskNum;

    public MyTask(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String call() {
        System.out.println("正在执行task " + taskNum);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "call 返回值：task " + taskNum + " 执行完毕";
    }
}

