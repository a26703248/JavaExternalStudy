package study;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ConcurrentDemo02 {
  public static void main(String[] args) {
    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
    long total = LongStream.rangeClosed(1, 3_000_000)
        .parallel()
        .sum();
    int poolSize = ForkJoinPool.commonPool().getPoolSize();
    System.out.println("Pool size:" + poolSize);
    System.out.println("=============");
    ForkJoinPool pool = new ForkJoinPool(15);
    ForkJoinTask<Long> task = pool.submit(() -> LongStream.rangeClosed(1, 3_000_000).parallel().sum());
    try {
      total = task.get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    poolSize = pool.getPoolSize();
    System.out.println("Pool size:" + poolSize);
  }
}
