package study;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class UnmodifiableCollection {
  public static void main(String[] args) {
    System.out.println("=========immutableList=============");
    List<String> list = Arrays
        .asList("this", "is", "a", "long", "list", "of", "strings", "to", "use", "as", "a", "demo")
        .stream()
        .collect(Collectors
            .collectingAndThen(Collectors.toList(),
                Collections::unmodifiableList));
    try {
      if (new Random().nextInt(10) % 3 == 0) {
        list.add("Ops!");
      }
    } catch (UnsupportedOperationException e) {
      System.out.println("List Exception");
    }

    System.out.println("=========immutableMap=============");
    Map<String, String> map = Collections.unmodifiableMap(
        new HashMap<String, String>() {
          {
            put("1", "this");
            put("2", "is");
            put("3", "a");
            put("4", "java");
            put("5", "code");
          }
        });
    try {
      if (new Random().nextInt(10) % 3 == 0) {
        map.put("6", "Ops!");
      }
    } catch (UnsupportedOperationException e) {
      System.out.println("Map Exception");
    }

    System.out.println("=========immutableSet=============");
    Set<Integer> set = new HashSet<>();
    set.add(1);
    set.add(2);
    set.add(3);
    set.add(4);
    set.stream()
        .collect(Collectors
            .collectingAndThen(Collectors.toSet(),
                Collections::unmodifiableSet));
    try {
      if (new Random().nextInt(10) % 3 == 0) {
        set.add(0);
      }
    } catch (UnsupportedOperationException e) {
      System.out.println("Set Exception");
    }
  }
}
