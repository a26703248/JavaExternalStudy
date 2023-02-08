package stream;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamStoredMap {
  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>();
    map.put("2", "is");
    map.put("1", "this");
    map.put("5", "code");
    map.put("3", "a");
    map.put("4", "java");
    map.put("7", "World");
    map.put("6", "Hello");
    Map<Integer, Long> res = map.entrySet().stream()
        .map(Map.Entry::getValue)
        .collect(Collectors
            .groupingBy(String::length, Collectors.counting()));
    res.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
  }
}
