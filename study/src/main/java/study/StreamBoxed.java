package study;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBoxed {
  public static void main(String[] args) {
    List<Integer> list = IntStream.of(1, 2, 3, 4, 5).boxed().collect(Collectors.toList());
    list.stream().forEach(System.out::println);
    System.out.println("=======================");
    String content = Stream.of("this", "is", "a", "Java Code").reduce((a, b) -> a + " " + b).orElse(" ");
    System.out.println(content);

    System.out.println("=======================");
    int sum = Stream.of("this", "is", "a", "Java Code")
        .map(String::length)
        .reduce(0, Integer::sum);
    System.out.println(sum);

    System.out.println("=======================");
    IntSummaryStatistics iss = Stream.of("this", "is", "a", "Java Code")
        .map(String::length)
        .mapToInt(Integer::intValue)
        .summaryStatistics();
    System.out.println(iss.getSum());
  }
}
