package study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo01 {
  public static void main(String[] args) {
    ExecutorService service = Executors.newCachedThreadPool();
    Future<String> future = service.submit(() -> {
      Thread.sleep(100);
      return "Hello World!";
    });
    System.out.println("Processing...");
    // while(!future.isDone()){
    //   System.out.println("Waiting...");
    // }
    future.cancel(true);
    new FutureDemo01().getIfNotCancelled(future);
  }

  public void getIfNotCancelled(Future<String> future) {
    try {
      if (!future.isCancelled()) {
        System.out.println(future.get());
      } else {
        System.out.println("Cancelled");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
