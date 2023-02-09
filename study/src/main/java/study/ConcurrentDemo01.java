package study;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConcurrentDemo01 {
  public static void main(String[] args) {

    Stream<Integer> parallel = Stream.of(1, 2, 3, 4, 5)
        .parallel();
    System.out.println("Parallel is " + parallel.isParallel());

    parallel = parallel.sequential();
    System.out.println("Parallel is " + parallel.isParallel());
    System.out.println("=====================");
    // 串流從開始到結束只會有一種狀態,所以平行與循序狀態會取決於最後宣告是 parallel 或 sequential
    List<Integer> list = Arrays.asList(1, 2, 9, 4, 6).parallelStream()
        .map(m -> m * 2)
        .peek(n -> System.out.printf("%s processing %d%n", Thread.currentThread().getName(), n))
        .sequential()
        .sorted()
        .collect(Collectors.toList());

    System.out.println("=====================");
    list.forEach(System.out::println);
    System.out.println("=====================");
    // sequential
    Instant before = Instant.now();
    int total = IntStream.of(3, 1, 4, 1, 5, 9)
        .map(ConcurrentDemo01::doubleIt)
        .sum();
    Instant after = Instant.now();
    Duration duration = Duration.between(before, after);
    System.out.println("total:" + total);
    System.out.println("sequential duration: " + duration.toMillis() + " ms");

    // parallel
    before = Instant.now();
    total = IntStream.of(3, 1, 4, 1, 5, 9)
        .parallel()
        .map(ConcurrentDemo01::doubleIt)
        .sum();
    after = Instant.now();
    duration = Duration.between(before, after);
    System.out.println("total:" + total);
    System.out.println("parallel duration: " + duration.toMillis() + " ms");
  }

  public static int doubleIt(int n) {
    try {
      Thread.sleep(100);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return n * 2;
  }
}
