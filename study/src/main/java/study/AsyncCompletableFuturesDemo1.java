package study;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncCompletableFuturesDemo1 {
  public static void main(String[] args) throws InterruptedException, ExecutionException {

    CompletableFuture<String> future = CompletableFuture
        .supplyAsync(() -> new AsyncCompletableFuturesDemo1().sleepThenReturnString())
        .thenApply(Integer::parseInt)
        .thenApply(x -> x * 2)
        .thenAccept(System.out::println)
        .thenCombine(CompletableFuture
            .supplyAsync(() -> "42"),
            (n1, n2) -> n2+n1);
    System.out.println("Running...");
    System.out.println(future.get());

  }

  public String sleepThenReturnString() {
    try {
      Thread.sleep(100);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "42";
  }
}
