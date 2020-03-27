package com.example.demo.test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author hfj
 * @date 2020-03-24
 */
public class ForkJoinTest {


    static class ComputTask extends RecursiveTask<Long> {

        private static final int THRESHOLD = 10;

        private long start;
        private long end;

        public ComputTask(long n) {
            this(1, n);
        }

        public ComputTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        /**
         * 代码中定义了一个阀值10，也就是说我们会把1-n拆分成1-10、10-20...这样的长度小于10的分段(代码中fork出子任务)，
         * 然后分别计算这些分段和，然后再不断汇总(通过join来获取子任务的结果)，得到最终的结果。
          * @return
         */
        @Override
        protected Long compute() {
            long sum = 0;
            if (end - start < THRESHOLD) {
                for (long j = start; j <= end; j++) {
                    sum += j;
                }
            } else {
                long mid = (start + end) >>> 1;
                ComputTask computTask1 = new ComputTask(start, mid);
                ComputTask computTask2 = new ComputTask(mid, end);
                computTask1.fork();
                computTask2.fork();
                sum = computTask1.join() + computTask2.join();
            }

            return sum;
        }
    }


    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 计算1-100的和。
        ComputTask sumTask = new ComputTask(100);
        ForkJoinTask<Long> submit = forkJoinPool.submit(sumTask);
        Long sum = submit.get();
        System.out.println(sum);

    }


}
