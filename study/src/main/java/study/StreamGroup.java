package study;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamGroup {
  public static void main(String[] args) {
    List<String> list = Arrays.asList("this", "is", "a", "long", "list", "of", "strings", "to", "use", "as", "a",
        "demo");
    Map<Boolean, List<String>> res1 = list.stream().collect(Collectors.partitioningBy(s -> s.length()%2==0));
    Map<Integer, Long> res2 = list.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
    Map<Boolean, Long> res3 = list.stream().collect(Collectors.partitioningBy(s -> s.length()%2==0, Collectors.counting()));
    System.out.println("=========partitioningBy==========");
    res1.forEach((k, v) -> System.out.println(k + ":" + v));
    System.out.println("=========下游搜集器==========");
    res3.forEach((k, v) -> System.out.println(k + ":" + v));
    System.out.println("=========groupingBy==========");
    res2.forEach((k, v) -> System.out.println(k + ":" + v));

  }
}
