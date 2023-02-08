package study;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ComparatorDemo {
  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>();
    map.put("1", "this");
    map.put("2", "is");
    map.put("3", "a");
    map.put("4", "java");
    map.put("5", "code");
    Map<Integer, Long> res = map.entrySet().stream()
        .map(Map.Entry::getValue)
        .collect(Collectors
            .groupingBy(String::length, Collectors.counting()));
    res.entrySet().stream().forEach((e) -> System.out.println(e.getKey() + ":" + e.getValue()));
  }
}
